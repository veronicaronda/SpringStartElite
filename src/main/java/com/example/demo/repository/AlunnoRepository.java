package com.example.demo.repository;

import com.example.demo.entity.Alunno;
import com.example.demo.entity.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunnoRepository extends JpaRepository<Alunno, Long> {
//    @Query("SELECT DISTINCT a FROM Alunno a LEFT JOIN FETCH a.corsi ORDER BY a.id ASC")
//    List<Alunno> getAlunniWithCorsiOrderBy();

    @NativeQuery(value = "SELECT * FROM Alunni a WHERE nome ILIKE %:keyword% OR cognome ILIKE %:keyword%")
    List<Alunno> getAlunniByNameOrSurname(@Param("keyword") String keyword);

    @NativeQuery(value = "SELECT * FROM Alunni a WHERE voto >=6")
    List<Alunno> getAlunniPassed();


}
