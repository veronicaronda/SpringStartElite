package com.example.demo.dto;

import com.example.demo.enumerator.FilterComparisonOperator;
import lombok.Data;

import java.util.List;
@Data
public class FilterDTO {
    private String field;
    private FilterComparisonOperator operator;
    private Object value;
    private List<String> columns;

    public FilterDTO(String field, FilterComparisonOperator comparisonOperator, Object value) {
        this.field = field;
        this.operator = comparisonOperator;
        this.value = value;
    }
}
