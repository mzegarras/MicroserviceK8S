package net.msonic.parametros.rest.common;

public class TransactionResponse {
	
	public TransactionResponse(){
		detRpta = new DetalleRespuesta();
	}
	
	private String codRpta;
	private String desRpta;
	
	public String getCodRpta() {
		return codRpta;
	}
	public void setCodRpta(String codRpta) {
		this.codRpta = codRpta;
	}
	public String getDesRpta() {
		return desRpta;
	}
	public void setDesRpta(String desRpta) {
		this.desRpta = desRpta;
	}
	
	private DetalleRespuesta detRpta;

	public DetalleRespuesta getDetRpta() {
		return detRpta;
	}
	public void setDetRpta(DetalleRespuesta detRpta) {
		this.detRpta = detRpta;
	}
	
	
	

}
