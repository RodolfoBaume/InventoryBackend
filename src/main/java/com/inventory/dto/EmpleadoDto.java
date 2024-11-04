package com.inventory.dto;

import com.inventory.entity.Empleado;
import com.inventory.entity.Usuario;

public record EmpleadoDto(
		long idEmpleado, 
		String nombre, 
		String apellidoPaterno, 
		String apellidoMaterno,  
		long nss, 
		String curp, 
		String rfc, 
		String puesto, 
		String observaciones,  
		Usuario usuario) {

	public EmpleadoDto(Empleado empleado) {
		this(empleado.getIdEmpleado(), empleado.getNombre(), empleado.getApellidoPaterno(), empleado.getApellidoMaterno(), empleado.getNss(), empleado.getCurp(), empleado.getRfc(), empleado.getPuesto(), empleado.getObservaciones(), empleado.getUsuario() );
	}
}
