package com.benchmark.ushers.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.benchmark.ushers.dao.model.PreferredLocation;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class PreferredLocationDaoImpl extends JdbcRepository<PreferredLocation, Integer> {

	private static final String TABLE_NAME = "preferred_locations";
	private JdbcTemplate jdbcTemplate;
	
	public PreferredLocationDaoImpl()
	{
		this(TABLE_NAME);
	}
	
	public PreferredLocationDaoImpl(DataSource dataSource)
	{
		this(TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	public PreferredLocationDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName);
	}

	public PreferredLocationDaoImpl(RowMapper<PreferredLocation> rowMapper, RowUnmapper<PreferredLocation> rowUnmapper, String tableName, String idColumn) {
		super(rowMapper, rowUnmapper, tableName, idColumn);
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	public static final RowMapper<PreferredLocation> MAPPER = new RowMapper<PreferredLocation>() {

		@Override
		public PreferredLocation mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new PreferredLocation(
					rs.getInt("id"),
					rs.getString("name")
			);
		}
	};

	private static final RowUnmapper<PreferredLocation> ROW_UNMAPPER = new RowUnmapper<PreferredLocation>() {
		@Override
		public Map<String, Object> mapColumns(PreferredLocation PreferredLocation) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("id", PreferredLocation.getId());
			mapping.put("name", PreferredLocation.getName());
			return mapping;
		}
	};

	@Override
	protected <S extends PreferredLocation> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.intValue());
		return entity;
	}
}
