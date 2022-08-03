package com.ibm.gbs.binding;


import com.ibm.gbs.schema.Balance;
import com.ibm.gbs.schema.Customer;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

import com.ibm.gbs.schema.CustomerBalance;


public interface BalanceListenerBinding {

    @Input("customer-input-channel")
    KStream<String, Customer> customerInputStream();

    @Input("balance-input-channel")
    KStream<String, Balance> balanceInputStream();

    @Output("customerBalance-output-channel")
    KStream<String, CustomerBalance> customerBalanceOutputStream();
}
