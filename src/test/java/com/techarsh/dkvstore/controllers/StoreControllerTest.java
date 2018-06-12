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

import com.techarsh.dkvstore.store.Store;
import com.techarsh.dkvstore.store.models.KeyValuePair;

@RunWith(SpringRunner.class)
@WebMvcTest(SyncController.class)
public class StoreControllerTest {
	 @Autowired
	 private MockMvc mvc;

	 @MockBean
	 private StoreController storeController;
	 
	 @Test
	 public void setKeyValue() throws Exception {
		 KeyValuePair kvp = new KeyValuePair("abc", "def");
		 when(storeController.set(kvp.getKey(), kvp.getVal())).thenReturn("OK");

	     mvc.perform(get("/set/{key}?val={val}",kvp.getKey(), kvp.getVal())
	               .contentType(MediaType.ALL))
	               .andExpect(status().isOk()).andExpect(content().string("OK"));
	 }
	 
	 @Test
	 public void getKeyValue() throws Exception {
		 KeyValuePair kvp = new KeyValuePair("abc", "def");
		 when(storeController.get(kvp.getKey())).thenReturn(kvp.getVal());

	     mvc.perform(get("/get/{key}",kvp.getKey())
	               .contentType(MediaType.ALL))
	               .andExpect(status().isOk()).andExpect(content().string(kvp.getVal()));
	 }
	 
	 @Test
	 public void getKeyValueNoExist() throws Exception {
		 KeyValuePair kvp = new KeyValuePair("abc", "def");
		 Store store = new Store();
		 store.put(kvp);
		 when(storeController.get("adf")).thenReturn("Key-Value Pair doesn't exist");

	     mvc.perform(get("/get/{key}","adf")
	               .contentType(MediaType.ALL))
	               .andExpect(status().isOk()).andExpect(content().string("Key-Value Pair doesn't exist"));
	 }
}
