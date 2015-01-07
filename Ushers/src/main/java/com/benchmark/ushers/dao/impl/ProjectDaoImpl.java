package com.benchmark.ushers.dao.impl;

import com.benchmark.ushers.dao.model.Project;
import com.benchmark.ushers.dao.model.Usher;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

@Repository
public class ProjectDaoImpl extends JdbcRepository<Project, String> {

	private static final String PROJECTS_TABLE_NAME = "projects";
	private JdbcTemplate jdbcTemplate;
		
	public ProjectDaoImpl(DataSource dataSource)
	{
		this(PROJECTS_TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	public ProjectDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName, "project_code");
	}
	public ProjectDaoImpl()
	{
		this(PROJECTS_TABLE_NAME);
	}
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}

	public static final RowMapper<Project> MAPPER = new RowMapper<Project>() {

		@Override
		public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
			Project project = new Project();
			project.setClientId(rs.getString("client_id"));
			project.setProductId(rs.getString("product_id"));
			project.setProjectName(rs.getString("project_name"));
			project.setProjectCode(rs.getString("project_code"));
			project.setProjectTypeId(rs.getString("project_type_id"));
			project.setProjectDate(rs.getDate("project_date"));
			return project;
		}
	};

	private static final RowUnmapper<Project> ROW_UNMAPPER = new RowUnmapper<Project>() {
		@Override
		public Map<String, Object> mapColumns(Project comment) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			try{
			mapping.put("client", comment.getClientId());
			mapping.put("product", comment.getProductId());
			mapping.put("project_name", comment.getProjectName());
			mapping.put("project_code", comment.getProjectCode());
			mapping.put("project_type", comment.getProjectTypeId());
			mapping.put("project_start_date", comment.getProjectDate());
			return mapping;
			}
			catch(Exception ex){
				
			}
			return null;
		}
	};

	@Override
	protected <S extends Project> S postUpdate(S entity) {
		entity.withPersisted(true);
		return entity;
	}

	@Override
	protected <S extends Project> S postCreate(S entity, Number generatedId) {
		entity.withPersisted(true);
		return entity;
	}
	
	public String getLastProjectrCode(){
		if(count() > 0){
			Project project = jdbcTemplate.queryForObject("SELECT * FROM " + PROJECTS_TABLE_NAME + " ORDER BY usher_code DESC LIMIT 1", MAPPER);
			return project != null ? project.getProjectCode() : "";
		}
		return "";
	}
	public void updateProject(Project project){
		update(project);
	}
}
