package com.techarsh.dkvstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Harsh Arora <aroraharsh90@gmail.com>
 *
 */
@SpringBootApplication
@ComponentScan("com.techarsh")
public class DkvstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(DkvstoreApplication.class, args);
	}
}
