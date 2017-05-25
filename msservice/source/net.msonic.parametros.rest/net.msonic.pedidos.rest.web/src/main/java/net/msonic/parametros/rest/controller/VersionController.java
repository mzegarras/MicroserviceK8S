package net.msonic.parametros.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.msonic.parametros.rest.common.TransactionResponse;
import net.msonic.parametros.rest.service.VersionService;

@RestController()
@RequestMapping("/version")
public class VersionController {
	
	@Autowired
	private VersionService versionService;
	
	
	@RequestMapping(value = "/v1/list/{version}/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public TransactionResponse list(@PathVariable("version") String version,@PathVariable("type") String type) {
		
		return versionService.list(version, type, "1");
		
	}
	

}
