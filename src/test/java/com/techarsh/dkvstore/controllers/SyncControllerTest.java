package com.techarsh.dkvstore.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.techarsh.dkvstore.store.models.KeyValuePair;

@RunWith(SpringRunner.class)
@WebMvcTest(SyncController.class)
public class SyncControllerTest {
	 @Autowired
	 private MockMvc mvc;

	 @MockBean
	 private SyncController syncController;
	 
	 @Test
	 public void setKeyValue() throws Exception {
		 KeyValuePair kvp = new KeyValuePair("abc", "def");
		 when(syncController.set(kvp.getKey(), kvp.getVal())).thenReturn("OK");

	     mvc.perform(get("/sync/{key}?val={val}",kvp.getKey(), kvp.getVal())
	               .contentType(MediaType.ALL))
	               .andExpect(status().isOk()).andExpect(content().string("OK"));
	 }
}
