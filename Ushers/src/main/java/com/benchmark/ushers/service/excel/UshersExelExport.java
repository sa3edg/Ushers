package com.benchmark.ushers.service.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.benchmark.ushers.dao.model.Usher;

public class UshersExelExport extends AbstractExcelExporter{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HSSFSheet excelSheet = workbook.createSheet("ushers");
		setExcelHeader(excelSheet);
		
		List<Usher> ushersList = (List<Usher>) model.get("ushers");
		setExcelRows(excelSheet, ushersList);
	}

	public void setExcelHeader(HSSFSheet excelSheet) {
		HSSFRow excelHeader = excelSheet.createRow(0);
		excelHeader.createCell(0).setCellValue("Id");
		excelHeader.createCell(4).setCellValue("Weight");
	}
	
	public void setExcelRows(HSSFSheet excelSheet, List<Usher> ushersList){
		int record = 1;
		for (Usher animal : ushersList) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			excelRow.createCell(0).setCellValue(animal.getId());
			excelRow.createCell(4).setCellValue(animal.getWeight());
		}
	}

}
