package com.sandav.pruebatecnica.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class SpaceshipControllerIntegrationTest {
	@Autowired
    private MockMvc mockMvc;
	
	private String rootApi = "/api/v1/spaceships";
	
	@Test
	void contextLoads() throws Exception {
		assertThat(mockMvc).isNotNull();
	}
	
	@Test
	public void when_findAll_then_status_200() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get(rootApi + "/")
                .contentType(MediaType.APPLICATION_JSON).queryParam("page", "0").queryParam("size", "20"))
        .andExpect(MockMvcResultMatchers
                .status()
                .isOk())
        .andExpect(MockMvcResultMatchers
                .content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));		
	}

}
