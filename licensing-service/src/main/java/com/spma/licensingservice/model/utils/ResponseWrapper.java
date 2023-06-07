package com.spma.licensingservice.model.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Setter
@Getter
@JsonInclude(NON_NULL)
@AllArgsConstructor
public class ResponseWrapper {
    private Object data;
    private Object metadata;
    private List<ErrorMessage> errors;
}