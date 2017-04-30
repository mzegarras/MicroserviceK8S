package net.msonic.parametros.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.msonic.parametros.rest.common.Country;
import net.msonic.parametros.rest.dao.CountryRepository;
import net.msonic.parametros.rest.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	public List<Country> list() {
		
		return countryRepository.list();
	}

}
