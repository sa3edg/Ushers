package com.benchmark.ushers.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.benchmark.ushers.dao.model.Client;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class ClientsDaoImpl extends JdbcRepository<Client, Integer> {

	private static final String TABLE_NAME = "clients";
	private JdbcTemplate jdbcTemplate;
	
	public ClientsDaoImpl()
	{
		this(TABLE_NAME);
	}
	
	public ClientsDaoImpl(DataSource dataSource)
	{
		this(TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	public ClientsDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName);
	}

	public ClientsDaoImpl(RowMapper<Client> rowMapper, RowUnmapper<Client> rowUnmapper, String tableName, String idColumn) {
		super(rowMapper, rowUnmapper, tableName, idColumn);
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	public static final RowMapper<Client> MAPPER = new RowMapper<Client>() {

		@Override
		public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Client(
					rs.getInt("id"),
					rs.getString("name")
			);
		}
	};

	private static final RowUnmapper<Client> ROW_UNMAPPER = new RowUnmapper<Client>() {
		@Override
		public Map<String, Object> mapColumns(Client Client) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("id", Client.getId());
			mapping.put("name", Client.getName());
			return mapping;
		}
	};

	@Override
	protected <S extends Client> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.intValue());
		return entity;
	}
}
