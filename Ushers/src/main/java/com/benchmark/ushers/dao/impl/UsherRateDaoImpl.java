package com.benchmark.ushers.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.benchmark.ushers.dao.model.Area;
import com.benchmark.ushers.dao.model.UsherRate;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class UsherRateDaoImpl extends JdbcRepository<UsherRate, Integer> {

	private static final String TABLE_NAME = "usher_rating";
	private JdbcTemplate jdbcTemplate;
	
	public UsherRateDaoImpl()
	{
		this(TABLE_NAME);
	}
	
	public UsherRateDaoImpl(DataSource dataSource)
	{
		this(TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	public UsherRateDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName);
	}

	public UsherRateDaoImpl(RowMapper<UsherRate> rowMapper, RowUnmapper<UsherRate> rowUnmapper, String tableName, String idColumn) {
		super(rowMapper, rowUnmapper, tableName, idColumn);
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	public static final RowMapper<UsherRate> MAPPER = new RowMapper<UsherRate>() {

		@Override
		public UsherRate mapRow(ResultSet rs, int rowNum) throws SQLException {
			UsherRate rate =  new UsherRate();
			rate.setId(rs.getInt("id"));
			rate.setProductId(String.valueOf(rs.getInt("product_id")));
			rate.setProjectTypeId(String.valueOf(rs.getInt("project_type_id")));
			rate.setProjectCode(rs.getString("project_id"));
			rate.setRate(rs.getString("rate"));
			rate.setSupervisorFeedback(rs.getString("supervisor_feedback"));
			rate.setUsherCode(rs.getString("usher_code"));
			rate.setUsherCoordinatorFeedback(rs.getString("usher_coordinator_feedback"));
			rate.setClientFeedback(rs.getString("client_feedback"));
			return rate;
		}
	};

	private static final RowUnmapper<UsherRate> ROW_UNMAPPER = new RowUnmapper<UsherRate>() {
		@Override
		public Map<String, Object> mapColumns(UsherRate rate) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("id", rate.getId());
			mapping.put("product_id", rate.getProductId());
			mapping.put("project_type_id", rate.getProjectTypeId());
			mapping.put("project_id", rate.getProjectCode());
			mapping.put("rate", rate.getRate());
			mapping.put("supervisor_feedback", rate.getSupervisorFeedback());
			mapping.put("usher_code", rate.getUsherCode());
			mapping.put("usher_coordinator_feedback", rate.getUsherCoordinatorFeedback());
			mapping.put("client_feedback", rate.getClientFeedback());
			return mapping;
		}
	};

	@Override
	protected <S extends UsherRate> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.intValue());
		return entity;
	}
}
