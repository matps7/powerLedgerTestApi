package com.powerldger.code;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
class CodeApplicationTests {

	@Autowired

	private MockMvc mockMvc;

	private static String readFileAsString(String file) throws Exception {
		return new String(Files.readAllBytes(Paths.get(file)));
	}

	@Before
	@Test
	public void testARun() throws Exception {
		ClassPathResource classPathResourceFOut = new ClassPathResource("templates/InputListBatteries.json");
		String testPayload = readFileAsString(classPathResourceFOut.getFile().getAbsolutePath());

		mockMvc.perform(post("/saveListBatteries")
						.content(testPayload)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testBGetRange() throws Exception {

		mockMvc.perform(get("/getByPostCodeRange")
						.param("start","0")
						.param("end","1234")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(
						status().isOk());
	}

	@Test
	public void testCGetRange() throws Exception {

		mockMvc.perform(get("/getByPostCodeRange")
						.param("start","0")
						.param("end","1")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(
						status().isNoContent());
	}

}
