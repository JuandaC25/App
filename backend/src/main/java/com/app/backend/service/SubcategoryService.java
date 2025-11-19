package com.app.backend.service;

import com.app.backend.model.Subcategory;
import com.app.backend.repository.CategoryRepository;
import com.app.backend.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubcategoryService {
    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Subcategory> findAll(){
        return subcategoryRepository.findAll();
    }
    
    public List<Subcategory> findByCategoryId(Long categoryId){
        return subcategoryRepository.findByCategoryId(categoryId);
    }

    public Subcategory findById(Long id){
        return subcategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Subcategoría no encontrada"));
    }

    public Subcategory create(Subcategory subcategory){
        return subcategoryRepository.save(subcategory);
    }

    public Subcategory update(Long id, Subcategory subcategoryDetails){
        Subcategory subcategory = findById(id);
        subcategory.setName(subcategoryDetails.getName());
        subcategory.setDescription(subcategoryDetails.getDescription());
        subcategory.setActive(subcategoryDetails.getActive());
        
        // Cargar la categoría desde la base de datos si se proporciona
        if (subcategoryDetails.getCategory() != null && subcategoryDetails.getCategory().getId() != null) {
            subcategory.setCategory(categoryRepository.findById(subcategoryDetails.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada")));
        }
        
        return subcategoryRepository.save(subcategory);
    }

    public void delete(Long id){
        Subcategory subcategory = findById(id);
        subcategoryRepository.delete(subcategory);
    }
}
