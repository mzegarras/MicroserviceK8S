package net.msonic.parametros.rest.config;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {


	@Autowired
	public void configeJackson(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
	    jackson2ObjectMapperBuilder.serializationInclusion(Include.NON_NULL);
	    jackson2ObjectMapperBuilder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}
	
	
}