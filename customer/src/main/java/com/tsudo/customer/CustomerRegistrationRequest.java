package com.tsudo.customer;

public record CustomerRegistrationRequest(
    String firstName,
    String lastName,
    String email){
}
