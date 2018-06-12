package com.techarsh.dkvstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techarsh.dkvstore.servers.SyncAllServers;
import com.techarsh.dkvstore.store.Store;
import com.techarsh.dkvstore.store.models.KeyValuePair;

/**
 * @author Harsh Arora <aroraharsh90@gmail.com>
 *
 */
@RestController
public class StoreController {

	@Autowired
	private Store store;

	@Autowired
	private SyncAllServers syncAllServers;

	@RequestMapping(value = "/get/{key}")
	public String get(@PathVariable String key) {
		KeyValuePair kvp = store.get(key);
		if (kvp == null) {
			return "Key-Value pair doesn't exist";
		} else {
			return kvp.getVal();
		}
	}

	@RequestMapping(value = "/set/{key}")
	public String set(@PathVariable String key, @RequestParam String val) {

		// RestTemplate restTemplate = new RestTemplate();
		// String res = restTemplate.getForObject("http://localhost:8080/get/abcd",
		// String.class);
		KeyValuePair kvp = new KeyValuePair(key, val);
		store.put(kvp);

		syncAllServers.sync(kvp);

		return "Success";
	}
}