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

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.benchmark.ushers.dao.model.Project;
import com.benchmark.ushers.dao.model.UsherTimeSheet;
import com.benchmark.ushers.dao.model.UsherTimeSheetReport;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class UsherTimeSheetDaoImpl extends
		JdbcRepository<UsherTimeSheet, Integer> {

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

	public List<Date> getUsherTimeSheetDates() {

		List<Date> dates = jdbcTemplate.queryForList("SELECT DISTINCT sheet_date FROM "
				+ TABLE_NAME, Date.class);
		return dates;
	}
	
	public List<UsherTimeSheetReport> getUsherTimeSheetReport(String projectCode, String locationType, Date fromDate, Date toDate) {

		List<UsherTimeSheetReport> reports = new ArrayList<UsherTimeSheetReport>();
		List<UsherTimeSheetReport> allReports = jdbcTemplate.query("SELECT DISTINCT u.usher_code, l.name, u.first_name, u.middle_name, u.last_name FROM ushers u inner join ushers_time_sheet t on u.usher_code = t.usher_code inner join projects_locations l on t.project_location_id = l.id"
				+ TABLE_NAME, USHER_TIME_SHEET_REPORT_MAPPER);
		return reports;
	}
	
	public static final RowMapper<UsherTimeSheetReport> USHER_TIME_SHEET_REPORT_MAPPER = new RowMapper<UsherTimeSheetReport>() {

		@Override
		public UsherTimeSheetReport mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			UsherTimeSheetReport timeSheet = new UsherTimeSheetReport();
			timeSheet.setId(rs.getInt("id"));
			timeSheet.setProjectLocationId(rs.getInt("project_location_id"));
			timeSheet.setProjectCode(rs.getString("project_id"));
			timeSheet.setUsherCode(rs.getString("usher_code"));
			timeSheet.setDeduction(rs.getInt("deduction"));
			timeSheet.setDebit(rs.getInt("debit"));
			timeSheet.setDate(rs.getDate("sheet_date"));
			timeSheet.setUniformDelivered(rs.getBoolean("uniform_delivered"));
			return timeSheet;
		}
	};
}
