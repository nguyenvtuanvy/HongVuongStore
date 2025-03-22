package com.HongVuongStore.chothuedonu.service.product;
import com.HongVuongStore.chothuedonu.entity.Category;
import com.HongVuongStore.chothuedonu.entity.Product;
import com.HongVuongStore.chothuedonu.repository.CategoryRepo;
import com.HongVuongStore.chothuedonu.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    public String saveProduct(String name, double price, Long categoryId, String image) {
        Product product = new Product();
        Category category = categoryRepo.findById(categoryId).orElseThrow();
        product.setCategory(category);
        product.setName(name);
        product.setPrice(price);
        product.setImage(image);

        productRepo.save(product);

        return "Product saved successfully!";
    }

    public String updatedProduct(Long id,String name, double price, Long categoryId, String image) {
        Product updatedProduct = productRepo.findById(id).orElseThrow();
        Category category = categoryRepo.findById(categoryId).orElseThrow();

        updatedProduct.setName(name);
        updatedProduct.setPrice(price);
        updatedProduct.setCategory(category);
        updatedProduct.setImage(image);

        productRepo.save(updatedProduct);

        return "Product updated successfully!";
    }

    public String deleteProduct(Long productId) {
        productRepo.deleteById(productId);

        return "Product deleted successfully!";
    }

    public Set<Product> findAllProducts(){
        return new HashSet<>(productRepo.findAllProducts());
    }

    public Set<Product> findProductsByCategoryId(Long categoryId){
        return productRepo.findAllProductsByCategoryId(categoryId);
    }
}
