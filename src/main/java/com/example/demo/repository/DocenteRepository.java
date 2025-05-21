package com.example.demo.repository;



import com.example.demo.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
    @Query("SELECT DISTINCT d FROM Docente d LEFT JOIN FETCH d.corsi cd ORDER BY d.id ASC")
    List<Docente> findAllWithCorsiOrderBy();
    @NativeQuery(value = "SELECT * FROM DOCENTE d WHERE d.nome ILIKE %:docenteNome% ")
    List<Docente> findDocenteByName(@Param("docenteNome") String docenteNome);
    @NativeQuery(value = "SELECT * FROM DOCENTE d WHERE d.cognome ILIKE %:docenteCognome%")
    List<Docente> findDocenteBySurname(@Param("docenteCognome") String docenteCognome);

    List<Docente> findAllByOrderByCognomeAsc();

}
