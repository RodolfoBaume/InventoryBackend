package com.inventory.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.TipoProductoDto;
import com.inventory.entity.TipoMovimiento;
import com.inventory.entity.TipoProducto;
import com.inventory.repository.ITipoProductoRepository;

@Service
public class TipoProductoService {

	@Autowired
	private ITipoProductoRepository tipoProductoRepository;

	// Consulta todos
	@Transactional(readOnly = true)
	public List<TipoProducto> findAll() {
		return (List<TipoProducto>) tipoProductoRepository.findAll(Sort.by("idTipoProducto"));
	}

	// consulta todos para paginación
	@Transactional(readOnly = true)
	public Page<TipoProducto> findAllPage(Pageable pageable) {
		return tipoProductoRepository.findAll(pageable);
	}

	// consulta por id
	@Transactional(readOnly = true)
	public TipoProducto findById(Long idTipoProducto) {
		return tipoProductoRepository.findById(idTipoProducto).orElse(null);
	}

	// Crear
	@Transactional
	public TipoProducto createTipoProducto(TipoProductoDto tipoProducto) {
		TipoProducto tipoProductoEntity = new TipoProducto();
		tipoProductoEntity.setTipoProducto(tipoProducto.tipoProducto());
		tipoProductoEntity.setStatus(tipoProducto.status());
		return tipoProductoRepository.save(tipoProductoEntity);
	}

	// Eliminar
	public TipoMovimiento deleteTipoMovimiento(Long idTipoMovimiento) {
		tipoProductoRepository.deleteById(idTipoMovimiento);
		return null;
	}

	// Modificar
	@Transactional
	public TipoProducto updateTipoProducto(Long idTipoProducto, TipoProductoDto tipoProducto) {
		TipoProducto tipoProductoEntity = tipoProductoRepository.findById(idTipoProducto).orElseThrow(
				() -> new NoSuchElementException("Tipo de Producto no encontrado con el ID: " + idTipoProducto));
		tipoProductoEntity.setTipoProducto(tipoProducto.tipoProducto());
		tipoProductoEntity.setStatus(tipoProducto.status());
		return tipoProductoRepository.save(tipoProductoEntity);
	}

}
