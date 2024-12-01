package com.example.capstoneexercise.Controller;

import com.example.capstoneexercise.ApiResponse.ApiResponse;
import com.example.capstoneexercise.Model.Category;
import com.example.capstoneexercise.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getAllCategories() {
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("Category Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        Boolean isUpdated = categoryService.updateCategory(id, category);
        if (isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("Category Updated Successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("Category Not Found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {
        Boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Category Deeleted Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category Not Found"));
    }

}
