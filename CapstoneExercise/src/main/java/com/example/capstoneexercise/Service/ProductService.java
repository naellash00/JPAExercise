package com.example.capstoneexercise.Service;

import com.example.capstoneexercise.Model.Product;
import com.example.capstoneexercise.Repository.CategoryRepository;
import com.example.capstoneexercise.Repository.MerchantRepository;
import com.example.capstoneexercise.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Boolean addProduct(Product product) {
        for (int i = 0; i < categoryRepository.findAll().size(); i++) {
            if (product.getCategoryID().equals(categoryRepository.findAll().get(i).getId())) {
                productRepository.save(product);
                return true;
            }
        }
        return false;
    }

    public Integer updateProduct(Integer id, Product product) {
        Product oldProduct = productRepository.getById(id);
        if (oldProduct == null) {
            return 1; // product not found
        }
        for (int i = 0; i < categoryRepository.findAll().size(); i++) {
            System.out.println(1);
            if (product.getCategoryID().equals(categoryRepository.findAll().get(i).getId())) {
                oldProduct.setName(product.getName());
                oldProduct.setPrice(product.getPrice());
                oldProduct.setCategoryID(product.getCategoryID());
                productRepository.save(oldProduct);
            }
            else return 3; // category not found
        }
        return 2; //correct
    }

    public Boolean deleteProduct(Integer id){
        Product product = productRepository.getById(id);
        if(product==null){
            return false;
        }
        productRepository.delete(product);
        return true;
    }


}




