package com.app.backend.controller;

import com.app.backend.model.Category;
import com.app.backend.service.CategoryService;
import com.app.backend.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MwediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acces.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/category/{categoryId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<Product> getProductByCategoryId(@PathVariable Long categoryId){
        return ResponseEntity.ok(productService.findCategoryById(categoryId));
    }

    @GetMapping("/Subcategory/{subcategoryId}")
    @PreAuthorize("hasRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<List<Product>> getProductsBySubcategoryId(@PathVariable Long subcategoryId){
        return ResponseEntity.ok(productService.findBySubcategoryId(subcategoryId));
    }

    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDINADOR')")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        return ResponseEntity.ok(productService.update(id, product));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> deleteProduct(
        @PathVariable Long id) {
            productService.delete(id);
            return ResponseEntity.ok(new MessageResponse ("Producto eliminado exitosamente"));
        }
}