package com.example.patterns_banking.services.proxy;

import com.example.patterns_banking.dtos.CustomerDTO;
import com.example.patterns_banking.models.Account;
import com.example.patterns_banking.models.Customer;

public interface ICustomerOperations {
  Customer createCustomer(CustomerDTO customerDTO);
}
