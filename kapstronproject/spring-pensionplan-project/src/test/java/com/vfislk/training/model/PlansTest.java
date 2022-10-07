package com.vfislk.training.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vfislk.training.repository.IPlansRepository;



class PlansTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;
	
	
	@MockBean
	IPlansRepository plansRepository;
	
	Plans plans;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}
	
	@BeforeEach
	void setUp() throws Exception {
		plans=new  Plans();
		System.out.println("called before each testcases");
	}
	
	
	@Test
	public void findAll() throws Exception{
		
		List<Plans> plansList=new ArrayList<>();
		Mockito.when(plansRepository.findAll()).thenReturn(plansList);
		mockMvc.perform(MockMvcRequestBuilders.
				get("/plans").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		    
	}

	@Test
	public void updatePlan() {
		Plans plans=plansRepository.findById(1).get();
		plans.setTerm("20");
		plansRepository.save(plans);
	   assertNotEquals("10-42", plansRepository.findById(1).get().getTerm());
	}
	
//	public void getPlansList() {
//		String uri="/plans";
//		MvcResult mvcResult=mvc
//	}
	
	@AfterEach
	void tearDown() throws Exception {
		plans=null;
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

}
