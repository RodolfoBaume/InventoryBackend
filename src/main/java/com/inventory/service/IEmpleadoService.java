package com.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.dto.EmpleadoDto;
import com.inventory.entity.Empleado;


public interface IEmpleadoService {

	List<Empleado> findAll();
	
	Page<Empleado> findAllPage(Pageable pageable);
	
	Empleado findById(Long idEmpleado);
	
	Empleado createEmpleado(EmpleadoDto empleado, Long idUsuario);
	
	Empleado deleteEmpleado(Long idEmpleado);
	
	Empleado updateEmpleado(Long idEmpleado, EmpleadoDto empleado);
}
