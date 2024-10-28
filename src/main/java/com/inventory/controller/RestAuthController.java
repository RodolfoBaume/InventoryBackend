package com.inventory.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.dto.AuthRespuestaDto;
import com.inventory.dto.LoginDto;
import com.inventory.dto.PasswordDto;
import com.inventory.dto.RegistroResponseDto;
import com.inventory.dto.UsuarioActualDto;
import com.inventory.dto.UsuarioDto;
import com.inventory.service.UsuarioAuthService;


@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api/auth")
public class RestAuthController {

    private UsuarioAuthService usuarioAuthService;

    @Autowired
    public RestAuthController(UsuarioAuthService usuarioService) {
        this.usuarioAuthService = usuarioService;
    }

    // Método para poder registrar usuarios con role "user"
    @PostMapping("/register2")
    public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioDto dtoRegistro, @RequestParam String role) {

        ResponseEntity<RegistroResponseDto> resp = null;
        String response = "";

        try {
            resp = usuarioAuthService.registrarUsuario(dtoRegistro, role);
        } catch (Exception e) {
            System.out.println(e.toString());
            response = "Credenciales Invalidas.";
            return new ResponseEntity<String>(response, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return resp;

    }

    // Método para poder logear un usuario y obtener un token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dtoLogin) {
        ResponseEntity<AuthRespuestaDto> token = null;
        String response = "";

        try {
            token = usuarioAuthService.login(dtoLogin);
        } catch (Exception e) {
            System.out.println(e.toString());
            response = "Credenciales Invalidas.";
            return new ResponseEntity<String>(response, HttpStatus.FORBIDDEN);
        }
        return token;

    }

    @GetMapping("/current-user")
    public ResponseEntity<?> usuarioActual(Principal principal) {
        ResponseEntity<UsuarioActualDto> currentUser = null;
        String response = "";

        try {
            currentUser = usuarioAuthService.obtenerUsuarioActual(principal);
        } catch (Exception e) {
            System.out.println(e.toString());
            response = "Imposible obtener el usuario actual";
            return new ResponseEntity<String>(response, HttpStatus.FORBIDDEN);
        }
        return currentUser;
    }

    @PostMapping("/new-credentials")
    public ResponseEntity<?> newCredentials(Principal principal, @RequestBody PasswordDto passwordDto) {
        ResponseEntity<?> response = null;
        Map<String, Object> resp = new HashMap<>();

        try {
            response = usuarioAuthService.cambiarPassword(principal, passwordDto);
            // dtoLogin.setUsername(currentUser.getBody().getUsername());
            // dtoLogin.setPassword(passwordDto.getCurrentPassword());

        } catch (Exception e) {
            System.out.println(e.toString());
            resp.put("mensaje", "Controller.-No es posible cambiar el password");
            return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.FORBIDDEN);
        }

        return response;
    }
}