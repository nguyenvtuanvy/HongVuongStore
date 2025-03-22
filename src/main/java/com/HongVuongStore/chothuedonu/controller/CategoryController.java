package com.HongVuongStore.chothuedonu.controller;

import com.HongVuongStore.chothuedonu.entity.Category;
import com.HongVuongStore.chothuedonu.service.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories() {
        Set<Category> categories = categoryService.findALllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        String message = categoryService.saveCategory(category);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        String message = categoryService.updatedCategory(id, category);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        String message = categoryService.deleteCategory(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
