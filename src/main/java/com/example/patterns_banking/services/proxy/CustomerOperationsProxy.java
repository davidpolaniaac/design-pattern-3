package com.example.patterns_banking.services.proxy;

import com.example.patterns_banking.dtos.AccountDTO;
import com.example.patterns_banking.dtos.CustomerDTO;
import com.example.patterns_banking.factory.AccountFactoryProvider;
import com.example.patterns_banking.models.Account;
import com.example.patterns_banking.models.Customer;
import com.example.patterns_banking.repositories.IAccountRepository;
import com.example.patterns_banking.repositories.ICustomerRepository;
import com.example.patterns_banking.services.commands.*;
import org.springframework.stereotype.Component;

@Component
public class CustomerOperationsProxy implements ICustomerOperations {
  private final ICustomerRepository customerRepository;

  public CustomerOperationsProxy(ICustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public Customer createCustomer(CustomerDTO customerDTO) {
    validateEmail(customerDTO.getEmail());
    ICommand<Customer> command = new CreateCustomerCommand(customerRepository, customerDTO);
    return command.execute();
  }

  private void validateEmail(String email) {
    if (email.toLowerCase().endsWith("@yahoo.com")) {
      throw new IllegalArgumentException("Los correos de Yahoo no están permitidos.");
    }
  }
}
