package com.example.capstoneexercise.Controller;

import com.example.capstoneexercise.ApiResponse.ApiResponse;
import com.example.capstoneexercise.Model.Product;
import com.example.capstoneexercise.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isAdded = productService.addProduct(product);
        if (isAdded) {
            return ResponseEntity.status(200).body(new ApiResponse("Product Added Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Incorrect Category ID For The Product"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        // Boolean isUpdated = productService.updateProduct(id, product);
        Integer updateResult = productService.updateProduct(id, product);
        if (updateResult == 2) {
            return ResponseEntity.status(200).body(new ApiResponse("Product Updated Successfully"));
        } else if (updateResult == 3) {
            return ResponseEntity.status(400).body(new ApiResponse("Incorrect Category ID For The Product"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        Boolean isDeleted = productService.deleteProduct(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Product Deleted Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product Not Found"));
    }


}
