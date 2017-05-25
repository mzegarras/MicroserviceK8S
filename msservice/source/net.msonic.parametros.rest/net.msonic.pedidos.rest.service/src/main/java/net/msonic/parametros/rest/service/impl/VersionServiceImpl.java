package net.msonic.parametros.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.msonic.parametros.rest.common.DetalleRespuesta;
import net.msonic.parametros.rest.common.TransactionResponse;
import net.msonic.parametros.rest.common.Version;
import net.msonic.parametros.rest.dao.VersionRepository;
import net.msonic.parametros.rest.service.VersionService;


@Service
public class VersionServiceImpl implements VersionService {
	
	@Autowired
	private VersionRepository versionRepository;
	
	@Override
	public TransactionResponse list(String versionPhone,String tipo, String activo) {
		
		TransactionResponse transactionResponse = new TransactionResponse();
		DetalleRespuesta detalleRespuesta = new DetalleRespuesta();
		
		
		Version version = versionRepository.list(tipo, activo);
		
		if(version!=null){
			
			int compareValue = compararVersionContraBD(versionPhone,version.getVersion());
			boolean mandatory = version.getMandatory()==1;
			
			transactionResponse.setCodRpta("0000");
			
			if(compareValue==0 && mandatory){
				detalleRespuesta.setMandatory(STR_ESTADO_ACTUALIZADO);
				detalleRespuesta.setMessage(MSG_ESTADO_ACTUALIZADO);
				detalleRespuesta.setVersion(version.getVersion());
			}else if(compareValue==1 && mandatory){
				detalleRespuesta.setMandatory(STR_ESTADO_OBLIGATORIO);
				detalleRespuesta.setMessage(MSG_ESTADO_OBLIGATORIO);
				detalleRespuesta.setVersion(version.getVersion());
			}else if(compareValue==1 && !mandatory){
				
				detalleRespuesta.setMandatory(STR_ESTADO_OPCIONAL);
				detalleRespuesta.setMessage(MSG_ESTADO_OPCIONAL);
				detalleRespuesta.setVersion(version.getVersion());
			}
			
		}else{
			transactionResponse.setCodRpta("9999");
			transactionResponse.setDesRpta("Error procesando.");
			detalleRespuesta=null;
		}
		
		transactionResponse.setDetRpta(detalleRespuesta);
		
		return transactionResponse;
	}
	
	private static final String POINT_VALUE= ".";
	private static final String EMPTY= "";
	private static final String ZERO_RIGHT_PADDING= "0";
	
	private static final String MSG_ESTADO_ACTUALIZADO = "Actualizado.";
	private static final String MSG_ESTADO_OBLIGATORIO = "Obligatorio.";
	public static final String MSG_ESTADO_OPCIONAL = "Opcional.";
	
	private static final String STR_ESTADO_ACTUALIZADO = "0";
	private static final String STR_ESTADO_OBLIGATORIO = "1";
	private static final String STR_ESTADO_OPCIONAL = "2";
	
	
	public int compararVersionContraBD(String current, String currentBD) {

		String value1 = current.replace(POINT_VALUE, EMPTY);
		String value2 = currentBD.replace(POINT_VALUE, EMPTY);

		if (value1.length() > value2.length()) {
			value2 = String.format("%-".concat(String.valueOf(value1.length())).concat("s"), value2).replace(" ",
					ZERO_RIGHT_PADDING);
		} else if (value1.length() < value2.length()) {
			value1 = String.format("%-".concat(String.valueOf(value2.length())).concat("s"), value1).replace(" ",
					ZERO_RIGHT_PADDING);
		}

		int i1 = Integer.parseInt(value1);
		int i2 = Integer.parseInt(value2);

		if (i1 == i2) {
			return 0;
		} else if (i1 < i2) {
			return 1;
		} else {
			return 2;
		}
	}
	

}
