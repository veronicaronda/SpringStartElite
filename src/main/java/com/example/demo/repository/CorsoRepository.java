package com.example.demo.repository;

import com.example.demo.entity.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorsoRepository extends JpaRepository<Corso,Long> {

    @NativeQuery(value = "SELECT * FROM Corso c WHERE nome ILIKE %:nome%")
    List<Corso> findCorsoByName(String nome);

    @NativeQuery(value = "SELECT * FROM Corso c WHERE anno_accademico = :annoAccademico")
    List<Corso> findCorsoByYear(int annoAccademico);

    @Query("SELECT c FROM Corso c JOIN c.docente d WHERE d.nome ILIKE %:keyword% OR d.cognome ILIKE %:keyword%")
    List<Corso> findCorsoByTeacher(@Param("keyword") String keyword);

}
