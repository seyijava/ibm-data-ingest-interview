package com.ibm.gbs.services;

import com.ibm.gbs.schema.Balance;
import com.ibm.gbs.schema.Customer;
import com.ibm.gbs.schema.CustomerBalance;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RecordBuilder {

    public CustomerBalance getCustomerBalance(Customer customer, Balance balance){
        CustomerBalance customerBalance = new CustomerBalance();
        customerBalance.setBalance(balance.getBalance());
        customerBalance.setCustomerId(customer.getCustomerId());
        customerBalance.setPhoneNumber(customer.getPhoneNumber());
        customerBalance.setAccountId(customer.getAccountId());
        return customerBalance;
    }
}
