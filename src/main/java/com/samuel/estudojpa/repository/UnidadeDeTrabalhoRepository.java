package com.samuel.estudojpa.repository;

import com.samuel.estudojpa.model.UnidadeDeTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeDeTrabalhoRepository extends JpaRepository<UnidadeDeTrabalho, Long> {
}
