package com.techarsh.dkvstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.techarsh.dkvstore.store.Store;
import com.techarsh.dkvstore.store.models.KeyValuePair;

/**
 * @author Harsh Arora <aroraharsh90@gmail.com>
 *
 */
@RestController
public class SyncController {

	@Autowired
	private Store store;

	@RequestMapping(value = "/sync/{key}")
	public String set(@PathVariable("key") String key, @RequestParam("val") String val) {

		KeyValuePair kvp = new KeyValuePair(key, val);
		try {
			store.put(kvp);
			return "OK";
		} catch (Exception e){
			return "Fail";
		}
	}

	@RequestMapping(value = "/sync", method = RequestMethod.POST)
	public ResponseEntity<String> sync(@RequestBody String jsonKVP) {

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JodaModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		try {
			KeyValuePair kvp = mapper.readValue(jsonKVP, KeyValuePair.class);

			store.put(kvp);

			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}
	}
}
