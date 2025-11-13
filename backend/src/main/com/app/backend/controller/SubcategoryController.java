package com.app.backend.controller;

import com.app.backend.model.Subcategory;
import com.app.backend.service.SubcategoryService;
import com.app.backend.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MwediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acces.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class SubcategoryController {

    @Autowired
    private SubcategoryService SubcategoryService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<List<Subcategory>> getAllCategories(){
        return ResponseEntity.ok(SubcategoryService.findAll());
    }

    @Getmappig("/category/{categoryId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<Subcategory> getSubcategoryById(@PathVariable Long id){
        return ResponseEntity.ok(SubcategoryService.findById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<List<Subcategory>> getAllSubcategories(){
        return ResponseEntity.ok(SubcategoryService.findAll());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<Subcategory> createSubcategoryById(@RequestBody Subcategory subcategory){
        return ResponseEntity.ok(subcategoryService.create(Subcategory));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<Subcategory> updateSubcategory (@PathVariable Long id, @RequestBody Subcategory subcategory){
        return ResponseEntity.ok(subcategoryService.update(id, subcategory));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> deleteSubcategory(
        @PathVariable Long id) {
            SubcategoryService.delete(id);
            return ResponsEntity.ok(new MessageResponse ("Categoria eliminada exisotsamente"));
        }

}