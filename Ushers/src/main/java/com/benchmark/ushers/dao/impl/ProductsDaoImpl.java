package com.benchmark.ushers.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.benchmark.ushers.dao.model.Product;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class ProductsDaoImpl extends JdbcRepository<Product, Integer> {

	private static final String TABLE_NAME = "products";
	private JdbcTemplate jdbcTemplate;
	
	public ProductsDaoImpl()
	{
		this(TABLE_NAME);
	}
	
	public ProductsDaoImpl(DataSource dataSource)
	{
		this(TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	public ProductsDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName);
	}

	public ProductsDaoImpl(RowMapper<Product> rowMapper, RowUnmapper<Product> rowUnmapper, String tableName, String idColumn) {
		super(rowMapper, rowUnmapper, tableName, idColumn);
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	public static final RowMapper<Product> MAPPER = new RowMapper<Product>() {

		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Product(
					rs.getInt("id"),
					rs.getString("name")
			);
		}
	};

	private static final RowUnmapper<Product> ROW_UNMAPPER = new RowUnmapper<Product>() {
		@Override
		public Map<String, Object> mapColumns(Product Product) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("id", Product.getId());
			mapping.put("name", Product.getName());
			return mapping;
		}
	};

	@Override
	protected <S extends Product> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.intValue());
		return entity;
	}
}
