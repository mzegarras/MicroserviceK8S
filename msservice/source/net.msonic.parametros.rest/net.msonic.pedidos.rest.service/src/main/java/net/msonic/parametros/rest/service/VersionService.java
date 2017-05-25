package net.msonic.parametros.rest.service;


import net.msonic.parametros.rest.common.TransactionResponse;

public interface VersionService {
	
	TransactionResponse list(String versionPhone,String tipo,String activo);

}
