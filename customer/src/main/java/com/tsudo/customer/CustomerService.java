package com.tsudo.customer;

import com.tsudo.amqp.RabbitMQMessageProducer;
import com.tsudo.clients.fraud.FraudCheckResponse;
import com.tsudo.clients.fraud.FraudClient;
import com.tsudo.clients.notification.NotificationClient;
import com.tsudo.clients.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public record CustomerService(
        CustomerRepository customerRepository,
        RestTemplate restTemplate,
        FraudClient fraudClient,
        NotificationClient notificationClient,
        RabbitMQMessageProducer messageProducer
) {
    public void registerCustomer(CustomerRegistrationRequest request){
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email()).build();
        //todo: check if email valid
        //todo: check if email not taken
        //todo: check if fraudster
        this.customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(customer.getId(),customer.getEmail(), "successfully registered");

        messageProducer.publish(notificationRequest, "internal.exchange", "internal.notification.routing-key");

        log.info("notification successfully sent to rabbitmq, pinged with {}", notificationRequest);


    }
}
