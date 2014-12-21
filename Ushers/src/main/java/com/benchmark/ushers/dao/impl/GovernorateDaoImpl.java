package com.benchmark.ushers.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.benchmark.ushers.dao.model.Governorate;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class GovernorateDaoImpl extends JdbcRepository<Governorate, Integer> {

	private static final String TABLE_NAME = "governorates";
	private JdbcTemplate jdbcTemplate;
	
	public GovernorateDaoImpl()
	{
		this(TABLE_NAME);
	}
	
	public GovernorateDaoImpl(DataSource dataSource)
	{
		this(TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	public GovernorateDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName);
	}

	public GovernorateDaoImpl(RowMapper<Governorate> rowMapper, RowUnmapper<Governorate> rowUnmapper, String tableName, String idColumn) {
		super(rowMapper, rowUnmapper, tableName, idColumn);
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	public static final RowMapper<Governorate> MAPPER = new RowMapper<Governorate>() {

		@Override
		public Governorate mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Governorate(
					rs.getInt("id"),
					rs.getString("name")
			);
		}
	};

	private static final RowUnmapper<Governorate> ROW_UNMAPPER = new RowUnmapper<Governorate>() {
		@Override
		public Map<String, Object> mapColumns(Governorate Governorate) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("id", Governorate.getId());
			mapping.put("name", Governorate.getName());
			return mapping;
		}
	};

	@Override
	protected <S extends Governorate> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.intValue());
		return entity;
	}
}
