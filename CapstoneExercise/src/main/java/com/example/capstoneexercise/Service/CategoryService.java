package com.example.capstoneexercise.Service;

import com.example.capstoneexercise.Model.Category;
import com.example.capstoneexercise.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public Boolean updateCategory(Integer id, Category category) {
        // check if a category with the id already exist
        Category oldCategory = categoryRepository.getById(id);
        if (oldCategory == null) { // if its null then it dosent exist and return false
            return false;
        }
        //if it exist set its old values to the new ones of category and SAVE THEM
        oldCategory.setName(category.getName());
        categoryRepository.save(oldCategory);
        return true;
    }

    public Boolean deleteCategory(@PathVariable Integer id) {
        Category category = categoryRepository.getById(id);
        if(category==null){
            return false;
        }
        categoryRepository.delete(category);
        return true;
    }

}
