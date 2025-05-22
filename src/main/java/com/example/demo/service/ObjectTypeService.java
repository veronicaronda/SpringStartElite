package com.example.demo.service;

//import com.example.demo.dto.FilterDTO;
//import com.example.demo.dto.FilterDocenteDTO;
//import com.example.demo.dto.ResponsePageDTO;
//import com.example.demo.repository.ObjectTypeRepository;
//import com.example.demo.repository.ObjectTypeRepositoryImpl;
//import org.aspectj.apache.bcel.generic.ObjectType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.function.Function;
//import java.util.stream.Collectors;

//@Service
//public class ObjectTypeService {
//    @Autowired
//    private ObjectTypeRepository repo;
//
//    public ResponsePageDTO<FilterDocenteDTO> findByCustomFilter(
//            List<FilterDTO> filters,
//            int page,
//            int size,
//            List<String> sortList,
//            Sort.Direction sortOrder,
//            Function<ObjectType, FilterDocenteDTO> mapper) {
//
//        // Prepare sorting
//        Sort sort = sortList != null && !sortList.isEmpty()
//                ? Sort.by(sortOrder, sortList.toArray(new String[0]))
//                : Sort.unsorted();
//
//        // Prepare pagination
//        Pageable pageable = PageRequest.of(page, size, sort);
//
//        // Call repository to apply filtering logic
//        Page<ObjectType> pageResult = repo.customSearch(filters, pageable);
//
//        // Map entities to DTOs
//        List<FilterDocenteDTO> dtoList = pageResult.getContent()
//                .stream()
//                .map(mapper)
//                .collect(Collectors.toList());
//
//        // Return the paginated DTOs
//        return new ResponsePageDTO<>(
//                dtoList,
//                pageResult.getNumber(),
//                pageResult.getSize(),
//                pageResult.getTotalElements(),
//                pageResult.getTotalPages(),
//                pageResult.isLast()
//        );
//    }
//}
