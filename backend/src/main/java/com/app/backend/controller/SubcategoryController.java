package com.app.backend.controller;

import com.app.backend.model.Subcategory;
import com.app.backend.service.SubcategoryService;
import com.app.backend.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subcategories")
@CrossOrigin(origins = "*")
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<java.util.List<Subcategory>> getALLSubcategories(){
        return ResponseEntity.ok(subcategoryService.findAll());
    }

    @GetMapping("/category/{categoryId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<java.util.List<Subcategory>> getSubcategoriesByCategoryId(@PathVariable Long categoryId){
        return ResponseEntity.ok(subcategoryService.findByCategoryId(categoryId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<?> getSubcategoryById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(subcategoryService.findById(id));
        } catch (RuntimeException e) {
            if (e.getMessage().contains("no encontrado")) {
                return ResponseEntity.status(404).body(new MessageResponse("Subcategoría no encontrada"));
            }
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<Subcategory> createSubcategory(@RequestBody Subcategory subcategory){
        return ResponseEntity.ok(subcategoryService.create(subcategory));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<Subcategory> updateSubcategory(@PathVariable Long id, @RequestBody Subcategory subcategory){
        return ResponseEntity.ok(subcategoryService.update(id, subcategory));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> deleteSubcategory(@PathVariable Long id) {
        subcategoryService.delete(id);
        return ResponseEntity.ok(new MessageResponse("Subcategoría eliminada exitosamente"));
    }

}