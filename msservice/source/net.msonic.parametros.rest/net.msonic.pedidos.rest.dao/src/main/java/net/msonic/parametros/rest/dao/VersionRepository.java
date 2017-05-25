package net.msonic.parametros.rest.dao;


import net.msonic.parametros.rest.common.Version;

public interface VersionRepository {
	
	Version list(String tipo,String activo);

}
