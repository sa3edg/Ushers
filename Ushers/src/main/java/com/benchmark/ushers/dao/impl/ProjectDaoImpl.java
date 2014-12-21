package com.benchmark.ushers.dao.impl;

import com.benchmark.ushers.common.util.DateUtil;
import com.benchmark.ushers.dao.model.Project;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class ProjectDaoImpl extends JdbcRepository<Project, Integer> {

	private static final String PROJECT_TABLE_NAME = "users";
	public ProjectDaoImpl()
	{
		this(PROJECT_TABLE_NAME);
	}
	public ProjectDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName);
	}

	public ProjectDaoImpl(RowMapper<Project> rowMapper, RowUnmapper<Project> rowUnmapper, String tableName, String idColumn) {
		super(rowMapper, rowUnmapper, tableName, idColumn);
	}

	public static final RowMapper<Project> MAPPER = new RowMapper<Project>() {

		@Override
		public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
			Project project = new Project();
			project.setId(rs.getInt("project_id"));
			project.setClient(rs.getString("client"));
			project.setProduct(rs.getString("product"));
			project.setProjectName(rs.getString("project_name"));
			project.setProjectCode(rs.getString("project_code"));
			project.setProjectType(rs.getString("project_type"));
			project.setStartDate(DateUtil.getStringFromDateWithFormat(rs.getDate("project_start_date")));
			project.setEndDate(DateUtil.getStringFromDateWithFormat(rs.getDate("project_end_date")));
			project.setYear(rs.getInt("year"));
			return project;
		}
	};

	private static final RowUnmapper<Project> ROW_UNMAPPER = new RowUnmapper<Project>() {
		@Override
		public Map<String, Object> mapColumns(Project comment) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			try{
			mapping.put("project_id", comment.getId());
			mapping.put("client", comment.getClient());
			mapping.put("product", comment.getProduct());
			mapping.put("project_name", comment.getProjectName());
			mapping.put("project_code", comment.getProjectCode());
			mapping.put("project_type", comment.getProjectType());
			mapping.put("project_start_date", comment.getStartDate());
			mapping.put("project_end_date", comment.getEndDate());
			mapping.put("year", comment.getYear());
			return mapping;
			}
			catch(Exception ex){
				
			}
			return null;
		}
	};

	@Override
	protected <S extends Project> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.intValue());
		return entity;
	}
}
