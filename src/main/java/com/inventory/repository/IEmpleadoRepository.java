package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.entity.Empleado;


@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, Long>{

}
