package com.latihan.demoapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SearchName {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Email")
    private String email;
}
