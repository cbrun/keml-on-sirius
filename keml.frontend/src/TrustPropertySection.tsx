import { gql, useMutation } from '@apollo/client';
import { GQLTextfield, GQLWidget, PropertySectionComponentProps, PropertySectionLabel, WidgetContribution } from '@eclipse-sirius/sirius-components-forms';
import ClearIcon from '@mui/icons-material/Clear';
import TuneIcon from '@mui/icons-material/Tune';
import { Box, FormHelperText, IconButton, InputAdornment, TextField, Tooltip } from '@mui/material';
import { useEffect, useRef, useState } from 'react';

// Keep the native textfield protocol: the Java form description owns validation
// and model updates, and Sirius sends refreshed values through its subscription.
const editTrustMutation = gql`
  mutation editTrust($input: EditTextfieldInput!) {
    editTextfield(input: $input) {
      __typename
      ... on SuccessPayload { messages { body level } }
      ... on ErrorPayload { messages { body level } }
    }
  }
`;

const isTrustWidget = (widget: GQLWidget): widget is GQLTextfield =>
  widget.__typename === 'Textfield' && widget.id.startsWith('keml-trust:');

export const trustWidgetContribution: WidgetContribution = {
  name: 'KEML trust score',
  icon: <TuneIcon />,
  previewComponent: () => null,
  component: (widget) => isTrustWidget(widget) ? TrustPropertySection : null,
};

export function TrustPropertySection({ editingContextId, formId, widget, readOnly }: PropertySectionComponentProps<GQLWidget>) {
  if (!isTrustWidget(widget)) return null;
  return <TrustInput editingContextId={editingContextId} formId={formId} widget={widget} readOnly={readOnly} />;
}

function TrustInput({ editingContextId, formId, widget, readOnly }: PropertySectionComponentProps<GQLTextfield>) {
  const [value, setValue] = useState(widget.stringValue);
  const [badInput, setBadInput] = useState(false);
  const [error, setError] = useState('');
  const focused = useRef(false);
  const saving = useRef(false);
  const rangeChanged = useRef(false);
  const [editTrust, { loading }] = useMutation(editTrustMutation);
  const disabled = readOnly || widget.readOnly || loading;
  const unset = value.trim() === '';
  const number = Number(value);
  const invalid = badInput || !unset && (!Number.isFinite(number) || number < -1 || number > 1);
  const messageId = `${widget.id}-message`;

  useEffect(() => {
    // A refresh from another editor must not erase a number being typed here.
    if (!focused.current && !saving.current) {
      setValue(widget.stringValue);
      setBadInput(false);
      setError('');
    }
  }, [widget.stringValue]);

  async function save(text: string, malformed = false) {
    const score = Number(text);
    if (disabled || saving.current || malformed || text.trim() !== '' && (!Number.isFinite(score) || score < -1 || score > 1)) return;
    if (text === widget.stringValue) return;
    saving.current = true;
    setError('');
    try {
      const result = await editTrust({ variables: { input: {
        id: crypto.randomUUID(), editingContextId, representationId: formId, textfieldId: widget.id, newValue: text,
      } } });
      const payload = result.data?.editTextfield;
      if (payload?.__typename !== 'SuccessPayload') {
        setError(payload?.messages?.map((message: { body: string }) => message.body).join(' ') || 'Could not save trust. Try again.');
      }
    } catch (failure) {
      setError(failure instanceof Error ? failure.message : 'Could not save trust. Try again.');
    } finally {
      saving.current = false;
    }
  }

  return <Box className="keml-trust-property" sx={{ mb: 2 }}>
    <PropertySectionLabel editingContextId={editingContextId} formId={formId} widget={widget} />
    <Box sx={{ display: 'flex', alignItems: 'center', gap: 2, mt: 1 }}>
      <Box sx={{ flex: 1, minWidth: 0 }}>
        <input className={`keml-trust-range${unset ? ' keml-trust-unset' : ''}`}
          type="range" min="-1" max="1" step="0.1" disabled={disabled}
          value={unset || !Number.isFinite(number) ? 0 : Math.max(-1, Math.min(1, number))}
          aria-label={`${widget.label} slider`} aria-valuetext={unset ? 'Unassessed' : value}
          aria-describedby={messageId}
          onFocus={() => { focused.current = true; }}
          onChange={(event) => {
            rangeChanged.current = true;
            setValue(event.currentTarget.value); setBadInput(false); setError('');
          }}
          onPointerUp={(event) => {
            if (disabled || event.button !== 0) return;
            rangeChanged.current = false;
            setValue(event.currentTarget.value); setBadInput(false); setError('');
            void save(event.currentTarget.value);
          }}
          onKeyUp={(event) => {
            if (rangeChanged.current && ['ArrowLeft', 'ArrowRight', 'ArrowUp', 'ArrowDown', 'Home', 'End', 'PageUp', 'PageDown'].includes(event.key)) {
              rangeChanged.current = false; void save(event.currentTarget.value);
            }
          }}
          onBlur={(event) => {
            focused.current = false;
            if (rangeChanged.current) { rangeChanged.current = false; void save(event.currentTarget.value); }
          }} />
        <Box aria-hidden="true" sx={{ display: 'flex', justifyContent: 'space-between', fontSize: '0.75rem', color: 'text.secondary' }}>
          <span>−1</span><span>0</span><span>+1</span>
        </Box>
      </Box>
      <TextField type="number" size="small" value={value} disabled={disabled} error={invalid || !!error}
        placeholder="Unset" sx={{ width: 112, flexShrink: 0 }}
        slotProps={{ htmlInput: { min: -1, max: 1, step: 'any', 'aria-label': widget.label, 'aria-describedby': messageId },
          input: { endAdornment: <InputAdornment position="end">
            <Tooltip title="Clear trust score"><span><IconButton size="small" aria-label={`Clear ${widget.label}`}
              disabled={disabled || unset && !badInput} onPointerDown={(event) => event.preventDefault()} onClick={() => {
                setValue(''); setBadInput(false); setError(''); void save('');
              }}><ClearIcon fontSize="small" /></IconButton></span></Tooltip>
          </InputAdornment> } }}
        onFocus={() => { focused.current = true; }}
        onChange={(event) => {
          setValue(event.target.value); setBadInput((event.target as HTMLInputElement).validity.badInput); setError('');
        }}
        onBlur={() => { focused.current = false; void save(value, badInput); }}
        onKeyDown={(event) => { if (event.key === 'Enter') { event.preventDefault(); void save(value, badInput); } }} />
    </Box>
    <FormHelperText id={messageId} error={invalid || !!error || widget.diagnostics.some(diagnostic => diagnostic.kind === 'ERROR')} aria-live="polite">
      {invalid ? 'Trust must be a finite number between −1 and +1.' : error || widget.diagnostics.map(diagnostic => diagnostic.message).join(' ') || (unset ? 'Unassessed — choose a score or type a number.' : '0 is neutral. Clear the number to leave trust unassessed.')}
    </FormHelperText>
  </Box>;
}
