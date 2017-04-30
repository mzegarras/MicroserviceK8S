package net.msonic.parametros.rest.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import net.msonic.parametros.rest.common.Country;
import net.msonic.parametros.rest.dao.CountryRepository;

@Repository
public class CountryRepositoryImpl implements CountryRepository {
	

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Country> list() {
		String SQL = "SELECT id,description FROM country";
		
		List<Country> countries = null;
		
		try{
			countries = jdbcTemplate.query(SQL,this::mapParam);
		}catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		
		return countries;
		
	}
	
	public Country mapParam(ResultSet rs, int i) throws SQLException {
		Country country = new Country();
		country.setId(rs.getInt("id"));
		country.setDescription(StringUtils.trimToEmpty(rs.getString("description")));
		return country;
	}
}
