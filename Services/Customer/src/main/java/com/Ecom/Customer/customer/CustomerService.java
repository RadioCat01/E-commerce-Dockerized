package com.Ecom.Customer.customer;

import com.Ecom.Customer.exception.CustomerNotFoundException;
import com.ctc.wstx.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    public final CustomerRepo repo;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {

        var Cus = repo.findByCustomId(request.Id());

        if(Cus.isEmpty()) {
            var customer = repo.save(mapper.toCustomer(request));
            return customer.getId();
        }
        return null;

    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repo.findById(request.Id()).orElseThrow(()-> new CustomerNotFoundException(
                String.format("cannot update customer %s", request.Id())
        ));

        mergerCustomer(customer, request);
        repo.save(customer);

    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if((request.address()!=null)){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
      return repo.findAll().stream().map(mapper::fromCustomer).collect(Collectors.toList());
    }

    public Boolean existsById(String id) {
        return repo.findById(id).isPresent();
    }

    public CustomerResponse findById(String id) {
        return repo.findById(id).map(mapper::fromCustomer).orElseThrow(()-> new CustomerNotFoundException(String.format("Customer not found")));
    }

    public void deleteCustomer(String id) {
        repo.deleteById(id);
    }
}

















