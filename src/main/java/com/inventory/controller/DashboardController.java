package com.inventory.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.service.ProductoService;
import com.inventory.service.ProveedorService;
import com.inventory.service.UbicacionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api/dashboard")
public class DashboardController {
	@Autowired
    private ProveedorService proveedorService;

    @Autowired
    private UbicacionService ubicacionService;
    
    @Autowired
    private ProductoService productoService;

    @GetMapping("/totales")
    public ResponseEntity<Map<String, Long>> getTotales() {
        long totalProveedores = proveedorService.countProveedores();
        long totalUbicaciones = ubicacionService.countUbicaciones();
        long totalProductos = productoService.countProductos();

        Map<String, Long> totales = new HashMap<>();
        totales.put("totalProveedores", totalProveedores);
        totales.put("totalUbicaciones", totalUbicaciones);
        totales.put("totalProductos", totalProductos);

        return ResponseEntity.ok(totales);
    }
}
