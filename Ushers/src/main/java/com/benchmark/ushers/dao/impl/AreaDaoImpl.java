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
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class AreaDaoImpl extends JdbcRepository<Area, Integer> {

	private static final String TABLE_NAME = "areas";
	private JdbcTemplate jdbcTemplate;
	
	public AreaDaoImpl()
	{
		this(TABLE_NAME);
	}
	
	public AreaDaoImpl(DataSource dataSource)
	{
		this(TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	public AreaDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName);
	}

	public AreaDaoImpl(RowMapper<Area> rowMapper, RowUnmapper<Area> rowUnmapper, String tableName, String idColumn) {
		super(rowMapper, rowUnmapper, tableName, idColumn);
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	public static final RowMapper<Area> MAPPER = new RowMapper<Area>() {

		@Override
		public Area mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Area(
					rs.getInt("id"),
					rs.getString("name")
			);
		}
	};

	private static final RowUnmapper<Area> ROW_UNMAPPER = new RowUnmapper<Area>() {
		@Override
		public Map<String, Object> mapColumns(Area Area) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("id", Area.getId());
			mapping.put("name", Area.getName());
			return mapping;
		}
	};

	@Override
	protected <S extends Area> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.intValue());
		return entity;
	}
}
