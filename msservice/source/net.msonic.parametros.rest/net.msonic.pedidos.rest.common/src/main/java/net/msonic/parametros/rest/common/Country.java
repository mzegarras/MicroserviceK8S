package net.msonic.parametros.rest.common;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("description")
	private String description;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
