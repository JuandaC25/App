package com.app.backend.controller;

import com.app.backend.dto.UserCreateRequest;
import com.app.backend.dto.UserUpdateRequest;
import com.app.backend.model.User;
import com.app.backend.service.UserService;
import com.app.backend.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.Http.MediaType;
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
    @PreAuthorize("hasRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userservice.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userservice.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userservice.create(user));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request){
        try{
            return ResponseEntity.ok(userservice.update(id, request));
        } catch (RuntimeException e){
            if(e.getMessage().contains("No tiene permisos")){
                return ResponseEntity.status(403).body(new MessageResponse(e.getMessage()));
            }
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
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