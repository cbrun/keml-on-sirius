import { ServerContext } from '@eclipse-sirius/sirius-components-core';
import { TreeItemContextMenuComponentProps, TreePaletteContext } from '@eclipse-sirius/sirius-components-trees';
import { PaletteToolOverriddenContributionComponentProps, ToolListItemText, fuzzyMatch } from '@eclipse-sirius/sirius-components-palette';
import DownloadIcon from '@mui/icons-material/Download';
import {
  Alert, Box, Button, Dialog, DialogActions, DialogContent, DialogTitle, FormControl,
  InputLabel, LinearProgress, ListItemIcon, ListItemText, MenuItem, Select, Stack,
  Tab, Tabs, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography,
} from '@mui/material';
import { forwardRef, useContext, useEffect, useRef, useState } from 'react';

type ParticipantCounts = { participant: string; sent: number; received: number; interrupted: number; facts: number; instructions: number };
type Knowledge = { text: string; participant: string; instruction: boolean; preknowledge: boolean; recordedImmediately: number | null; recordedAfterwards: number | null };
type Results = {
  title: string; overview: ParticipantCounts[]; repetitions: number;
  argumentHeaders: string[]; arguments: string[][]; knowledge: Knowledge[];
  trustScenarios: { weight: number; preset: string; scores: { initial: number; computed: number }[] }[];
};
type Analysis = { snapshotId: string; createdAt: string; results: Results };
type Props = { editingContextId: string; conversationId: string; onClose: () => void };

const presets = [
  ['a', 'All partners: 1.0'],
  ['b', 'LLM: 0.5; other partners: 1.0'],
  ['c', 'LLM: 1.0; other partners: 0.5'],
  ['d', 'All partners: 0.5'],
];
const score = (value: number | null) => value == null ? '—' : value.toFixed(2);

async function checkResponse(response: Response): Promise<Response> {
  if (!response.ok) {
    const problem = await response.json().catch(() => null);
    throw new Error(problem?.detail || `Analysis failed (${response.status}). Please try again.`);
  }
  return response;
}

// Match Sirius's standard context-menu modal pattern: keep the contribution mounted
// until the dialog closes, then release the enclosing menu and restore its focus.
export const AnalyseConversationMenu = forwardRef<HTMLLIElement, TreeItemContextMenuComponentProps>(
  ({ editingContextId, item, onClose }, ref) => {
    return <AnalysisAction editingContextId={editingContextId} conversationId={item.id} onClose={onClose} ref={ref} />;
  }
);

export const AnalyseConversationPaletteTool = forwardRef<HTMLLIElement, PaletteToolOverriddenContributionComponentProps>(
  ({ onInvoked, searchedValue, tool, isToolInPalette }, ref) => {
    const { editingContextId, item, onClose } = useContext(TreePaletteContext);
    if (!item) return null;
    if (searchedValue && !fuzzyMatch(tool.label, searchedValue).matches) return null;
    return <AnalysisAction editingContextId={editingContextId} conversationId={item.id} onClose={onClose}
      onInvoked={onInvoked} disabled={!isToolInPalette} searchedValue={searchedValue} ref={ref} />;
  }
);

const AnalysisAction = forwardRef<HTMLLIElement, Props & { onInvoked?: () => void; disabled?: boolean; searchedValue?: string | null }>(
  ({ editingContextId, conversationId, onClose, onInvoked, disabled, searchedValue }, ref) => {
    const { httpOrigin } = useContext(ServerContext);
    const [open, setOpen] = useState(false);
    return <>
      <MenuItem ref={ref} disabled={disabled} onClick={() => { onInvoked?.(); setOpen(true); }} data-testid="keml-analyse-conversation">
        <ListItemIcon><img src={`${httpOrigin}/api/images/icons/full/obj16/AnalyseConversation.svg`} alt="" width={20} height={20} /></ListItemIcon>
        {searchedValue ? <ToolListItemText label="Analyse conversation…" searchedValue={searchedValue} /> : <ListItemText primary="Analyse conversation…" />}
      </MenuItem>
      {open && <AnalysisDialog editingContextId={editingContextId} conversationId={conversationId} onClose={onClose} />}
    </>;
  }
);

function AnalysisDialog({ editingContextId, conversationId, onClose }: Props) {
  const { httpOrigin } = useContext(ServerContext);
  const base = `${httpOrigin}/api/editingcontexts/${encodeURIComponent(editingContextId)}/keml/conversations/${encodeURIComponent(conversationId)}`;
  const [analysis, setAnalysis] = useState<Analysis | null>(null);
  const [loading, setLoading] = useState(true);
  const [downloading, setDownloading] = useState(false);
  const [error, setError] = useState('');
  const [revision, setRevision] = useState(0);
  const [tab, setTab] = useState(0);
  const [weight, setWeight] = useState(2);
  const [preset, setPreset] = useState('a');
  const downloadRequest = useRef<AbortController | null>(null);

  useEffect(() => {
    const request = new AbortController();
    setLoading(true);
    setError('');
    setAnalysis(null);
    fetch(`${base}/analysis`, { method: 'POST', signal: request.signal })
      .then(checkResponse).then(response => response.json()).then(setAnalysis)
      .catch(reason => { if (!request.signal.aborted) setError(reason.message || 'Could not connect to the server.'); })
      .finally(() => { if (!request.signal.aborted) setLoading(false); });
    return () => request.abort();
  }, [base, revision]);
  useEffect(() => () => downloadRequest.current?.abort(), []);

  const download = async () => {
    if (!analysis) return;
    const request = new AbortController();
    downloadRequest.current = request;
    setDownloading(true);
    setError('');
    try {
      const response = await checkResponse(await fetch(`${base}/analysis/${analysis.snapshotId}/reports`, { signal: request.signal }));
      const url = URL.createObjectURL(await response.blob());
      const link = document.createElement('a');
      link.href = url;
      link.download = 'keml-analysis.zip';
      link.click();
      setTimeout(() => URL.revokeObjectURL(url), 1000);
    } catch (reason) {
      if (!request.signal.aborted) setError(reason instanceof Error ? reason.message : 'The reports could not be downloaded.');
    } finally {
      if (!request.signal.aborted) setDownloading(false);
    }
  };

  const results = analysis?.results;
  const scenario = results?.trustScenarios.find(value => value.weight === weight && value.preset === preset);
  return <Dialog open onClose={onClose} fullWidth maxWidth="lg" aria-labelledby="keml-analysis-title">
    <DialogTitle id="keml-analysis-title">Analysis — {results?.title || 'Conversation'}</DialogTitle>
    <DialogContent>
      <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
        {analysis ? `Snapshot taken ${new Date(analysis.createdAt).toLocaleString()}. ` : ''}
        Analysis leaves your conversation unchanged. Recalculate to include subsequent edits.
      </Typography>
      {error && <Alert severity="error" sx={{ mb: 2 }}>{error}</Alert>}
      {(loading || downloading) && <Box role="status" aria-live="polite" sx={{ mb: 2 }}>
        <Typography variant="body2">{loading ? 'Analysing conversation…' : 'Preparing reports…'}</Typography><LinearProgress />
      </Box>}
      <Tabs value={tab} onChange={(_event, value) => setTab(value)} aria-label="Analysis sections" sx={{ mb: 2 }}>
        {['Overview', 'Argumentation', 'Trust'].map((label, index) =>
          <Tab key={label} label={label} id={`analysis-tab-${index}`} aria-controls={`analysis-panel-${index}`} disabled={!results} />)}
      </Tabs>
      {results && <Box role="tabpanel" id={`analysis-panel-${tab}`} aria-labelledby={`analysis-tab-${tab}`} sx={{ minHeight: 280 }}>
        {tab === 0 && <>
          <Typography variant="body2" sx={{ mb: 2 }}>Messages are counted from each participant’s perspective. Interrupted replies and repetitions belong to messages received by the Author. Author facts and instructions are pre-knowledge.</Typography>
          <TableContainer><Table size="small" aria-label="Conversation overview">
            <TableHead><TableRow>{['Participant', 'Sent', 'Received', 'Interrupted replies', 'Facts', 'Instructions'].map(label => <TableCell key={label}>{label}</TableCell>)}</TableRow></TableHead>
            <TableBody>{results.overview.map(row => <TableRow key={row.participant}>
              <TableCell component="th" scope="row" sx={{ fontWeight: row.participant === 'Author' ? 'bold' : undefined }}>{row.participant}</TableCell>
              {[row.sent, row.received, row.interrupted, row.facts, row.instructions].map((value, index) => <TableCell key={index}>{value}</TableCell>)}
            </TableRow>)}</TableBody>
          </Table></TableContainer>
          <Typography sx={{ mt: 2 }}>Knowledge items: {results.knowledge.length} · Pre-knowledge: {results.knowledge.filter(item => item.preknowledge).length} · Repetitions: {results.repetitions}</Typography>
        </>}
        {tab === 1 && <>
          <Typography variant="body2" sx={{ mb: 2 }}>Rows are argument sources; columns are targets. Cells show attacks / supports. F means facts, I means instructions. Strong links count as one link; supplements are excluded from this matrix.</Typography>
          <TableContainer><Table size="small" aria-label="Argumentation matrix">
            <TableHead><TableRow><TableCell>Source → target</TableCell>{results.argumentHeaders.map(header => <TableCell key={header}>{header}</TableCell>)}</TableRow></TableHead>
            <TableBody>{results.arguments.map((row, index) => <TableRow key={results.argumentHeaders[index]}>
              <TableCell component="th" scope="row">{results.argumentHeaders[index]}</TableCell>
              {row.map((value, column) => <TableCell key={column} sx={{ color: value === '0/0' ? 'text.secondary' : 'text.primary', fontWeight: value === '0/0' ? undefined : 'bold' }}>{value}</TableCell>)}
            </TableRow>)}</TableBody>
          </Table></TableContainer>
        </>}
        {tab === 2 && <>
          <Stack direction="row" spacing={2} sx={{ mb: 2, flexWrap: 'wrap', gap: 1 }}>
            <FormControl size="small" sx={{ minWidth: 130 }}><InputLabel id="weight-label">Weight</InputLabel>
              <Select labelId="weight-label" label="Weight" value={weight} onChange={event => setWeight(Number(event.target.value))}>
                {Array.from({ length: 9 }, (_, i) => i + 2).map(value => <MenuItem key={value} value={value}>{value}</MenuItem>)}
              </Select>
            </FormControl>
            <FormControl size="small" sx={{ minWidth: 300 }}><InputLabel id="preset-label">Initial trust</InputLabel>
              <Select labelId="preset-label" label="Initial trust" value={preset} onChange={event => setPreset(event.target.value)}>
                {presets.map(([id, label]) => <MenuItem key={id} value={id}>{label}</MenuItem>)}
              </Select>
            </FormControl>
          </Stack>
          <Typography variant="body2" sx={{ mb: 2 }}>Author initial trust is 1.0. The LLM presets identify the participant named “LLM”. Computed scores range from −1 to 1; recorded ratings are shown separately. Changing these controls uses the same snapshot.</Typography>
          {results.knowledge.length === 0 ? <Typography>No knowledge to evaluate yet.</Typography> :
            <TableContainer sx={{ maxHeight: 400 }}><Table stickyHeader size="small" aria-label="Knowledge trust">
              <TableHead><TableRow>{['Knowledge', 'Participant', 'Type', 'Initial', 'Computed', 'Recorded immediately', 'Recorded afterwards'].map(label => <TableCell key={label}>{label}</TableCell>)}</TableRow></TableHead>
              <TableBody>{results.knowledge.map((item, index) => <TableRow key={index}>
                <TableCell component="th" scope="row" sx={{ minWidth: 240, maxWidth: 440, overflowWrap: 'anywhere' }}>{item.text || '(Untitled)'}</TableCell>
                <TableCell>{item.participant}</TableCell><TableCell>{item.preknowledge ? 'Pre-knowledge · ' : ''}{item.instruction ? 'Instruction' : 'Fact'}</TableCell>
                <TableCell>{score(scenario?.scores[index]?.initial ?? null)}</TableCell>
                <TableCell>{score(scenario?.scores[index]?.computed ?? null)}</TableCell>
                <TableCell>{score(item.recordedImmediately)}</TableCell><TableCell>{score(item.recordedAfterwards)}</TableCell>
              </TableRow>)}</TableBody>
            </Table></TableContainer>}
        </>}
      </Box>}
    </DialogContent>
    <DialogActions sx={{ px: 3, pb: 2, flexWrap: 'wrap' }}>
      <Button onClick={() => setRevision(value => value + 1)} disabled={loading || downloading}>Recalculate</Button>
      <Button variant="contained" startIcon={<DownloadIcon />} onClick={download} disabled={!analysis || loading || downloading}>Download reports ZIP</Button>
      <Button onClick={onClose}>Close</Button>
    </DialogActions>
  </Dialog>;
}
