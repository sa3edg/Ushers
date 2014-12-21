package com.benchmark.ushers.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.benchmark.ushers.common.util.DateUtil;
import com.benchmark.ushers.dao.model.Usher;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class UsherDaoImpl extends JdbcRepository<Usher, String> {

	private static final String USHERS_TABLE_NAME = "ushers";
	private JdbcTemplate jdbcTemplate;
		
	public UsherDaoImpl(DataSource dataSource)
	{
		this(USHERS_TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	public UsherDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName, "usher_code");
	}
	public UsherDaoImpl()
	{
		this(USHERS_TABLE_NAME);
	}
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	
	public static final RowMapper<Usher> MAPPER = new RowMapper<Usher>() {
		@Override
		public Usher mapRow(ResultSet rs, int rowNum) throws SQLException {
			Usher usher = new Usher();
			usher.setUsherCode(rs.getString("usher_code"));
			usher.setAdditionalInformation(rs.getString("additional_information"));
			usher.setAddress(rs.getString("address"));
			usher.setAge(rs.getInt("age"));
			usher.setBirthDate(DateUtil.getStringFromDateWithFormat(rs.getDate("birth_date")));
			usher.setEmailAddress(rs.getString("email_address"));
			usher.setFacebookAccount(rs.getString("facebook_account"));
			usher.setFirstName(rs.getString("first_name"));
			usher.setGender(rs.getString("gender"));
			usher.setHairType(rs.getString("hair_color"));
			usher.setHeight(rs.getString("height"));
			usher.setLastName(rs.getString("last_name"));
			usher.setMiddleName(rs.getString("middle_name"));
			usher.setMobileNumber(rs.getString("mobile_number"));
			usher.setNationalIdNumber(rs.getString("national_id_no"));
			usher.setPantsSize(rs.getString("pants_ize"));
			usher.setLandlineNumber(rs.getString("landline_number"));
			usher.setReferredBy(rs.getString("referred_by"));
			usher.setSchool(rs.getString("school"));
			usher.setShirtSize(rs.getString("shirt_size"));
			usher.setSocialInsurance(rs.getBoolean("social_insurance"));
			usher.setSocialInsuranceDate(DateUtil.getStringFromDateWithFormat(rs.getDate("social_insurance_date")));
			usher.setSocialInsuranceExitDate(DateUtil.getStringFromDateWithFormat(rs.getDate("social_insurance_exit_date")));
			usher.setSocialInsuranceForm6(rs.getBoolean("social_insurance_form_6"));
			usher.setSocialInsuranceNumber(rs.getString("social_insurance_no"));
			usher.setUniversity(rs.getString("university"));
			usher.setWeight(rs.getString("weight"));
			usher.setUsherCaliber(rs.getString("usher_caliber"));
			usher.setMaritalStatus(rs.getString("marital_status"));
			usher.setHasKids(rs.getBoolean("has_kids"));
			usher.setNumberOfKids(rs.getInt("kids_number"));
			usher.setAppartmentNumber(rs.getString("appartment_number"));
			usher.setStreet(rs.getString("street"));
			usher.setArea(rs.getString("area"));
			usher.setGovernorate(rs.getString("governorate"));
			usher.setPreferredLocation(rs.getString("preferred_location"));
			usher.setPreferredShift(rs.getString("preferred_shift"));
			usher.setLanguages(rs.getString("languages"));
			usher.setUniversityDegree(rs.getString("university_degree"));
			usher.setGraduationYear(rs.getString("graduation_year"));
			usher.withPersisted(true);
			return usher;
		}
	};

	public static final RowUnmapper<Usher> ROW_UNMAPPER = new RowUnmapper<Usher>() {
		@Override
		public Map<String, Object> mapColumns(Usher usher) {
			final LinkedHashMap<String, Object> columns = new LinkedHashMap<String, Object>();
			columns.put("usher_code", usher.getUsherCode());
			columns.put("additional_information", usher.getAdditionalInformation());
			columns.put("address", usher.getAddress());
			columns.put("age", usher.getAge());
			columns.put("email_address", usher.getEmailAddress());
			columns.put("facebook_account", usher.getFacebookAccount());
			columns.put("first_name", usher.getFirstName());
			columns.put("gender", usher.getGender());
			columns.put("hair_color", usher.getHairType());
			columns.put("height", usher.getHeight());
			columns.put("last_name", usher.getLastName());
			columns.put("middle_name", usher.getMiddleName());
			columns.put("mobile_number", usher.getMobileNumber());
			columns.put("national_id_no", usher.getNationalIdNumber());
			columns.put("pants_ize", usher.getPantsSize());
			columns.put("landline_number", usher.getLandlineNumber());
			columns.put("referred_by", usher.getReferredBy());
			columns.put("school", usher.getSchool());
			columns.put("shirt_size", usher.getShirtSize());
			columns.put("social_insurance", usher.isSocialInsurance());
			columns.put("social_insurance_date", usher.getSocialInsuranceDate());
			columns.put("social_insurance_exit_date", usher.getSocialInsuranceExitDate());
			columns.put("social_insurance_form_6", usher.isSocialInsuranceForm6());
			columns.put("social_insurance_no", usher.getSocialInsuranceNumber());
			columns.put("university", usher.getUniversity());
			columns.put("class", usher.getClass());
			columns.put("weight", usher.getWeight());
			columns.put("usher_caliber", usher.getUsherCaliber());
			columns.put("marital_status", usher.getMaritalStatus());
			columns.put("has_kids", usher.isHasKids());
			columns.put("kids_number", usher.getNumberOfKids());
			columns.put("appartment_number", usher.getAppartmentNumber());
			columns.put("street", usher.getStreet());
			columns.put("area", usher.getArea());
			columns.put("governorate", usher.getGovernorate());
			columns.put("preferred_location", usher.getPreferredLocation());
			columns.put("preferred_shift", usher.getPreferredShift());
			columns.put("languages", usher.getLanguages());
			columns.put("university_degree", usher.getUniversityDegree());
			columns.put("graduation_year", usher.getGraduationYear());
			return columns;
		}
	};

	@Override
	protected <S extends Usher> S postUpdate(S entity) {
		entity.withPersisted(true);
		return entity;
	}

	@Override
	protected <S extends Usher> S postCreate(S entity, Number generatedId) {
		entity.withPersisted(true);
		return entity;
	}
}
