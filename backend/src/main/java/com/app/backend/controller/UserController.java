package com.app.backend.controller;

import com.app.backend.dto.UserCreateRequest;
import com.app.backend.dto.UserUpdateRequest;
import com.app.backend.model.User;
import com.app.backend.service.UserService;
import com.app.backend.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")

public class UserController {
    
    @Autowired
    private UserService userservice;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<java.util.List<User>> getAllUsers(){
        return ResponseEntity.ok(userservice.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userservice.findById(id));
        } catch (RuntimeException e) {
            if (e.getMessage().contains("no encontrado")) {
                return ResponseEntity.status(404).body(new MessageResponse("Usuario no encontrado"));
            }
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createUser(@RequestBody com.app.backend.dto.UserCreateRequest request){
        return ResponseEntity.ok(userservice.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request){
        try{
            return ResponseEntity.ok(userservice.update(id, request));
        } catch (RuntimeException e){
            if(e.getMessage().contains("No tiene permisos")){
                return ResponseEntity.status(403).body(null);
            }
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable Long id) {
        try{
            userservice.delete(id);
            return ResponseEntity.ok(new MessageResponse("Usuario eliminado exitosamente"));
        } catch (RuntimeException e){
            if(e.getMessage().contains("No tiene permisos")){
                return ResponseEntity.status(403).body(new MessageResponse(e.getMessage()));
            }
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
