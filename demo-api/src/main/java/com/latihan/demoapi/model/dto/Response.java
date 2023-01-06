package com.latihan.demoapi.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.latihan.demoapi.model.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private Boolean status;

    private List<String> messageError = new ArrayList<>();

    private T payload;

}
