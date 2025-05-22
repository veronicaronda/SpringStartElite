package com.example.demo.repository;

import com.example.demo.dto.FilterDTO;
import org.aspectj.apache.bcel.generic.ObjectType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ObjectTypeRepositoryCustom {
    Page<ObjectType> customSearch(List<FilterDTO> filters, Pageable pageable);
}
