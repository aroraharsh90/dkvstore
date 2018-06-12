package com.techarsh.dkvstore.requests;

import java.util.HashMap;
import java.util.Map;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import com.techarsh.dkvstore.servers.ServerNode;
import com.techarsh.dkvstore.store.models.KeyValuePair;

/**
 * @author Harsh Arora <aroraharsh90@gmail.com>
 *
 */
public class Requests {

	@Async
	public static void sendHeartBeatRequest(ServerNode serverNode) {
		final String uri = "http://{hostname}:{port}/heartbeat";
		Map<String, String> params = new HashMap<String, String>();
		params.put("hostname", serverNode.getAddress().getHostAddress().toString());
		params.put("port", serverNode.getPort());
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.getForObject(uri, String.class, params);

		} catch (Exception e) {
			serverNode.setOnline(false);
			return;
		}
		serverNode.setOnline(true);
	}

	@Async
	public static void sendStoreSyncRequest(ServerNode serverNode, KeyValuePair kvp) {
		if (serverNode == null || kvp == null) {
			return;
		}

		final String uri = "http://{hostname}:{port}/sync/{key}?val={val}";

		Map<String, String> params = new HashMap<String, String>();
		params.put("hostname", serverNode.getAddress().getHostAddress().toString());
		params.put("port", serverNode.getPort());
		params.put("key", kvp.getKey());
		params.put("val", kvp.getVal());

		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.getForObject(uri, String.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Async
	// public static void sendStoreSyncRequest(ServerNode serverNode, KeyValuePair
	// kvp) {
	// if (serverNode == null || kvp == null) {
	// return;
	// }
	//
	// ObjectMapper mapper = new ObjectMapper();
	// mapper.registerModule(new JodaModule());
	// mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	//
	// final String uri = "http://{hostname}:{port}/sync";
	//
	// Map<String, String> params = new HashMap<String, String>();
	// params.put("hostname", serverNode.getAddress().getHostAddress().toString());
	// params.put("port", serverNode.getPort());
	//
	// try {
	//
	// // Convert object to JSON string
	// String jsonInString = mapper.writeValueAsString(kvp);
	// HttpHeaders headers = new HttpHeaders();
	// headers.setContentType(MediaType.APPLICATION_JSON);
	//
	// HttpEntity entity = new HttpEntity(jsonInString, headers);
	// // System.out.println(params.get("hostname") + ":" + params.get("port"));
	//
	// RestTemplate restTemplate = new RestTemplate();
	// try {
	// ResponseEntity<String> out = restTemplate.exchange(uri, HttpMethod.POST,
	// entity, String.class, params);
	// System.out.println(out.getBody());
	// } catch (Exception e) {
	// System.out.println(e.toString());
	// }
	//
	// } catch (JsonGenerationException e) {
	// e.printStackTrace();
	// } catch (JsonMappingException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
}
