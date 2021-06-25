package de.bnd.coding.sample.demo.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

// Record is a simple, more simple version of a class
// Its purpose is to simply hold data as it can not have any functions
// However, Getters and Setters are automatically included

// Defines the order of the Json Properties returned
@JsonPropertyOrder({"userName", "userPassword", "firstName", "lastName", "zipCode"})
public record UserDto (
    // JsonProperty maps a variable to a JSON field. The names do not have to match.
    @JsonProperty("userName") String userName,
    @JsonProperty("userPassword") String userPassword,
    @JsonProperty("firstName") String firstName,
    @JsonProperty("lastName") String lastName,
    @JsonProperty("zipCode") String zipCode
) { }