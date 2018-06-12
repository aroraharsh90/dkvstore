package com.techarsh.dkvstore.servers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techarsh.dkvstore.requests.Requests;
import com.techarsh.dkvstore.store.models.KeyValuePair;

/**
 * @author Harsh Arora <aroraharsh90@gmail.com>
 *
 */
@Component
public class SyncAllServers {

	private Observer observer;

	@Autowired
	public SyncAllServers(Observer observer) {
		this.observer = observer;
	}

	public void sync(KeyValuePair kvp) {
		ArrayList<ServerNode> nodesList = observer.getAvailableNodes();
		for (ServerNode node : nodesList) {
			if (!node.isSelf()) {
				Requests.sendStoreSyncRequest(node, kvp);
			}
		}
	}
}
