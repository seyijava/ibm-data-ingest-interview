package com.ibm.gbs.processor;


import com.ibm.gbs.binding.BalanceListenerBinding;
import com.ibm.gbs.schema.Balance;
import com.ibm.gbs.schema.Customer;
import com.ibm.gbs.schema.CustomerBalance;
import com.ibm.gbs.services.RecordBuilder;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.StreamJoined;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;

import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.messaging.handler.annotation.SendTo;

import java.time.Duration;


@EnableBinding(BalanceListenerBinding.class)
public class CustomerBalanceProcessor {

    @Autowired
    private RecordBuilder recordBuilder;

    @StreamListener
    @SendTo("customerBalance-output-channel")
    public KStream<String, CustomerBalance> process(@Input("customer-input-channel") KStream<String, Customer> customerData,
                                                    @Input("balance-input-channel") KStream<String, Balance> balanceData) {
        KStream<String, CustomerBalance> customerBalanceKStream = customerData.join(balanceData, (c, b) ->
                recordBuilder.getCustomerBalance(c, b), JoinWindows.of(Duration.ofMillis(100)));
        return customerBalanceKStream;

    }

}
