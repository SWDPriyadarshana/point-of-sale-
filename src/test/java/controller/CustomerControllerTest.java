package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import backend.AbstractTest;
import lk.eyepax.demo.dto.CustomerDTO;

public class CustomerControllerTest extends AbstractTest {
	private static final Logger LOGGER = Logger.getLogger(CustomerControllerTest.class.getName());

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	/**
	 * check retrieve customer
	 * @throws Exception
	 */

	@Test
	public void getCustomersList() throws Exception {
		String uri = "/api/v1/customers";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();

		LOGGER.info("response status " + status);
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		CustomerDTO[] customerDTOs = super.mapFromJson(content, CustomerDTO[].class);
		assertTrue(customerDTOs.length > 0);
	}
	/**
	 * save customer using put method ,put not returns anything
	 * @throws Exception
	 */
	
	@Test
	   public void saveCustomer() throws Exception {
	     
	      CustomerDTO customerDTO=new CustomerDTO();
	      customerDTO.setId("3");
	      String id=customerDTO.getId();
	      String uri = "/api/v1/customers/"+id;
	      customerDTO.setName("Ranga");
	      customerDTO.setAddress("Ingiriya");
	      String inputJson = super.mapToJson(customerDTO);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      LOGGER.info("response status " + status);
	      assertEquals(200, status);
	   

	   }
	
	/**
	 * this method use to check update customer using post method,when success returns true
	 * @throws Exception
	 */
	@Test
	   public void updateCustomer() throws Exception {
	      String uri = "/api/v1/customers/c001";
	      CustomerDTO customerDTO=new CustomerDTO();
	      customerDTO.setId("c001");
	      customerDTO.setName("Sanka");
	      customerDTO.setAddress("Ingiriya");
	      String inputJson = super.mapToJson(customerDTO);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      LOGGER.info("response status " + status);
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	     assertEquals(content, "true");
	   }
	/**
	 * this method use to delete customer,when it becomes success ,returns true
	 * @throws Exception
	 */
	
	 @Test
	   public void deleteProduct() throws Exception {
	      String uri = "/api/v1/customers/3";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "true");
	   }
	
	

}
