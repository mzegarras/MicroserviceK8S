package net.msonic.parametros.rest.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import net.msonic.parametros.rest.common.Version;
import net.msonic.parametros.rest.dao.VersionRepository;

@Repository
public class VersionRepositoryImpl implements VersionRepository {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Version list(String tipo,String activo) {
		
		String SQL = "select id,version,tipo,mandatory,activo from version where tipo=cast(? as smallint) and activo=cast(? as smallint);";
		
		Version version = null;
		
	
		try{
			version = jdbcTemplate.queryForObject(SQL, new String[]{tipo, activo},this::mapParam);
		}catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		
		return version;
	}
	
	
	public Version mapParam(ResultSet rs, int i) throws SQLException {
		
		Version version = new Version();
		version.setId(rs.getInt("id"));
		version.setVersion(StringUtils.trimToEmpty(rs.getString("version")));
		version.setTipo(rs.getInt("tipo"));
		version.setMandatory(rs.getInt("mandatory"));
		version.setActivo(rs.getInt("activo"));
		
		return version;
	}

}
