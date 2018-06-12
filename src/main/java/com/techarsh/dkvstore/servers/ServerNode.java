package com.techarsh.dkvstore.servers;

import java.net.InetAddress;

/**
 * @author Harsh Arora <aroraharsh90@gmail.com>
 *
 */
public class ServerNode {
	private boolean online;
	private InetAddress address;
	private String port;
	private boolean self;

	public ServerNode(boolean self, InetAddress address, String port) {
		this.self = self;
		this.address = address;
		this.online = false;
		this.port = port;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		if (this.online != online) {
			this.online = online;
			System.out.println(
					"Server at " + address.getHostName() + ":" + port + " is now " + (online ? "online" : "offline"));
		}
	}

	public InetAddress getAddress() {
		return address;
	}

	public boolean isSelf() {
		return self;
	}

	public String getPort() {
		return port;
	}

	@Override
	public String toString() {
		return "ServerNode [online=" + online + ", address=" + address + ", port=" + port + ", self=" + self + "]";
	}

}
