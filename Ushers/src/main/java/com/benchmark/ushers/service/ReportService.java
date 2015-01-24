package com.benchmark.ushers.service;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.benchmark.ushers.reports.ReportStatementBase;

import java.sql.Connection;
import java.sql.SQLException;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;

public class ReportService {
	
	private static final String SELECT_STATEMENT = "select usher_type, first_name, last_name from ushers where";
	private Connection connection;
	
	public ReportService(DataSource dataSource)
	{
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JasperReportBuilder createJasperReport(ReportStatementBase reportStatement)
	{
		// Here use DynamicReports API to build a report 
        // and fill it with a JRDataSource.
        // I used SearchController session bean
        // to get required search results data.
		JasperReportBuilder report = DynamicReports.report();//a new report
		report
		  .columns(
//		      Columns.column("Customer Id", "id", DataTypes.integerType()),
			  Columns.column("Usher Type", "usher_type", DataTypes.stringType()),
		      Columns.column("First Name", "first_name", DataTypes.stringType()),
		      Columns.column("Last Name", "last_name", DataTypes.stringType()))//,
//		      Columns.column("Date", "date", DataTypes.dateType()))
		  .title(//title of the report
		      Components.text("SimpleReportExample")
			  .setHorizontalAlignment(HorizontalAlignment.CENTER))
			  .pageFooter(Components.pageXofY())//show page number on the page footer
			  .setDataSource(reportStatement.getReportSql(), 
	                                  connection);
	 
//		try {
//	                //show the report
//			report.show();
//	 
//	                //export the report to a pdf file
//			report.toPdf(new FileOutputStream("c:/report.pdf"));
//		} catch (DRException e) {
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		return report;
	}

}
