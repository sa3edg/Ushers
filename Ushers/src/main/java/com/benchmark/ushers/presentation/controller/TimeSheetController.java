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
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.group.Groups;
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








import com.benchmark.ushers.common.util.UsherUtil;
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

	private static final String PDF_TYPE = "PDF";
	private static final String EXCEL_TYPE = "EXCEL";
	@RequestMapping(value = "/usherTimeSheets", method = RequestMethod.GET)
	public ModelAndView getTimeShets(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		List<UsherTimeSheet> UsherTimeSheets = daoService
				.getUsherTimeSheetDaoImpl().getUsherTimeSheetReport();
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
		List<UsherTimeSheet> timeSheets = UsherUtil.getUsherTimeSheets(UsherTimeSheet);
		daoService.getUsherTimeSheetDaoImpl().save(timeSheets);
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
				.getUsherTimeSheetDaoImpl().getUsherTimeSheetReport();
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
		List<UsherTimeSheetReport> items = new ArrayList<UsherTimeSheetReport>();
		UsherTimeSheetReport report = new UsherTimeSheetReport();
		report.setProjectName("Sample1" + UsherTimeSheet.getFromDate());
		report.setUsherName("Said gamal said");
		items.add(report);
		List<UsherTimeSheetReport> reports = daoService.getUsherTimeSheetDaoImpl().getUsherTimeSheetReport(UsherTimeSheet.getProjectCode(), UsherTimeSheet.getLocationType(), UsherTimeSheet.getFromDate(), UsherTimeSheet.getToDate());
		showUsherTimeSheetReport(items, response);
	}
	
	@RequestMapping(value = "/handleUsherTimeSheetReportActions", method = RequestMethod.POST, params = { "showReportNewWindowParam"})
	@ResponseBody
	public void usherTimeSheetReportNewWindow(
			@ModelAttribute("UsherTimeSheetCriteria") UsherTimeSheetReportCriteria UsherTimeSheet, HttpServletResponse response) throws Exception{
		List<UsherTimeSheetReport> items = new ArrayList<UsherTimeSheetReport>();
		UsherTimeSheetReport report = new UsherTimeSheetReport();
		report.setProjectName("Sample1" + UsherTimeSheet.getFromDate());
		report.setUsherName("Said gamal said");
		items.add(report);
		showUsherTimeSheetReport(items, response);
	}
	
	@RequestMapping(value = "/handleUsherTimeSheetReportActions", method = RequestMethod.POST, params = { "exportReportToPDF"})
	@ResponseBody
	public void exportUsherTimeSheetReportToPDF(
			@ModelAttribute("UsherTimeSheetCriteria") UsherTimeSheetReportCriteria UsherTimeSheet, HttpServletResponse response) throws Exception{
			List<UsherTimeSheetReport> items = new ArrayList<UsherTimeSheetReport>();
			UsherTimeSheetReport report = new UsherTimeSheetReport();
			report.setProjectName("Sample1" + UsherTimeSheet.getFromDate());
			report.setUsherName("Said gamal said");
			items.add(report);
			exportUsherTimeSheetReport(items, response,PDF_TYPE);
	}
	
	@RequestMapping(value = "/handleUsherTimeSheetReportActions", method = RequestMethod.POST, params = { "exportReportToExcel"})
	@ResponseBody
	public void exportUsherTimeSheetReportToExcel(
			@ModelAttribute("UsherTimeSheetCriteria") UsherTimeSheetReportCriteria UsherTimeSheet, HttpServletResponse response) throws Exception{
			List<UsherTimeSheetReport> items = new ArrayList<UsherTimeSheetReport>();
			UsherTimeSheetReport report = new UsherTimeSheetReport();
			report.setProjectName("Sample1" + UsherTimeSheet.getFromDate());
			report.setUsherName("Said gamal said");
			items.add(report);
			exportUsherTimeSheetReport(items, response, EXCEL_TYPE);
	}
	
	@RequestMapping(value = "/handleUsherTimeSheetReportActions", method = RequestMethod.POST, params = { "print"})
	@ResponseBody
	public void printUsherTimeSheetReport(
			@ModelAttribute("UsherTimeSheetCriteria") UsherTimeSheetReportCriteria UsherTimeSheet, HttpServletResponse response) throws Exception{
			List<UsherTimeSheetReport> items = new ArrayList<UsherTimeSheetReport>();
			UsherTimeSheetReport report = new UsherTimeSheetReport();
			report.setProjectLocationName("Saudii");
			report.setUsherName("Said gamal said");
			items.add(report);
			
			UsherTimeSheetReport report1 = new UsherTimeSheetReport();
			report1.setProjectLocationName("Saudii");
			report1.setUsherName("Said gamal said");
			items.add(report1);
			printUsherTimeSheetReport(items, response);
	}
    public JasperReportBuilder createUsherTimeSheetReport(List<UsherTimeSheetReport> items){
    	JRDataSource JRdataSource = new JRBeanCollectionDataSource(items);
		JasperReportBuilder report = DynamicReports.report();//a new report
		TextColumnBuilder<String> projectLocation = Columns.column("project location", "projectLocationName", DataTypes.stringType()).setPrintRepeatedDetailValues(false);
		TextColumnBuilder<String> usherName = Columns.column("usher name", "usherName", DataTypes.stringType());

		//init groups
//		ColumnGroupBuilder projectLocationGroup = Groups.group(projectLocation);

		report
		  .columns(
				  projectLocation,
				  usherName)//,
//		      Columns.column("Date", "date", DataTypes.dateType()))
//		  .groupBy(projectLocationGroup)
		  .title(//title of the report
		      Components.text("SimpleReportExample")
			  .setHorizontalAlignment(HorizontalAlignment.CENTER))
			  .pageFooter(Components.pageXofY())//show page number on the page footer
			  .setDataSource(JRdataSource);
		return report;
    }
	
	public void showUsherTimeSheetReport(List<UsherTimeSheetReport> items, HttpServletResponse response) throws Exception
	{
		JasperReportBuilder report = createUsherTimeSheetReport(items);
		response.setContentType("text/html");
		ServletOutputStream outStream = response.getOutputStream();
		report.toHtml(outStream);
		outStream.flush();
		outStream.close();
	}
	
	public void exportUsherTimeSheetReport(List<UsherTimeSheetReport> items, HttpServletResponse response, String type) throws Exception
	{
		JasperReportBuilder report = createUsherTimeSheetReport(items);
		String title = "myReport";
		response.setHeader("Content-Disposition", "attachment;filename="+title+".pdf");
		if(PDF_TYPE.equals(type)){
			response.setContentType("application/pdf");
		}else if(EXCEL_TYPE.equals(type)){
			response.setContentType("application/vnd.ms-excel");
		}
		ServletOutputStream outStream = response.getOutputStream();
		report.toPdf(outStream);
		outStream.flush();
		outStream.close();
	}
	
	public void printUsherTimeSheetReport(List<UsherTimeSheetReport> items, HttpServletResponse response) throws Exception
	{
		JasperReportBuilder report = createUsherTimeSheetReport(items);
		response.setHeader("Content-Type", "application/pdf");
		response.setContentType("application/pdf");
		ServletOutputStream outStream = response.getOutputStream();
		report.toPdf(outStream);
		outStream.flush();
		outStream.close();
	}
}
