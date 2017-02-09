package com.benchmark.ushers.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.benchmark.ushers.common.util.DateUtil;
import com.benchmark.ushers.dao.model.Project;
import com.benchmark.ushers.dao.model.UsherTimeSheet;
import com.benchmark.ushers.dao.model.UsherTimeSheetReport;
import com.benchmark.ushers.service.DaoService;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class UsherTimeSheetDaoImpl extends
		JdbcRepository<UsherTimeSheet, Integer> {

	private static final Logger logger = LoggerFactory.getLogger(UsherTimeSheetDaoImpl.class);
	private static final String TABLE_NAME = "ushers_time_sheet";
	private JdbcTemplate jdbcTemplate;

	public UsherTimeSheetDaoImpl() {
		this(TABLE_NAME);
	}

	public UsherTimeSheetDaoImpl(DataSource dataSource) {
		this(TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}

	public UsherTimeSheetDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName);
	}

	public UsherTimeSheetDaoImpl(RowMapper<UsherTimeSheet> rowMapper,
			RowUnmapper<UsherTimeSheet> rowUnmapper, String tableName,
			String idColumn) {
		super(rowMapper, rowUnmapper, tableName, idColumn);
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}

	public static final RowMapper<UsherTimeSheet> MAPPER = new RowMapper<UsherTimeSheet>() {

		@Override
		public UsherTimeSheet mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			UsherTimeSheet timeSheet = new UsherTimeSheet();
			timeSheet.setId(rs.getInt("id"));
			timeSheet.setProjectLocationId(rs.getInt("project_location_id"));
			timeSheet.setProjectCode(rs.getString("project_id"));
			timeSheet.setUsherCode(rs.getString("usher_code"));
			timeSheet.setDeduction(rs.getInt("deduction"));
			timeSheet.setDebit(rs.getInt("debit"));
			timeSheet.setDaySalary(rs.getInt("day_salary"));
			timeSheet.setDate(rs.getDate("sheet_date"));
			timeSheet.setUniformDelivered(rs.getBoolean("uniform_delivered"));
			return timeSheet;
		}
	};

	private static final RowUnmapper<UsherTimeSheet> ROW_UNMAPPER = new RowUnmapper<UsherTimeSheet>() {
		@Override
		public Map<String, Object> mapColumns(UsherTimeSheet UsherTimeSheet) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("id", UsherTimeSheet.getId());
			mapping.put("project_location_id",
					UsherTimeSheet.getProjectLocationId());
			mapping.put("project_id", UsherTimeSheet.getProjectCode());
			mapping.put("usher_code", UsherTimeSheet.getUsherCode());
			mapping.put("deduction", UsherTimeSheet.getDeduction());
			mapping.put("debit", UsherTimeSheet.getDebit());
			mapping.put("day_salary", UsherTimeSheet.getDaySalary());
			mapping.put("sheet_date", UsherTimeSheet.getDate());
			mapping.put("uniform_delivered", UsherTimeSheet.isUniformDelivered());
			return mapping;
		}
	};

	@Override
	protected <S extends UsherTimeSheet> S postCreate(S entity,
			Number generatedId) {
		entity.setId(generatedId.intValue());
		return entity;
	}
	
	public List<UsherTimeSheet> getUsherTimeSheetReport() {

		List<UsherTimeSheet> allReports = jdbcTemplate.query("SELECT t.id, p.project_name, u.usher_code, l.name, u.first_name, u.middle_name, u.last_name , t.deduction, t.debit, t.sheet_date FROM ushers u inner join ushers_time_sheet t on u.usher_code = t.usher_code inner join projects_locations l on t.project_location_id = l.id inner join projects p on t.project_id = p.project_code"
				, USHER_TIME_SHEET_MAPPER);
		return allReports;
	}
	
	public List<Date> getUsherTimeSheetDates() {

		List<Date> dates = jdbcTemplate.queryForList("SELECT DISTINCT sheet_date FROM "
				+ TABLE_NAME, Date.class);
		return dates;
	}
	public int getDebitForUsher(String usherCode, String projectCode, String locationType, Date fromDate, Date toDate) {
		String query = "SELECT SUM(t.debit) FROM ushers_time_sheet t inner join projects_locations l on t.project_location_id = l.id where t.usher_code = ? and t.project_id = ? and l.location_type like ? and t.sheet_date in ( ? , ? )";
	    return jdbcTemplate.queryForObject(
	    		query,new Object[] { usherCode, projectCode, locationType, fromDate, toDate }, Integer.class);
	  }
	public float getDeductionForUsher(String usherCode, String projectCode, String locationType, Date fromDate, Date toDate) {
		String query = "SELECT SUM(t.deduction) FROM ushers_time_sheet t inner join projects_locations l on t.project_location_id = l.id where t.usher_code = ? and t.project_id = ? and l.location_type like ? and t.sheet_date in ( ? , ? )";
	    return jdbcTemplate.queryForObject(
	    		query,new Object[] { usherCode, projectCode, locationType, fromDate, toDate }, Float.class);
	  }
	public float getDeductionForUsherPerDay(String usherCode, String projectCode, String locationType, Date date) {
		String query = "SELECT t.deduction FROM ushers_time_sheet t inner join projects_locations l on t.project_location_id = l.id where t.usher_code = ? and t.project_id = ? and l.location_type like ? and t.sheet_date = ?";
	    return jdbcTemplate.queryForObject(
	    		query,new Object[] { usherCode, projectCode, locationType, date }, Float.class);
	  }
	public int getSalaryForUsherPerDay(String usherCode, String projectCode, String locationType, Date date) {
		String query = "SELECT t.day_salary FROM ushers_time_sheet t inner join projects_locations l on t.project_location_id = l.id where t.usher_code = ? and t.project_id = ? and l.location_type like ? and t.sheet_date = ?";
	    return jdbcTemplate.queryForObject(
	    		query,new Object[] { usherCode, projectCode, locationType, date }, Integer.class);
	  }
	public boolean isUniformDelivered(String usherCode, String projectCode, String locationType, Date fromDate, Date toDate) {
		String query = "SELECT t.uniform_delivered FROM ushers_time_sheet t inner join projects_locations l on t.project_location_id = l.id where t.usher_code = ? and t.project_id = ? and l.location_type like ? and t.sheet_date in ( ? , ? ) and t.uniform_delivered = ?";
	    return jdbcTemplate.queryForObject(
	    		query,new Object[] { usherCode, projectCode, locationType, fromDate, toDate, true }, Boolean.class);
	  }
	public List<Date> getUsherTimeSheetDates(String usherCode, String projectCode, String locationType, Date fromDate, Date toDate) {

		List<Date> dates = jdbcTemplate.queryForList("SELECT DISTINCT sheet_date FROM ushers_time_sheet t inner join projects_locations l on t.project_location_id = l.id where t.usher_code = ? and t.project_id = ? and l.location_type like ? and t.sheet_date in ( ? , ? )",
				new Object[] { usherCode, projectCode, locationType, fromDate, toDate }, Date.class);
		return dates;
	}
	
	public List<UsherTimeSheetReport> getUsherTimeSheetReport(String projectCode, String locationType, Date fromDate, Date toDate) {

		locationType = locationType+"%";
		List<UsherTimeSheetReport> reports = new ArrayList<UsherTimeSheetReport>();
		//String query = "SELECT DISTINCT u.usher_code, l.name, u.first_name, u.middle_name, u.last_name FROM ushers u inner join ushers_time_sheet t on u.usher_code = t.usher_code inner join projects_locations l on t.project_location_id = l.id where t.project_id = "+projectCode+" and l.location_type = "+locationType+" and t.sheet_date in ( "+DateUtil.getMySqlDateString(fromDate)+" , " +DateUtil.getMySqlDateString(toDate) +" )";
		String query = "SELECT DISTINCT u.usher_code, l.name, u.first_name, u.middle_name, u.last_name , u.national_id_no FROM ushers u inner join ushers_time_sheet t on u.usher_code = t.usher_code inner join projects_locations l on t.project_location_id = l.id where t.project_id = ? and l.location_type like ? and t.sheet_date in ( ? , ? )";
		logger.debug("join query :" + query);
		logger.debug("project code :" + projectCode);
		logger.debug("location type:" + locationType);
		logger.debug("start date :" + fromDate);
		logger.debug("end date :" + toDate);
		List<UsherTimeSheetReport> allReports = jdbcTemplate.query( query, new Object[] { projectCode, locationType, fromDate, toDate },USHER_TIME_SHEET_REPORT_MAPPER);
		
		logger.debug("query reult size :" + allReports.size());
		for(UsherTimeSheetReport report : allReports){
			int usherDebit = getDebitForUsher(report.getUsherCode(), projectCode, locationType, fromDate, toDate);
			//float usherDeduction = getDeductionForUsher(report.getUsherCode(), projectCode, locationType, fromDate, toDate);
			boolean isUniformDelivered = isUniformDelivered(report.getUsherCode(), projectCode, locationType, fromDate, toDate);
			List<Date> usherDates = getUsherTimeSheetDates(report.getUsherCode(), projectCode, locationType, fromDate, toDate);
			
			List<String> usherDatesString = DateUtil.getDateDatesString(usherDates);
			report.setDates(DateUtil.getDatesList(fromDate, toDate));
			int daysCount = 0;
			for(String date : report.getDates()){
				if(usherDatesString.contains(date)){
					date="1";
					daysCount++;
				}else{
					date="0";
				}
			}
			
			if(daysCount != usherDates.size()){
				logger.debug("The size does not equal error occured");
			}
		    float deduction = 0;
		    int avgDaySalary = 0;
		    float totalMoneyAmount = 0;
			for(Date date : usherDates){
				int daySalary = getSalaryForUsherPerDay(report.getUsherCode(), projectCode, locationType, date);
				float deductionPerDay = getDeductionForUsherPerDay(report.getUsherCode(), projectCode, locationType, date);
				deduction += deductionPerDay;
				avgDaySalary += daySalary;
				float daySalaryAfterDeduction = daySalary - (daySalary/deductionPerDay);
				totalMoneyAmount += daySalaryAfterDeduction;
			}
			report.setDaysCount(daysCount);
			report.setDeduction(deduction);
			report.setTotalDays(daysCount - deduction);
			report.setDayMoneyAmount(avgDaySalary/daysCount);
			report.setMoneyAmount(totalMoneyAmount);
			report.setDebit(usherDebit);
			report.setTotalMoneyAmount(report.getMoneyAmount() - report.getDebit());
			report.setUniformDelivered(isUniformDelivered);
		}
		return reports;
	}
	
	public static final RowMapper<UsherTimeSheetReport> USHER_TIME_SHEET_REPORT_MAPPER = new RowMapper<UsherTimeSheetReport>() {

		@Override
		public UsherTimeSheetReport mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			UsherTimeSheetReport timeSheet = new UsherTimeSheetReport();
//			timeSheet.setId(rs.getInt("id"));
//			timeSheet.setProjectLocationId(rs.getInt("project_location_id"));
//			timeSheet.setProjectCode(rs.getString("project_id"));
			timeSheet.setUsherCode(rs.getString("usher_code"));
			timeSheet.setProjectLocationName(rs.getString("name"));
			timeSheet.setUsherName(rs.getString("first_name") + " " + rs.getString("middle_name") + " " + rs.getString("last_name"));
			timeSheet.setUsherIdentificationId(rs.getString("national_id_no"));
//			timeSheet.setDeduction(rs.getInt("deduction"));
//			timeSheet.setDebit(rs.getInt("debit"));
//			timeSheet.setDate(rs.getDate("sheet_date"));
//			timeSheet.setUniformDelivered(rs.getBoolean("uniform_delivered"));
			return timeSheet;
		}
	};
	
	public static final RowMapper<UsherTimeSheet> USHER_TIME_SHEET_MAPPER = new RowMapper<UsherTimeSheet>() {

		@Override
		public UsherTimeSheet mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			UsherTimeSheet timeSheet = new UsherTimeSheet();
			timeSheet.setId(rs.getInt("id"));
			timeSheet.setProjectLocationName(rs.getString("name"));
			timeSheet.setProjectName(rs.getString("project_name"));
			timeSheet.setUsherName(rs.getString("first_name") + " " + rs.getString("middle_name") + " " + rs.getString("last_name"));
			timeSheet.setDeduction(rs.getInt("deduction"));
			timeSheet.setDebit(rs.getInt("debit"));
			timeSheet.setDate(rs.getDate("sheet_date"));
			return timeSheet;
		}
	};
}
