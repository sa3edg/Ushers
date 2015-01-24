package com.benchmark.ushers.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.benchmark.ushers.dao.model.ProjectType;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class ProjectTypeDaoImpl extends JdbcRepository<ProjectType, Integer> {

	private static final String TABLE_NAME = "project_types";
	private JdbcTemplate jdbcTemplate;
	
	public ProjectTypeDaoImpl()
	{
		this(TABLE_NAME);
	}
	
	public ProjectTypeDaoImpl(DataSource dataSource)
	{
		this(TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	public ProjectTypeDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName);
	}

	public ProjectTypeDaoImpl(RowMapper<ProjectType> rowMapper, RowUnmapper<ProjectType> rowUnmapper, String tableName, String idColumn) {
		super(rowMapper, rowUnmapper, tableName, idColumn);
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	public static final RowMapper<ProjectType> MAPPER = new RowMapper<ProjectType>() {

		@Override
		public ProjectType mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new ProjectType(
					rs.getInt("id"),
					rs.getString("name")
			);
		}
	};

	private static final RowUnmapper<ProjectType> ROW_UNMAPPER = new RowUnmapper<ProjectType>() {
		@Override
		public Map<String, Object> mapColumns(ProjectType ProjectType) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("id", ProjectType.getId());
			mapping.put("name", ProjectType.getName());
			return mapping;
		}
	};

	@Override
	protected <S extends ProjectType> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.intValue());
		return entity;
	}
}
