package com.benchmark.ushers.service.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.benchmark.ushers.dao.model.Usher;

public class UshersExelExportService extends AbstractExcelExportService{
	public static final String SERVICE_HELPER_KEY = "SERVICE_HELPER";
	private static final Logger logger = LoggerFactory.getLogger(UshersExelExportService.class);
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExcelExportHelperService excelExportHelperService = (ExcelExportHelperService) model.get(UshersExelExportService.SERVICE_HELPER_KEY);
		logger.debug("sheet name in export service class: " + excelExportHelperService.ushersSheetName);
		HSSFSheet excelSheet = workbook.createSheet(excelExportHelperService.ushersSheetName);
		excelSheet.setDefaultColumnWidth(AbstractExcelExportService.HEADER_CELL_WIDTH);
		// create style for header cells
		CellStyle style = createExcelHeaderStyle(workbook);
		// create header row
		setExcelHeader(excelSheet, style, excelExportHelperService);
		
		@SuppressWarnings("unchecked")
		List<Usher> ushersList = (List<Usher>) model.get("ushers");
		setExcelRows(excelSheet, ushersList);
	}

	public void setExcelHeader(HSSFSheet excelSheet, CellStyle style, ExcelExportHelperService excelExportHelperService) {
		HSSFRow excelHeader = excelSheet.createRow(0);
		excelHeader.createCell(0).setCellValue(excelExportHelperService.usherCode);
		excelHeader.getCell(0).setCellStyle(style);
		excelHeader.createCell(1).setCellValue(excelExportHelperService.usherType);
		excelHeader.getCell(1).setCellStyle(style);
		excelHeader.createCell(2).setCellValue(excelExportHelperService.usherCaliber);
		excelHeader.getCell(2).setCellStyle(style);
		excelHeader.createCell(3).setCellValue(excelExportHelperService.firstName);
		excelHeader.getCell(3).setCellStyle(style);
		excelHeader.createCell(4).setCellValue(excelExportHelperService.middleName);
		excelHeader.getCell(4).setCellStyle(style);
		excelHeader.createCell(5).setCellValue(excelExportHelperService.lastName);
		excelHeader.getCell(5).setCellStyle(style);
		excelHeader.createCell(6).setCellValue(excelExportHelperService.maritalStatus);
		excelHeader.getCell(6).setCellStyle(style);
		excelHeader.createCell(7).setCellValue(excelExportHelperService.hasKids);
		excelHeader.getCell(7).setCellStyle(style);
		excelHeader.createCell(8).setCellValue(excelExportHelperService.numberOfKids);
		excelHeader.getCell(8).setCellStyle(style);
		excelHeader.createCell(9).setCellValue(excelExportHelperService.gender);
		excelHeader.getCell(9).setCellStyle(style);
		excelHeader.createCell(10).setCellValue(excelExportHelperService.birthDate);
		excelHeader.getCell(10).setCellStyle(style);
		excelHeader.createCell(11).setCellValue(excelExportHelperService.age);
		excelHeader.getCell(11).setCellStyle(style);
		excelHeader.createCell(12).setCellValue(excelExportHelperService.address);
		excelHeader.getCell(12).setCellStyle(style);
		excelHeader.createCell(13).setCellValue(excelExportHelperService.appartmentNumber);
		excelHeader.getCell(13).setCellStyle(style);
		excelHeader.createCell(14).setCellValue(excelExportHelperService.street);
		excelHeader.getCell(14).setCellStyle(style);
		excelHeader.createCell(15).setCellValue(excelExportHelperService.area);
		excelHeader.getCell(15).setCellStyle(style);
		excelHeader.createCell(16).setCellValue(excelExportHelperService.governorate);
		excelHeader.getCell(16).setCellStyle(style);
		excelHeader.createCell(17).setCellValue(excelExportHelperService.preferredLocation);
		excelHeader.getCell(17).setCellStyle(style);
		excelHeader.createCell(18).setCellValue(excelExportHelperService.preferredShift);
		excelHeader.getCell(18).setCellStyle(style);
		excelHeader.createCell(19).setCellValue(excelExportHelperService.mobileNumber);
		excelHeader.getCell(19).setCellStyle(style);
		excelHeader.createCell(20).setCellValue(excelExportHelperService.landlineNumber);
		excelHeader.getCell(20).setCellStyle(style);
		excelHeader.createCell(21).setCellValue(excelExportHelperService.height);
		excelHeader.getCell(21).setCellStyle(style);
		excelHeader.createCell(22).setCellValue(excelExportHelperService.weight);
		excelHeader.getCell(22).setCellStyle(style);
		excelHeader.createCell(23).setCellValue(excelExportHelperService.shirtSize);
		excelHeader.getCell(23).setCellStyle(style);
		excelHeader.createCell(24).setCellValue(excelExportHelperService.pantsSize);
		excelHeader.getCell(24).setCellStyle(style);
		excelHeader.createCell(25).setCellValue(excelExportHelperService.hairType);
		excelHeader.getCell(25).setCellStyle(style);
		excelHeader.createCell(26).setCellValue(excelExportHelperService.languages);
		excelHeader.getCell(26).setCellStyle(style);
		excelHeader.createCell(27).setCellValue(excelExportHelperService.referredBy);
		excelHeader.getCell(27).setCellStyle(style);
		excelHeader.createCell(28).setCellValue(excelExportHelperService.university);
		excelHeader.getCell(28).setCellStyle(style);
		excelHeader.createCell(29).setCellValue(excelExportHelperService.universityDegree);
		excelHeader.getCell(29).setCellStyle(style);
		excelHeader.createCell(30).setCellValue(excelExportHelperService.graduationYear);
		excelHeader.getCell(30).setCellStyle(style);
		excelHeader.createCell(31).setCellValue(excelExportHelperService.school);
		excelHeader.getCell(31).setCellStyle(style);
		excelHeader.createCell(32).setCellValue(excelExportHelperService.facebookAccount);
		excelHeader.getCell(32).setCellStyle(style);
		excelHeader.createCell(33).setCellValue(excelExportHelperService.emailAddress);
		excelHeader.getCell(33).setCellStyle(style);
		excelHeader.createCell(34).setCellValue(excelExportHelperService.socialInsurance);
		excelHeader.getCell(34).setCellStyle(style);
		excelHeader.createCell(35).setCellValue(excelExportHelperService.socialInsuranceNumber);
		excelHeader.getCell(35).setCellStyle(style);
		excelHeader.createCell(36).setCellValue(excelExportHelperService.socialInsuranceDate);
		excelHeader.getCell(36).setCellStyle(style);
		excelHeader.createCell(37).setCellValue(excelExportHelperService.socialInsuranceForm6);
		excelHeader.getCell(37).setCellStyle(style);
		excelHeader.createCell(38).setCellValue(excelExportHelperService.socialInsuranceExitDate);
		excelHeader.getCell(38).setCellStyle(style);
		excelHeader.createCell(39).setCellValue(excelExportHelperService.nationalIdNumber);
		excelHeader.getCell(39).setCellStyle(style);
		excelHeader.createCell(40).setCellValue(excelExportHelperService.additionalInformation);
		excelHeader.getCell(40).setCellStyle(style);
		excelHeader.createCell(41).setCellValue(excelExportHelperService.rate);
		excelHeader.getCell(41).setCellStyle(style);
		
		
	}
	
	public void setExcelRows(HSSFSheet excelSheet, List<Usher> ushersList){
		int record = 1;
		for (Usher usher : ushersList) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			excelRow.createCell(0).setCellValue(usher.getUsherCode());
			excelRow.createCell(1).setCellValue(usher.getUsherType());
			excelRow.createCell(2).setCellValue(usher.getUsherCaliber());
			excelRow.createCell(3).setCellValue(usher.getFirstName());
			excelRow.createCell(4).setCellValue(usher.getMiddleName());
			excelRow.createCell(5).setCellValue(usher.getLastName());
			excelRow.createCell(6).setCellValue(usher.getMaritalStatus());
			excelRow.createCell(7).setCellValue(usher.isHasKids());
			excelRow.createCell(8).setCellValue(usher.getNumberOfKids());
			excelRow.createCell(9).setCellValue(usher.getGender());
			if(usher.getBirthDate() != null){
				excelRow.createCell(10).setCellValue(usher.getBirthDate());
			}else{
				excelRow.createCell(10).setCellValue(StringUtils.EMPTY);
			}
			excelRow.createCell(11).setCellValue(usher.getAge());
			excelRow.createCell(12).setCellValue(usher.getAddress());
			excelRow.createCell(13).setCellValue(usher.getAppartmentNumber());
			excelRow.createCell(14).setCellValue(usher.getStreet());
			excelRow.createCell(15).setCellValue(usher.getArea());
			excelRow.createCell(16).setCellValue(usher.getGovernorate());
			excelRow.createCell(17).setCellValue(usher.getPreferredLocation());
			excelRow.createCell(18).setCellValue(usher.getPreferredShift());
			excelRow.createCell(19).setCellValue(usher.getMobileNumber());
			excelRow.createCell(20).setCellValue(usher.getLandlineNumber());
			excelRow.createCell(21).setCellValue(usher.getHeight());
			excelRow.createCell(22).setCellValue(usher.getWeight());
			excelRow.createCell(23).setCellValue(usher.getShirtSize());
			excelRow.createCell(24).setCellValue(usher.getPantsSize());
			excelRow.createCell(25).setCellValue(usher.getHairType());
			excelRow.createCell(26).setCellValue(usher.getLanguages());
			excelRow.createCell(27).setCellValue(usher.getReferredBy());
			excelRow.createCell(28).setCellValue(usher.getUniversity());
			excelRow.createCell(29).setCellValue(usher.getUniversityDegree());
			excelRow.createCell(30).setCellValue(usher.getGraduationYear());
			excelRow.createCell(31).setCellValue(usher.getSchool());
			excelRow.createCell(32).setCellValue(usher.getFacebookAccount());
			excelRow.createCell(33).setCellValue(usher.getEmailAddress());
			excelRow.createCell(34).setCellValue(usher.isSocialInsurance());
			excelRow.createCell(35).setCellValue(usher.getSocialInsuranceNumber());
			if(usher.getSocialInsuranceDate() != null){
				excelRow.createCell(36).setCellValue(usher.getSocialInsuranceDate());
			}else{
				excelRow.createCell(36).setCellValue(StringUtils.EMPTY);
			}
			excelRow.createCell(37).setCellValue(usher.isSocialInsuranceForm6());
			if(usher.getSocialInsuranceExitDate() != null){
				excelRow.createCell(38).setCellValue(usher.getSocialInsuranceExitDate());
			}else{
				excelRow.createCell(38).setCellValue(StringUtils.EMPTY);
			}
			excelRow.createCell(39).setCellValue(usher.getNationalIdNumber());
			excelRow.createCell(40).setCellValue(usher.getAdditionalInformation());
			excelRow.createCell(41).setCellValue(usher.getRate());
		}
	}

}
