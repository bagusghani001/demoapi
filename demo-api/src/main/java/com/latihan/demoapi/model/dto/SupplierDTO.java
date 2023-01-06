package com.latihan.demoapi.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {
    @JsonProperty("Name")
    @NotEmpty(message = "Name is Required")
    private String name;
    @JsonProperty("Address")
    @NotEmpty(message = "Address is Required")
    private String address;
    @JsonProperty("Email")
    @Email(message = "Email is Not Valid")
    private String email;

}
