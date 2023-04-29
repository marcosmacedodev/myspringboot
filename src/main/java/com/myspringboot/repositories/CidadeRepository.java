package com.myspringboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myspringboot.model.Cidade;
import com.myspringboot.model.Estado;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
	@Transactional(readOnly=true)
	List<Cidade> findAllByEstado(Estado estado);
}
