package service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import lk.eyepax.demo.dto.CustomerDTO;
import lk.eyepax.demo.entity.Customer;
import lk.eyepax.demo.repository.CustomerRepository;
import lk.eyepax.demo.service.impl.CustomerServiceImpl;

public class CustomerServiceTest {

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	@Mock
	CustomerRepository customerRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllCustomersTest() {
		List<Customer> list = new ArrayList<>();

		list.add(new Customer("t001", "ranga", "Inga"));
		list.add(new Customer("t002", "sanka", "han"));
		list.add(new Customer("t003", "nuwan", "hon"));

		when(customerRepository.findAll()).thenReturn(list);

		// test
		List<CustomerDTO> empList = customerServiceImpl.findAllCustomers();

		assertEquals(3, empList.size());
		verify(customerRepository, times(1)).findAll();
	}

	@Test
	public void getCustomerByIdTest() {
		when(customerRepository.findById("c1")).thenReturn(Optional.of(new Customer("c1", "Ranga", "Inga")));

		CustomerDTO customerDTO = customerServiceImpl.findCustomer("c1");

		assertEquals("c1", customerDTO.getId());
		assertEquals("Ranga", customerDTO.getName());
		assertEquals("Inga", customerDTO.getAddress());
	}

	@Test
	public void createCustomerTest() {

		CustomerDTO customerDTO = new CustomerDTO("c0011", "Sanka", "Ingiriya");

		Customer customer = new Customer("c0011", "Sanka", "Ingiriya");

		when(customerRepository.save(customer)).thenReturn(new Customer("c0011", "Sanka", "Ingiriya"));

		assertEquals(customerDTO.getId(), customerRepository.save(customer).getId());
		assertEquals(customerDTO.getName(), customerRepository.save(customer).getName());
		assertEquals(customerDTO.getAddress(), customerRepository.save(customer).getAddress());

	}

	

}
