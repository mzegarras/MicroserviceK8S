package net.msonic.parametros.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import net.msonic.parametros.rest.common.Country;
import net.msonic.parametros.rest.service.CountryService;

@RestController()
@RequestMapping("/country")
public class CountryController {

	
	@Autowired
	private CountryService countryService;
	
	@RequestMapping(value = "/v1/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Country> listAll() {
		
		
		return countryService.list();
		
	}
	
}
