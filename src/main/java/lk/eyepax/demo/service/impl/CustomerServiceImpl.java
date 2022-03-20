package lk.eyepax.demo.service.impl;

import lk.eyepax.demo.dto.CustomerDTO;
import lk.eyepax.demo.entity.Customer;
import lk.eyepax.demo.repository.CustomerRepository;
import lk.eyepax.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> findAllCustomers() {
        List<Customer> all = customerRepository.findAll();
      ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        for (Customer customer : all) {
            customerDTOS.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress()));
        }
        return customerDTOS;
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDTO findCustomer(String id) {
        Customer customer = customerRepository.findById(id).get();
        return new CustomerDTO(customer.getId(), customer.getName(),customer.getAddress());
    }

    @Override
    public void saveCustomer(String id, CustomerDTO customerDTO) {
        if (!id.equals(customerDTO.getId())){
            throw new RuntimeException("Customer IDs are mismatched");
        }
        Customer customer = new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress());
        customerRepository.save(customer);
    }

    @Override
    public boolean updateCustomer(String id, CustomerDTO customerDTO) {
        if (customerRepository.existsById(id)) {
            saveCustomer(id, customerDTO);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(String id) {
        customerRepository.deleteById(id);
        return true;
    }

    @Override
    public long customersCount() {
        return customerRepository.count();
    }

    @Override
    public List<CustomerDTO> findCustomersByAddressLike(String address) {
        List<Customer> customers = customerRepository.mataOniAddressMeaAkurenPatanGanna(address + "%");
//   3:     var customers =  customerRepository.mataOniAddressMeaAkurenPatanGanna(null,address );
       ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        for (Customer customer : customers) {
            customerDTOS.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress()));
        }


        return customerDTOS;
    }

}
