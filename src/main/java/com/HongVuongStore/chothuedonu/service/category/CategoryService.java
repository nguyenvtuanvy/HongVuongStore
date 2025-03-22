package com.HongVuongStore.chothuedonu.service.category;

import com.HongVuongStore.chothuedonu.entity.Category;
import com.HongVuongStore.chothuedonu.repository.CategoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepo categoryRepo;

    public String saveCategory(Category category){
        categoryRepo.save(category);

        return "Category created successfully!";
    }

    public String updatedCategory(Long id,Category category){
        Category updatedCategory = categoryRepo.findById(id).orElseThrow();

        updatedCategory.setName(category.getName());

        categoryRepo.save(updatedCategory);

        return "Category updated successfully!";
    }

    public String deleteCategory(Long id){
        categoryRepo.deleteById(id);

        return "Category deleted successfully!";
    }

    public Set<Category> findALllCategories(){
        return new HashSet<>(categoryRepo.findAll());
    }
}
