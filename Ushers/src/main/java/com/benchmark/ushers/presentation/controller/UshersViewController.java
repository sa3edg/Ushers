package com.benchmark.ushers.presentation.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.benchmark.ushers.dao.model.Area;
import com.benchmark.ushers.dao.model.Governorate;
import com.benchmark.ushers.dao.model.PreferredLocation;
import com.benchmark.ushers.dao.model.Product;
import com.benchmark.ushers.dao.model.ProjectType;
import com.benchmark.ushers.dao.model.UserRole;
import com.benchmark.ushers.dao.model.Usher;
import com.benchmark.ushers.dao.model.UsherReport;
import com.benchmark.ushers.reports.ReportStatementBase;
import com.benchmark.ushers.reports.UsherCodeReportStatement;
import com.benchmark.ushers.service.ReportService;
import com.benchmark.ushers.service.excel.ExcelExportHelperService;
import com.benchmark.ushers.service.excel.UshersExelExportService;

@Controller
public class UshersViewController extends AbstractViewController {

	//Parameters for reports
	private static final String VALUE_TYPE_PDF = "pdf";
	private static final String VALUE_TYPE_XLS = "xls";
	
	private static final Map<String, String> FILE_TYPE_2_CONTENT_TYPE = 
			new HashMap<String, String>();
	static {
		FILE_TYPE_2_CONTENT_TYPE.put(VALUE_TYPE_PDF, "application/pdf");
		FILE_TYPE_2_CONTENT_TYPE.put(VALUE_TYPE_XLS, "application/vnd.ms-excel");
	}

	@Autowired
	private ReportService reportService;
	
	@Autowired
	protected ExcelExportHelperService excelExportHelperService;
	
	@RequestMapping(value = "/ushers", method = RequestMethod.GET)
	public ModelAndView getGovernorates(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		if (request.isUserInRole(UserRole.ADMIN_ROLE) || request.isUserInRole(UserRole.SUPER_USER_ROLE) || request.isUserInRole(UserRole.USHER_ROLE)) {
			List<Usher> ushers = daoService.getUsherDaoImpl().findAll();
			model.addObject("ushers", ushers);
			model.setViewName("ushers/ushersData");
		}
		return model;

	}

	@RequestMapping(value = "/addUsherForm**", method = RequestMethod.GET)
	public ModelAndView addUsherForm() {
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("usher", new Usher());
		modelView.setViewName("ushers/addUsher");
		initModelList(modelView);
		return modelView;
	}

	@RequestMapping(value = "/viewUsher", method = RequestMethod.GET)
	public ModelAndView viewUsher(@RequestParam("id") String id) {
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("usher", daoService.getUsherDaoImpl().findOne(id));
		modelView.setViewName("ushers/usherProfile");
		initModelList(modelView);
		return modelView;
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/addUsher", method = RequestMethod.POST)
	public String addUsher(@ModelAttribute("usher") Usher usher,
			@RequestParam("file") MultipartFile[] files) throws Exception {

		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			byte[] bytes = file.getBytes();
			switch (i) {
			case 0:
				usher.setPhoto1(bytes);
				break;
			case 1:
				usher.setPhoto2(bytes);
				break;
			case 2:
				usher.setPhoto3(bytes);
				break;
			case 3:
				usher.setPhoto4(bytes);
				break;
			default:
			}
		}
		daoService.storeUsher(usher);
		return "redirect:/ushers";
	}
	@RequestMapping(value = "/importCSVForm**", method = RequestMethod.GET)
	public ModelAndView importCSVForm() {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("ushers/importUshers");
		initModelList(modelView);
		return modelView;
	}
	@RequestMapping(value = "/importCSV", method = RequestMethod.POST)
	public String importCSV(@RequestParam("file") MultipartFile file) throws Exception {
		importCSVService.importUsherCSVFile(file.getInputStream());
		return "redirect:/ushers";
	}
	
	@RequestMapping(value = "/editUsher**", method = RequestMethod.GET)
	public ModelAndView editUsher(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		Usher usher = daoService.getUsherDaoImpl().findOne(id);
		model.addObject("usher", usher);
		model.setViewName("ushers/addUsher");
		initModelList(model);
		return model;
	}
	
	@RequestMapping("/deleteUsher")
	public ModelAndView deleteUsher(@RequestParam("id") String id) {
		daoService.getUsherDaoImpl().delete(id);
		ModelAndView model = new ModelAndView();
		List<Usher> ushers = daoService.getUsherDaoImpl().findAll();
		model.addObject("ushers", ushers);
		model.setViewName("ushers/ushers");
		return model;
	}

	@RequestMapping(value = "/exportUshers", method = RequestMethod.GET)
	public ModelAndView getExcel() {
		List<Usher> ushers = daoService.getUsherDaoImpl().findAll();
		ModelAndView model = new ModelAndView();
		model.setViewName("ushersExcelView");
		model.addObject("ushers", ushers);
		model.addObject(UshersExelExportService.SERVICE_HELPER_KEY, excelExportHelperService);
		return model;
	}

	@RequestMapping(value = "/getUsherImage/{id}/{index}", method = RequestMethod.GET)
	public void getUsherImage(@PathVariable("id") String id,
			@PathVariable("index") String index, HttpServletResponse response)
			throws ServletException, IOException {
		Usher usher = daoService.getUsherDaoImpl().findOne(id);
		if (usher != null) {
			if ("0".equals(index) && usher.getPhoto1() != null) {
				writeImage(response, usher.getPhoto1());
			} else if ("1".equals(index) && usher.getPhoto2() != null) {
				writeImage(response, usher.getPhoto2());
			} else if ("2".equals(index) && usher.getPhoto3() != null) {
				writeImage(response, usher.getPhoto3());
			} else if ("3".equals(index) && usher.getPhoto4() != null) {
				writeImage(response, usher.getPhoto4());
			}
		}
	}

	@RequestMapping("/exportReport")
	public void exportUsher(@ModelAttribute("usherReport") UsherReport usherReport,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		
		String fileType = usherReport.getFileType();

		ReportStatementBase reportStatement = createReportStatement(usherReport);
		
		
		
		response.setContentType(FILE_TYPE_2_CONTENT_TYPE.get(fileType));
		OutputStream out = response.getOutputStream();
		try {
			JasperReportBuilder jrb = reportService.createJasperReport(reportStatement);
			
			if (VALUE_TYPE_PDF.equals(fileType)) {
				jrb.toPdf(out);
			} else if (VALUE_TYPE_XLS.equals(fileType)) {
				jrb.toExcelApiXls(out);
			}
		} catch (DRException e) {
			throw new ServletException(e);
		}
		out.close();
	}
	
	@RequestMapping(value = "/showReportForm**", method = RequestMethod.GET)
	public ModelAndView showReportForm() {
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("usherReport", new UsherReport());
		modelView.setViewName("ushers/usherReport");
		initReportForm(modelView);
		return modelView;
	}
	
	private void writeImage(HttpServletResponse response, byte[] image)
			throws IOException {
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.setContentLength(image.length);
		response.getOutputStream().write(image);
		response.getOutputStream().close();
		response.flushBuffer();
	}

	private void initModelList(ModelAndView model) {
		// add ushers types
		List<String> ushersTypes = new ArrayList<String>();
		ushersTypes.add("Instore");
		ushersTypes.add("Activiation");
		model.addObject("usherTypes", ushersTypes);

		// add ushers Caliber
		List<String> ushersCaliber = new ArrayList<String>();
		ushersCaliber.add("Smalls stores A");
		ushersCaliber.add("Small stores B");
		ushersCaliber.add("Hypermarket A");
		ushersCaliber.add("Hypermarket B");
		model.addObject("ushersCalibers", ushersCaliber);

		// add ushers marital Status
		List<String> maritalStatus = new ArrayList<String>();
		maritalStatus.add("Married");
		maritalStatus.add("Single");
		maritalStatus.add("Widow");
		maritalStatus.add("Divorced");
		model.addObject("maritalStatus", maritalStatus);

		// add ushers gender
		List<String> gender = new ArrayList<String>();
		gender.add("Male");
		gender.add("Female");
		model.addObject("gender", gender);

		// add ushers areas
		List<Area> areas = daoService.getAreaDaoImpl().findAll();
		model.addObject("areas", areas);

		// add ushers governorate
		List<Governorate> governorates = daoService.getGovernorateDaoImpl()
				.findAll();
		model.addObject("governorates", governorates);

		// add ushers preferred Locations
		List<PreferredLocation> preferredLocations = daoService
				.getPreferredLocationDaoImpl().findAll();
		model.addObject("preferredLocations", preferredLocations);

		// add ushers preferred Shifts
		List<String> preferredShift = new ArrayList<String>();
		preferredShift.add("Morning");
		preferredShift.add("Night");
		preferredShift.add("Between");
		model.addObject("preferredShifts", preferredShift);

		// add ushers preferred Shifts
		List<String> shirtSizes = new ArrayList<String>();
		shirtSizes.add("XS");
		shirtSizes.add("S");
		shirtSizes.add("M");
		shirtSizes.add("L");
		shirtSizes.add("XL");
		shirtSizes.add("XXL");
		shirtSizes.add("XXXL");
		model.addObject("shirtSizes", shirtSizes);

		// add ushers preferred Shifts
		List<String> pantsSizes = new ArrayList<String>();
		pantsSizes.add("XS");
		pantsSizes.add("S");
		pantsSizes.add("M");
		pantsSizes.add("L");
		pantsSizes.add("XL");
		pantsSizes.add("XXL");
		pantsSizes.add("XXXL");
		model.addObject("pantsSizes", pantsSizes);

		// add ushers preferred Shifts
		List<String> hairTypes = new ArrayList<String>();
		hairTypes.add("Normal");
		hairTypes.add("Good");
		hairTypes.add("Veiled");
		model.addObject("hairTypes", hairTypes);

		// add ushers languages
		List<String> languages = new ArrayList<String>();
		languages.add("English");
		languages.add("French");
		languages.add("German");
		languages.add("None");
		model.addObject("languages", languages);

		// add ushers preferred Shifts
		List<String> universityDegrees = new ArrayList<String>();
		universityDegrees.add("Graduate");
		universityDegrees.add("Ungraduate");
		model.addObject("universityDegrees", universityDegrees);

		List<String> socialInsurance = new ArrayList<String>();
		socialInsurance.add("Yes");
		socialInsurance.add("No");
		model.addObject("socialInsurance", socialInsurance);

		List<String> socialInsuranceForm6 = new ArrayList<String>();
		socialInsuranceForm6.add("Yes");
		socialInsuranceForm6.add("No");
		model.addObject("socialInsuranceForm6", socialInsuranceForm6);

		List<String> rates = new ArrayList<String>();
		rates.add("Poor");
		rates.add("Good");
		rates.add("Very good");
		rates.add("Excellent");
		model.addObject("rates", rates);
	}
	
	private void initReportForm(ModelAndView model) {
		// initialize file types
		List<String> fileTypes = new ArrayList<String>();
		fileTypes.add("xls");
		fileTypes.add("pdf");
		model.addObject("fileTypes", fileTypes);
		
		// add project products
		List<Product> products = daoService.getProductDaoImpl()
				.findAll();
		model.addObject("products", products);

		// add project types
		List<ProjectType> projectTypes = daoService
				.getProjectTypeDaoImpl().findAll();
		model.addObject("projectTypes", projectTypes);

		// initialize report criteria 
		initModelList(model);
	}
	
	private ReportStatementBase createReportStatement(UsherReport usherReport) {
		
		String usherCodeItemValue = usherReport.getUsherCode();
		String usherTypeItemValue = usherReport.getUsherType();
		String usherCaliberItemValue = usherReport.getUsherCaliber();
		String firstNameItemValue = usherReport.getFirstName();
		String middleNameItemValue = usherReport.getMiddleName();
		String lastNameItemValue = usherReport.getLastName();
		String genderItemValue = usherReport.getGender();
		String addressItemValue = usherReport.getAddress();
		String appartmentNumberItemValue = usherReport.getAppartmentNumber();
		String streetItemValue = usherReport.getStreet();
		String governorateItemValue = usherReport.getGovernorate();
		String preferredLocationItemValue = usherReport.getPreferredLocation();
		String languagesItemValue = usherReport.getLanguages();
		String universityItemValue = usherReport.getUniversity();
		String socialInsuranceNumberItemValue = usherReport.getSocialInsuranceNumber();
		Date socialInsuranceDateItemValue = usherReport.getSocialInsuranceDate();
//		String usherCodeItemValue = usherReport.getForm6();//TODO
		Date socialInsuranceExitDateItemValue = usherReport.getSocialInsuranceExitDate();
		String productIdItemValue = usherReport.getProductId();
		String projectCodeItemValue = usherReport.getProjectCode();
		String projectNameItemValue = usherReport.getProjectName();
		String projectTypeIdItemValue = usherReport.getProjectTypeId();
		
		ReportStatementBase reportStatement = null;
		
		if (usherCodeItemValue != null && usherCodeItemValue.equals(""))
		{
			reportStatement = new UsherCodeReportStatement(usherCodeItemValue); 
		}
		//TODO create more classes for the other reports 
		
		return reportStatement;
		
	}
}
