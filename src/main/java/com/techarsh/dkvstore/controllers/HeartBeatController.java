package com.techarsh.dkvstore.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Harsh Arora <aroraharsh90@gmail.com>
 *
 */
@RestController
public class HeartBeatController {
	@RequestMapping(value = "/heartbeat")
	public String heartbeat() {

		return "ACK";
	}
}
