package keml.analysis;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.javatuples.Pair;

import keml.Information;
import keml.NewInformation;
import keml.PreKnowledge;

public class WorkbookController {

	XSSFWorkbook wb;
	Sheet sheet;

	XSSFCellStyle defaultStyle;
	XSSFCellStyle headerStyle;
	XSSFCellStyle headerMessageStyle;
	XSSFCellStyle bigHeaderStyle;
	XSSFCellStyle floatStyle;
	XSSFCellStyle trustStyle;
	XSSFCellStyle distrustStyle;
	XSSFCellStyle neutTrustStyle;
	XSSFCellStyle instructionStyle;
	XSSFCellStyle factStyle;
	XSSFCellStyle origLLMStyle;
	XSSFCellStyle origOtherStyle;

	// data properties
	int firstFreeColumn = 0;
	private HashMap<Information, Integer> infoToRow;

	public WorkbookController() {

		infoToRow = new HashMap<>();

		wb = new XSSFWorkbook();
		sheet = wb.createSheet("Trust");
		// we need two rows we might merge:
		Row headers = sheet.createRow(0);
		Row headers1 = sheet.createRow(1);

		Cell start = headers.createCell(0);
		defaultStyle = (XSSFCellStyle) start.getCellStyle();
		defaultStyle.setAlignment(HorizontalAlignment.CENTER);

		// ************* headers *****************
		Font headerFont = wb.createFont();
		headerFont.setBold(true);
		headerStyle = wb.createCellStyle();
		headerStyle.setRotation((short) 90);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		// headerStyle.setFont(headerFont);

		headerMessageStyle = wb.createCellStyle();
		headerMessageStyle.setAlignment(HorizontalAlignment.LEFT);
		// headerMessageStyle.setFont(headerFont);

		bigHeaderStyle = wb.createCellStyle();
		bigHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
		Font bigHeaderFont = wb.createFont();
		// bigHeaderFont.setBold(true);
		bigHeaderFont.setFontHeight((short) 360);
		bigHeaderStyle.setFont(bigHeaderFont);

		start.setCellValue("Time");
		start.setCellStyle(headerStyle);
		headers1.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));

		Cell i = headers.createCell(1);
		i.setCellValue("Message");
		i.setCellStyle(headerMessageStyle);
		headers1.createCell(1);
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));

		i = headers.createCell(2);
		i.setCellValue("#Arg");
		i.setCellStyle(headerStyle);
		headers1.createCell(2);
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));

		i = headers.createCell(3);
		i.setCellValue("#Rep");
		i.setCellStyle(headerStyle);
		headers1.createCell(3);
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 3, 3));

		i = headers.createCell(4);
		i.setCellValue("fTi");
		i.setCellStyle(headerMessageStyle);
		headers1.createCell(4);
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 4, 4));

		i = headers.createCell(5);
		i.setCellValue("fTa");
		i.setCellStyle(headerMessageStyle);
		headers1.createCell(5);
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 5, 5));

		firstFreeColumn = 6;

		// *********** styles *******************

		CellStyle floatStyle = wb.createCellStyle();
		floatStyle.setDataFormat(wb.createDataFormat().getFormat("0.0#"));

		// additional color styles:
		// *************** Trust ****************
		trustStyle = wb.createCellStyle();
		trustStyle.setDataFormat(floatStyle.getDataFormat());
		trustStyle.setAlignment(HorizontalAlignment.CENTER);
		trustStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#339966"), null));
		trustStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// *************** Distrust *************
		distrustStyle = wb.createCellStyle();
		trustStyle.setDataFormat(floatStyle.getDataFormat());
		distrustStyle.setAlignment(HorizontalAlignment.CENTER);
		distrustStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#FF5F5F"), null));
		distrustStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// *************** neutral about trust *************
		neutTrustStyle = wb.createCellStyle();
		trustStyle.setDataFormat(floatStyle.getDataFormat());
		neutTrustStyle.setAlignment(HorizontalAlignment.CENTER);
		neutTrustStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		neutTrustStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// ************** isFact *************
		factStyle = wb.createCellStyle();
		factStyle.setAlignment(HorizontalAlignment.CENTER);
		factStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#99CC00"), null));
		factStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// ************* is Instruction **********
		instructionStyle = wb.createCellStyle();
		instructionStyle.setAlignment(HorizontalAlignment.CENTER);
		instructionStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#FFCC00"), null));
		instructionStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// *************** origin LLM style**************
		origLLMStyle = wb.createCellStyle();
		origLLMStyle.setAlignment(HorizontalAlignment.LEFT);
		origLLMStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#CCFFFF"), null));
		origLLMStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// *************** origin Other style**************
		origOtherStyle = wb.createCellStyle();
		origOtherStyle.setAlignment(HorizontalAlignment.LEFT);
		origOtherStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.decode("#FFFF99"), null));
		origOtherStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	}

	public void initialize(List<NewInformation> newInfos, List<PreKnowledge> preKnowledge) {
		int offset = 2; // adapted in loop
		for (int i = 0; i < preKnowledge.size(); i++) {
			PreKnowledge pre = preKnowledge.get(i);
			infoToRow.put(pre, offset);
			Row r = sheet.createRow(offset++);
			Cell t = r.createCell(0);
			t.setCellValue(-1);
			colorByIsInstruction(t, pre.isIsInstruction());
			Cell msg = r.createCell(1);
			msg.setCellValue(pre.getMessage());
			colorByOrigin(msg, false);
			r.createCell(2).setCellValue(pre.getTargetedBy().size());
			r.createCell(3).setCellValue(pre.getRepeatedBy().size());
			Float fTi = pre.getFeltTrustImmediately();
			Float fTa = pre.getFeltTrustAfterwards();
			float fTiCellValue = fTi != null ? fTi.floatValue() : Float.NaN;
			float fTaCellValue = fTa != null ? fTa.floatValue() : Float.NaN;
			setAndColorByValue(r.createCell(4), fTiCellValue);
			setAndColorByValue(r.createCell(5), fTaCellValue);
		}
		for (int i = 0; i < newInfos.size(); i++) {
			NewInformation info = newInfos.get(i);
			infoToRow.put(info, offset);
			Row r = sheet.createRow(offset++);
			Cell t = r.createCell(0);
			t.setCellValue(info.getTiming());
			colorByIsInstruction(t, info.isIsInstruction());
			Cell msg = r.createCell(1);
			msg.setCellValue(info.getMessage());
			colorByOrigin(msg, info.getSourceConversationPartner().getName().equals("LLM"));
			r.createCell(2).setCellValue(info.getTargetedBy().size());
			r.createCell(3).setCellValue(info.getRepeatedBy().size());
			Float fTi = info.getFeltTrustImmediately();
			Float fTa = info.getFeltTrustAfterwards();
			float fTiCellValue = fTi != null ? fTi.floatValue() : Float.NaN;
			float fTaCellValue = fTa != null ? fTa.floatValue() : Float.NaN;
			setAndColorByValue(r.createCell(4), fTiCellValue);
			setAndColorByValue(r.createCell(5), fTaCellValue);
		}
	}

	public void addTrusts(HashMap<Information, Pair<Float, Float>> trusts, String name) {
		Row headers0 = sheet.getRow(0);
		Cell i = headers0.createCell(firstFreeColumn);
		i.setCellValue(name);
		i.setCellStyle(bigHeaderStyle);
		i = headers0.createCell(firstFreeColumn + 1);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, firstFreeColumn, firstFreeColumn + 1));

		Row headers = sheet.getRow(1);

		i = headers.createCell(firstFreeColumn);
		i.setCellValue("iT");
		i.setCellStyle(headerMessageStyle);
		i = headers.createCell(firstFreeColumn + 1);
		i.setCellValue("T");
		i.setCellStyle(headerMessageStyle);

		trusts.forEach((info, scores) -> {
			int rowIndex = infoToRow.get(info);
			Row current = sheet.getRow(rowIndex);
			setAndColorByValue(current.createCell(firstFreeColumn), scores.getValue0());
			setAndColorByValue(current.createCell(firstFreeColumn + 1), scores.getValue1());
		});
		setBorderLeft(firstFreeColumn);

		firstFreeColumn += 2;
	}

	private void sizeColumns() {
		for (int i = 0; i < firstFreeColumn; i++) {
			sheet.autoSizeColumn(i);
		}
	}

	private void setBorderLeft(int columnIndex) {
		CellRangeAddress hdr = new CellRangeAddress(0, 2, columnIndex, columnIndex);
		RegionUtil.setBorderLeft(BorderStyle.MEDIUM, hdr, sheet);
		RegionUtil.setLeftBorderColor(IndexedColors.BLACK.getIndex(), hdr, sheet);
		// use messageMap to determine row count
		if (infoToRow.isEmpty()) {
			return;
		}
		CellRangeAddress adr = new CellRangeAddress(2, infoToRow.size() + 1, columnIndex, columnIndex);
		RegionUtil.setBorderLeft(BorderStyle.MEDIUM, adr, sheet);
		RegionUtil.setLeftBorderColor(IndexedColors.WHITE.getIndex(), adr, sheet);
	}

	private void colorByIsInstruction(Cell cell, boolean isInstruction) {
		if (isInstruction) {
			cell.setCellStyle(instructionStyle);
		} else {
			cell.setCellStyle(factStyle);
		}
	}

	private void colorByOrigin(Cell cell, boolean isLLM) {
		if (isLLM) {
			cell.setCellStyle(origLLMStyle);
		} else {
			cell.setCellStyle(origOtherStyle);
		}
	}

	private void setAndColorByValue(Cell cell, float value) {
		cell.setCellValue(value);
		if (value > 0.0f) {
			cell.setCellStyle(trustStyle);
		} else if (value < 0.0f) {
			cell.setCellStyle(distrustStyle);
		} else {
			cell.setCellStyle(neutTrustStyle);
		}
	}

	public void write(String file) throws IOException {
		sizeColumns();
		String path = FilenameUtils.removeExtension(file) + "-trust.xlsx";
		try (FileOutputStream o = new FileOutputStream(path)) {
			wb.write(o);
			wb.close();
		}
	}

}
