package com.benchmark.ushers.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.benchmark.ushers.dao.model.ProjectLocation;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class ProjectLocationDaoImpl extends JdbcRepository<ProjectLocation, Integer> {

	private static final String TABLE_NAME = "projects_locations";
	private JdbcTemplate jdbcTemplate;
	
	public ProjectLocationDaoImpl()
	{
		this(TABLE_NAME);
	}
	
	public ProjectLocationDaoImpl(DataSource dataSource)
	{
		this(TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	public ProjectLocationDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName);
	}

	public ProjectLocationDaoImpl(RowMapper<ProjectLocation> rowMapper, RowUnmapper<ProjectLocation> rowUnmapper, String tableName, String idColumn) {
		super(rowMapper, rowUnmapper, tableName, idColumn);
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	public static final RowMapper<ProjectLocation> MAPPER = new RowMapper<ProjectLocation>() {

		@Override
		public ProjectLocation mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new ProjectLocation(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("location_type")
			);
		}
	};

	private static final RowUnmapper<ProjectLocation> ROW_UNMAPPER = new RowUnmapper<ProjectLocation>() {
		@Override
		public Map<String, Object> mapColumns(ProjectLocation ProjectLocation) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("id", ProjectLocation.getId());
			mapping.put("name", ProjectLocation.getName());
			mapping.put("location_type", ProjectLocation.getLocationType());
			return mapping;
		}
	};

	@Override
	protected <S extends ProjectLocation> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.intValue());
		return entity;
	}
}
