package com.example.demo.repository;

import com.example.demo.dto.FilterDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.aspectj.apache.bcel.generic.ObjectType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

//public class ObjectTypeRepositoryImpl implements ObjectTypeRepositoryCustom {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public Page<ObjectType> customSearch(List<FilterDTO> filters, Pageable pageable) {
//        // TODO: build a dynamic query using CriteriaBuilder or JPQL based on filters
//        // This is just a placeholder example
//
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<ObjectType> query = cb.createQuery(ObjectType.class);
//        Root<ObjectType> root = query.from(ObjectType.class);
//
//        // Example filter application:
//        List<Predicate> predicates = new ArrayList<>();
//        if (filters != null) {
//            for (FilterDTO filter : filters) {
//                if ("name".equalsIgnoreCase(filter.getField()) && filter.getValue() != null) {
//                    predicates.add(cb.like(root.get("name"), "%" + filter.getValue() + "%"));
//                }
//                // Add more filters based on filter.getOperator(), etc.
//            }
//        }
//
//        query.where(predicates.toArray(new Predicate[0]));
//
//        TypedQuery<ObjectType> typedQuery = entityManager.createQuery(query);
//        typedQuery.setFirstResult((int) pageable.getOffset());
//        typedQuery.setMaxResults(pageable.getPageSize());
//
//        List<ObjectType> resultList = typedQuery.getResultList();
//
//        // Count query
//        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
//        countQuery.select(cb.count(countQuery.from(ObjectType.class)))
//                .where(predicates.toArray(new Predicate[0]));
//        Long total = entityManager.createQuery(countQuery).getSingleResult();
//
//        return new PageImpl<>(resultList, pageable, total);
//    }
//}
