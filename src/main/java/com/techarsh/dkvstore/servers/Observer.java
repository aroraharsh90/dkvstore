package com.techarsh.dkvstore.servers;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.techarsh.dkvstore.properties.ServerNodeProperties;
import com.techarsh.dkvstore.requests.Requests;

/**
 * @author Harsh Arora <aroraharsh90@gmail.com>
 *
 */
@Service
@EnableScheduling
public class Observer {

	@Autowired
	private ServerNodeProperties serverNodeProperties;

	private ArrayList<ServerNode> nodesList;

	public Observer() {
	}

	@PostConstruct
	public void init() {
		this.nodesList = serverNodeProperties.getNodesList();
	}

	@Scheduled(fixedRate = 2000)
	public void run() {
		// System.out.println(nodesList.get(0).toString());
		for (ServerNode node : nodesList) {
			Requests.sendHeartBeatRequest(node);
		}
	}

	public ArrayList<ServerNode> getAvailableNodes() {
		ArrayList<ServerNode> serverNodeList = new ArrayList<>();
		for (ServerNode node : nodesList) {
			if (node.isOnline()) {
				serverNodeList.add(node);
			}
		}
		return serverNodeList;
	}
}
