package com.techarsh.dkvstore.store;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.techarsh.dkvstore.store.models.KeyValuePair;

/**
 * @author Harsh Arora <aroraharsh90@gmail.com>
 *
 */
@Component
public class Store {
	private final ConcurrentHashMap<String, KeyValuePair> store = new ConcurrentHashMap<>();

	public void put(KeyValuePair kvp) {
		if (store.containsKey(kvp.getKey())) {
			// if(kvp.getDateTime().isAfter(store.get(kvp.getKey()).getDateTime())) {
			store.put(kvp.getKey(), kvp);
			// }
		} else {
			store.put(kvp.getKey(), kvp);
		}
	}

	public KeyValuePair get(String key) {
		if (store.containsKey(key)) {
			return store.get(key);
		}
		return null;
	}
}
