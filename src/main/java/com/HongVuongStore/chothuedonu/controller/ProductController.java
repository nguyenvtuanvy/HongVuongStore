package com.HongVuongStore.chothuedonu.controller;

import com.HongVuongStore.chothuedonu.entity.Category;
import com.HongVuongStore.chothuedonu.entity.Product;
import com.HongVuongStore.chothuedonu.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        Set<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductByCategoryId(@PathVariable Long id) {
        Set<Product> products = productService.findProductsByCategoryId(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> createProduct(@RequestParam("name") String name,
                                           @RequestParam("price") double price,
                                           @RequestParam("categoryId") Long categoryId,
                                           @RequestParam("image") String image) {
        String message = productService.saveProduct(name, price, categoryId, image);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        String message = productService.deleteProduct(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestParam Long id,
                                           @RequestParam("name") String name,
                                           @RequestParam("price") double price,
                                           @RequestParam("categoryId") Long categoryId,
                                           @RequestParam("image") String image) {
        String message = productService.updatedProduct(id, name, price, categoryId, image);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
