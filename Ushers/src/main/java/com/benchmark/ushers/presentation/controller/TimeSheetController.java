package com.benchmark.ushers.presentation.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;











import javax.servlet.http.HttpServletResponse;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//import ar.com.fdvs.dj.core.DynamicJasperHelper;
//import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
//import ar.com.fdvs.dj.domain.DynamicReport;
//import ar.com.fdvs.dj.domain.builders.ReflectiveReportBuilder;




import com.benchmark.ushers.dao.model.Usher;
import com.benchmark.ushers.dao.model.UsherTimeSheet;
import com.benchmark.ushers.dao.model.Client;
import com.benchmark.ushers.dao.model.ProjectLocation;
import com.benchmark.ushers.dao.model.PreferredLocation;
import com.benchmark.ushers.dao.model.Product;
import com.benchmark.ushers.dao.model.Project;
import com.benchmark.ushers.dao.model.ProjectType;
import com.benchmark.ushers.dao.model.UserRole;
import com.benchmark.ushers.dao.model.UsherTimeSheetReport;
import com.benchmark.ushers.dao.model.UsherTimeSheetReportCriteria;
import com.benchmark.ushers.reports.ReportStatementBase;

@Controller
public class TimeSheetController extends AbstractViewController {

	@RequestMapping(value = "/usherTimeSheets", method = RequestMethod.GET)
	public ModelAndView getTimeShets(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		List<UsherTimeSheet> UsherTimeSheets = daoService
				.getUsherTimeSheetDaoImpl().findAll();
		model.addObject("usherTimeSheets", UsherTimeSheets);
		model.setViewName("timeSheet/UsherTimeSheets");
		return model;

	}

	@RequestMapping(value = "/addUsherTimeSheetForm**", method = RequestMethod.GET)
	public ModelAndView addUsherTimeSheetForm() {
		ModelAndView model = new ModelAndView();
		model.addObject("UsherTimeSheet", new UsherTimeSheet());
		model.setViewName("timeSheet/addUsherTimeSheet");
		initUsherTimeSheet(model);
		return model;
	}

	@RequestMapping(value = "/addUsherTimeSheet", method = RequestMethod.POST)
	public String addUsherTimeSheet(
			@ModelAttribute("UsherTimeSheet") UsherTimeSheet UsherTimeSheet) {
		daoService.getUsherTimeSheetDaoImpl().save(UsherTimeSheet);
		return "redirect:/usherTimeSheets";
	}

	@RequestMapping(value = "/editUsherTimeSheet**", method = RequestMethod.GET)
	public ModelAndView editUsherTimeSheet(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		UsherTimeSheet UsherTimeSheet = daoService.getUsherTimeSheetDaoImpl()
				.findOne(new Integer(id));
		model.addObject("UsherTimeSheet", UsherTimeSheet);
		model.setViewName("timeSheet/addUsherTimeSheet");
		return model;
	}

	@RequestMapping("/deleteUsherTimeSheet")
	public ModelAndView deleteUsherTimeSheet(@RequestParam("id") String id) {
		daoService.getUsherTimeSheetDaoImpl().delete(new Integer(id));
		ModelAndView model = new ModelAndView();
		List<UsherTimeSheet> UsherTimeSheets = daoService
				.getUsherTimeSheetDaoImpl().findAll();
		model.addObject("usherTimeSheets", UsherTimeSheets);
		model.setViewName("timeSheet/usherTimeSheets");
		return model;
	}

	private void initUsherTimeSheet(ModelAndView model) {
		// add projects Locations
		List<ProjectLocation> projectsLocations = daoService.getProjectLocationDaoImpl().findAll();
		model.addObject("projectsLocations", projectsLocations);

		// add projects
		List<Project> projects = daoService.getProjectDaoImpl().findAll();
		model.addObject("projects", projects);

		// add ushers
		List<Usher> ushers = daoService.getUsherDaoImpl()
				.findAll();
		model.addObject("ushers", ushers);
		
		List<String> uniform = new ArrayList<String>();
		uniform.add("No");
		uniform.add("Yes");
		model.addObject("uniform", uniform);
	}
	
	@RequestMapping(value = "/showUsherTimeSheetCriteria", method = RequestMethod.GET)
	public ModelAndView showUsherTimeSheetCriteria() {
		ModelAndView model = new ModelAndView();
		model.addObject("UsherTimeSheetCriteria", new UsherTimeSheetReportCriteria());
		model.setViewName("timeSheet/usherTimeSheetReportCriteria");
		initUsherTimeSheetCriteria(model);
		return model;
	}
	
	private void initUsherTimeSheetCriteria(ModelAndView model) {
		List<String> types = new ArrayList<String>();
		types.add("Smalls stores");
		types.add("Hypermarket");
		model.addObject("types", types);

		// add projects
		List<Project> projects = daoService.getProjectDaoImpl().findAll();
		model.addObject("projects", projects);

		// add dates
		List<Date> dates = daoService.getUsherTimeSheetDaoImpl()
				.getUsherTimeSheetDates();
		model.addObject("dates", dates);
	}
	
	@RequestMapping(value = "/handleUsherTimeSheetReportActions", method = RequestMethod.POST, params = { "showReportPopupParam"})
	@ResponseBody
	public void usherTimeSheetReportPopUp(
			@ModelAttribute("UsherTimeSheetCriteria") UsherTimeSheetReportCriteria UsherTimeSheet,  HttpServletResponse response) throws Exception{
//		ModelAndView model = new ModelAndView();
//		if("showReport".equals(target)){
		List<UsherTimeSheetReport> items = new ArrayList<UsherTimeSheetReport>();
		UsherTimeSheetReport report = new UsherTimeSheetReport();
		report.setProjectName("Sample1" + UsherTimeSheet.getFromDate());
		report.setUsherName("Said gamal said");
		items.add(report);
//		
//		model.addObject("items", items);
//		model.setViewName("timeSheet/usherTimeSheetReport");
		createJasperReport(items, response);
//		return "timeSheet/usherTimeSheetReport";
//		}else{
//			List<UsherTimeSheetReport> items = new ArrayList<UsherTimeSheetReport>();
//			UsherTimeSheetReport report = new UsherTimeSheetReport();
//			report.setProjectName("Sample1" + UsherTimeSheet.getFromDate());
//			report.setUsherName("Said gamal said");
//			items.add(report);
//			exportUsherTimeSheetReport(items, response);
//			return "timeSheet/usherTimeSheetReport";
//		}
	}
	
	@RequestMapping(value = "/handleUsherTimeSheetReportActions", method = RequestMethod.POST, params = { "showReportNewWindowParam"})
	@ResponseBody
	public void usherTimeSheetReportNewWindow(
			@ModelAttribute("UsherTimeSheetCriteria") UsherTimeSheetReportCriteria UsherTimeSheet, HttpServletResponse response) throws Exception{
//		ModelAndView model = new ModelAndView();
//		if("showReport".equals(target)){
		List<UsherTimeSheetReport> items = new ArrayList<UsherTimeSheetReport>();
		UsherTimeSheetReport report = new UsherTimeSheetReport();
		report.setProjectName("Sample1" + UsherTimeSheet.getFromDate());
		report.setUsherName("Said gamal said");
		items.add(report);
//		
//		model.addObject("items", items);
//		model.setViewName("timeSheet/usherTimeSheetReport");
		createJasperReport(items, response);
//		return "timeSheet/usherTimeSheetReport";
//		}else{
//			List<UsherTimeSheetReport> items = new ArrayList<UsherTimeSheetReport>();
//			UsherTimeSheetReport report = new UsherTimeSheetReport();
//			report.setProjectName("Sample1" + UsherTimeSheet.getFromDate());
//			report.setUsherName("Said gamal said");
//			items.add(report);
//			exportUsherTimeSheetReport(items, response);
//			return "timeSheet/usherTimeSheetReport";
//		}
	}
	
	@RequestMapping(value = "/handleUsherTimeSheetReportActions", method = RequestMethod.POST, params = { "exportReportToPDF"})
	@ResponseBody
	public String exportUsherTimeSheetReportToPDF(
			@ModelAttribute("UsherTimeSheetCriteria") UsherTimeSheetReportCriteria UsherTimeSheet, HttpServletResponse response) throws Exception{

			List<UsherTimeSheetReport> items = new ArrayList<UsherTimeSheetReport>();
			UsherTimeSheetReport report = new UsherTimeSheetReport();
			report.setProjectName("Sample1" + UsherTimeSheet.getFromDate());
			report.setUsherName("Said gamal said");
			items.add(report);
			exportUsherTimeSheetReport(items, response);
			return "timeSheet/usherTimeSheetReport";
	}
//	@RequestMapping(value = "/createUsherTimeSheetReport", method = RequestMethod.GET)
////	@RequestMapping(value = "/createUsherTimeSheetReport", method = RequestMethod.POST)
//	public String createUsherTimeSheetReport(
//			@ModelAttribute("UsherTimeSheetCriteria") UsherTimeSheetReportCriteria UsherTimeSheet, HttpServletResponse response) throws Exception{
////		ModelAndView model = new ModelAndView();
////		List<UsherTimeSheetReport> items = new ArrayList<UsherTimeSheetReport>();
////		UsherTimeSheetReport report = new UsherTimeSheetReport();
////		report.setProjectName("Sample1");
////		report.setUsherName("Said gamal said");
////		items.add(report);
////		//createJasperReport(items, response);
////		model.addObject("items", items);
////		model.setViewName("timeSheet/usherTimeSheetReport");
//		return "timeSheet/usherTimeSheetReport";
//	}
	
	public void createJasperReport(List<UsherTimeSheetReport> items, HttpServletResponse response) throws Exception
	{
		
//		DynamicReport dynamicReport = new ReflectiveReportBuilder(items).build();
//        dynamicReport.setTitle("List of Employees");
//        JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), items);
//        JasperViewer.viewReport(jasperPrint);
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream()); 
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(items);
		JasperReportBuilder report = DynamicReports.report();//a new report
		
		report
		  .columns(
		      Columns.column("First Name", "projectName" ,DataTypes.stringType()),
		      Columns.column("Last Name", "usherName", DataTypes.stringType()))//,
//		      Columns.column("Date", "date", DataTypes.dateType()))
		  .title(//title of the report
		      Components.text("SimpleReportExample")
			  .setHorizontalAlignment(HorizontalAlignment.CENTER))
			  .pageFooter(Components.pageXofY())//show page number on the page footer
			  .setDataSource(JRdataSource);
//		report.show();
		//report.toPdf(response.getOutputStream());
		ServletOutputStream outStream = response.getOutputStream();
		report.toHtml(outStream);
		outStream.flush();
		outStream.close();
//		return;
		//report.print();
	}
	
	public void exportUsherTimeSheetReport(List<UsherTimeSheetReport> items, HttpServletResponse response) throws Exception
	{
		
//		DynamicReport dynamicReport = new ReflectiveReportBuilder(items).build();
//        dynamicReport.setTitle("List of Employees");
//        JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), items);
//        JasperViewer.viewReport(jasperPrint);
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream()); 
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(items);
		JasperReportBuilder report = DynamicReports.report();//a new report
		
		report
		  .columns(
		      Columns.column("First Name", "projectName" ,DataTypes.stringType()),
		      Columns.column("Last Name", "usherName", DataTypes.stringType()))//,
//		      Columns.column("Date", "date", DataTypes.dateType()))
		  .title(//title of the report
		      Components.text("SimpleReportExample")
			  .setHorizontalAlignment(HorizontalAlignment.CENTER))
			  .pageFooter(Components.pageXofY())//show page number on the page footer
			  .setDataSource(JRdataSource);
//		report.show();
		String title = "myReport";
		response.setHeader("Content-Disposition", "attachment;filename="+title+".pdf");
		response.setContentType("application/pdf");
		ServletOutputStream outStream = response.getOutputStream();
		report.toPdf(outStream);
		outStream.flush();
		outStream.close();
//		return;
		//report.print();
	}

	// @RequestMapping(value = "/preferredLocations", method =
	// RequestMethod.GET)
	// public ModelAndView getPreferredLocations(HttpServletRequest request) {
	// ModelAndView model = new ModelAndView();
	// if (request.isUserInRole(UserRole.DATA_ENTRY_ROLE)) {
	// List<PreferredLocation> UsherTimeSheets =
	// daoService.getPreferredLocationDaoImpl().findAll();
	// model.addObject("locations", UsherTimeSheets);
	// model.setViewName("dataEnttry/preferredLocations");
	// }
	// return model;
	//
	// }
	//
	// @RequestMapping(value = "/addPreferredLocationsForm**", method =
	// RequestMethod.GET)
	// public ModelAndView addPreferredLocationsForm() {
	// ModelAndView model = new ModelAndView();
	// model.addObject("location", new PreferredLocation());
	// model.setViewName("dataEnttry/addPreferredLocation");
	// return model;
	// }
	//
	// @RequestMapping(value ="/addPreferredLocations", method =
	// RequestMethod.POST)
	// public String addPreferredLocations(@ModelAttribute("UsherTimeSheet")
	// PreferredLocation UsherTimeSheet) {
	// daoService.getPreferredLocationDaoImpl().save(UsherTimeSheet);
	// return "redirect:/preferredLocations";
	// }
	//
	// @RequestMapping(value = "/editPreferredLocations**", method =
	// RequestMethod.GET)
	// public ModelAndView editPreferredLocations(@RequestParam("id") String id)
	// {
	// ModelAndView model = new ModelAndView();
	// PreferredLocation location =
	// daoService.getPreferredLocationDaoImpl().findOne(new Integer(id));
	// model.addObject("location", location);
	// model.setViewName("dataEnttry/addPreferredLocation");
	// return model;
	// }
	//
	// @RequestMapping("/deletePreferredLocations")
	// public ModelAndView deletePreferredLocations(@RequestParam("id") String
	// id) {
	// daoService.getPreferredLocationDaoImpl().delete(new Integer(id));
	// ModelAndView model = new ModelAndView();
	// List<PreferredLocation> locations =
	// daoService.getPreferredLocationDaoImpl().findAll();
	// model.addObject("locations", locations);
	// model.setViewName("dataEnttry/preferredLocations");
	// return model;
	// }
	//
	// @RequestMapping(value = "/clients", method = RequestMethod.GET)
	// public ModelAndView getClients(HttpServletRequest request) {
	// ModelAndView model = new ModelAndView();
	// List<Client> UsherTimeSheets = daoService.getClientDaoImpl().findAll();
	// model.addObject("clients", UsherTimeSheets);
	// model.setViewName("dataEnttry/clients");
	//
	// return model;
	//
	// }
	//
	// @RequestMapping(value = "/addClientForm**", method = RequestMethod.GET)
	// public ModelAndView addClientForm() {
	// ModelAndView model = new ModelAndView();
	// model.addObject("client", new Client());
	// model.setViewName("dataEnttry/addClient");
	// return model;
	// }
	//
	// @RequestMapping(value ="/addClient", method = RequestMethod.POST)
	// public String addClient(@ModelAttribute("client") Client client) {
	// daoService.getClientDaoImpl().save(client);
	// return "redirect:/clients";
	// }
	//
	// @RequestMapping(value = "/editClient**", method = RequestMethod.GET)
	// public ModelAndView editClient(@RequestParam("id") String id) {
	// ModelAndView model = new ModelAndView();
	// Client client = daoService.getClientDaoImpl().findOne(new Integer(id));
	// model.addObject("client", client);
	// model.setViewName("dataEnttry/addClient");
	// return model;
	// }
	//
	// @RequestMapping("/deleteClient")
	// public ModelAndView deleteClient(@RequestParam("id") String id) {
	// daoService.getClientDaoImpl().delete(new Integer(id));
	// ModelAndView model = new ModelAndView();
	// List<Client> UsherTimeSheets = daoService.getClientDaoImpl().findAll();
	// model.addObject("clients", UsherTimeSheets);
	// model.setViewName("dataEnttry/clients");
	// return model;
	// }
	//
	// @RequestMapping(value = "/products", method = RequestMethod.GET)
	// public ModelAndView getProducts(HttpServletRequest request) {
	// ModelAndView model = new ModelAndView();
	// List<Product> UsherTimeSheets = daoService.getProductDaoImpl().findAll();
	// model.addObject("products", UsherTimeSheets);
	// model.setViewName("dataEnttry/products");
	// return model;
	//
	// }
	//
	// @RequestMapping(value = "/addProductForm**", method = RequestMethod.GET)
	// public ModelAndView addProductForm() {
	// ModelAndView model = new ModelAndView();
	// model.addObject("product", new Product());
	// model.setViewName("dataEnttry/addProduct");
	// return model;
	// }
	//
	// @RequestMapping(value ="/addProduct", method = RequestMethod.POST)
	// public String addProduct(@ModelAttribute("product") Product product) {
	// daoService.getProductDaoImpl().save(product);
	// return "redirect:/products";
	// }
	//
	// @RequestMapping(value = "/editProduct**", method = RequestMethod.GET)
	// public ModelAndView editProduct(@RequestParam("id") String id) {
	// ModelAndView model = new ModelAndView();
	// Product client = daoService.getProductDaoImpl().findOne(new Integer(id));
	// model.addObject("product", client);
	// model.setViewName("dataEnttry/addProduct");
	// return model;
	// }
	//
	// @RequestMapping("/deleteProduct")
	// public ModelAndView deleteProduct(@RequestParam("id") String id) {
	// daoService.getProductDaoImpl().delete(new Integer(id));
	// ModelAndView model = new ModelAndView();
	// List<Product> UsherTimeSheets = daoService.getProductDaoImpl().findAll();
	// model.addObject("products", UsherTimeSheets);
	// model.setViewName("dataEnttry/products");
	// return model;
	// }
	//
	// @RequestMapping(value = "/projectTypes", method = RequestMethod.GET)
	// public ModelAndView getPrpjectTypes(HttpServletRequest request) {
	// ModelAndView model = new ModelAndView();
	//
	// List<ProjectType> UsherTimeSheets =
	// daoService.getProjectTypeDaoImpl().findAll();
	// model.addObject("projectTypes", UsherTimeSheets);
	// model.setViewName("dataEnttry/projectTypes");
	// return model;
	//
	// }
	//
	// @RequestMapping(value = "/addProjectTypeForm**", method =
	// RequestMethod.GET)
	// public ModelAndView addProjectTypeForm() {
	// ModelAndView model = new ModelAndView();
	// model.addObject("projectType", new ProjectType());
	// model.setViewName("dataEnttry/addProjectType");
	// return model;
	// }
	//
	// @RequestMapping(value ="/addProjectType", method = RequestMethod.POST)
	// public String addProjectType(@ModelAttribute("projectType") ProjectType
	// projectType) {
	// daoService.getProjectTypeDaoImpl().save(projectType);
	// return "redirect:/projectTypes";
	// }
	//
	// @RequestMapping(value = "/editProjectType**", method = RequestMethod.GET)
	// public ModelAndView editProjectType(@RequestParam("id") String id) {
	// ModelAndView model = new ModelAndView();
	// ProjectType client = daoService.getProjectTypeDaoImpl().findOne(new
	// Integer(id));
	// model.addObject("projectType", client);
	// model.setViewName("dataEnttry/addProduct");
	// return model;
	// }
	//
	// @RequestMapping("/deleteProjectType")
	// public ModelAndView deleteProjectType(@RequestParam("id") String id) {
	// daoService.getProjectTypeDaoImpl().delete(new Integer(id));
	// ModelAndView model = new ModelAndView();
	// List<ProjectType> UsherTimeSheets =
	// daoService.getProjectTypeDaoImpl().findAll();
	// model.addObject("projectTypes", UsherTimeSheets);
	// model.setViewName("dataEnttry/projectTypes");
	// return model;
	// }
	//
	// @RequestMapping(value = "/projects", method = RequestMethod.GET)
	// public ModelAndView getPrpjects(HttpServletRequest request) {
	// ModelAndView model = new ModelAndView();
	//
	// List<Project> UsherTimeSheets = daoService.getProjectDaoImpl().findAll();
	// model.addObject("projects", UsherTimeSheets);
	// model.setViewName("dataEnttry/projects");
	// return model;
	//
	// }
	//
	// @RequestMapping(value = "/addProjectForm**", method = RequestMethod.GET)
	// public ModelAndView addProjectForm() {
	// ModelAndView model = new ModelAndView();
	// model.addObject("project", new Project());
	// model.setViewName("dataEnttry/addProject");
	// initModelList(model);
	// return model;
	// }
	//
	// @RequestMapping(value ="/addProject", method = RequestMethod.POST)
	// public String addProject(@ModelAttribute("project") Project project) {
	// daoService.storeProject(project);
	// return "redirect:/projects";
	// }
	//
	// @RequestMapping(value = "/editProject**", method = RequestMethod.GET)
	// public ModelAndView editProject(@RequestParam("id") String id) {
	// ModelAndView model = new ModelAndView();
	// Project client = daoService.getProjectDaoImpl().findOne(id);
	// model.addObject("project", client);
	// model.setViewName("dataEnttry/addProject");
	// return model;
	// }
	//
	// @RequestMapping("/deleteProject")
	// public ModelAndView deleteProject(@RequestParam("id") String id) {
	// daoService.getProjectDaoImpl().delete(id);
	// ModelAndView model = new ModelAndView();
	// List<Project> UsherTimeSheets = daoService.getProjectDaoImpl().findAll();
	// model.addObject("projects", UsherTimeSheets);
	// model.setViewName("dataEnttry/projects");
	// return model;
	// }
	//
	// private void initModelList(ModelAndView model) {
	//
	// // add project clients
	// List<Client> clients = daoService.getClientDaoImpl().findAll();
	// model.addObject("clients", clients);
	//
	// // add project products
	// List<Product> products = daoService.getProductDaoImpl()
	// .findAll();
	// model.addObject("products", products);
	//
	// // add project types
	// List<ProjectType> projectTypes = daoService
	// .getProjectTypeDaoImpl().findAll();
	// model.addObject("projectTypes", projectTypes);
	// }
}
