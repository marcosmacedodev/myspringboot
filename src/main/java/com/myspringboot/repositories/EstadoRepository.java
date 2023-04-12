package com.myspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myspringboot.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
