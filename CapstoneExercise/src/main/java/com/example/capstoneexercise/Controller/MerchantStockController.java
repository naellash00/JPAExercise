package com.example.capstoneexercise.Controller;

import com.example.capstoneexercise.ApiResponse.ApiResponse;
import com.example.capstoneexercise.Model.MerchantStock;
import com.example.capstoneexercise.Repository.MerchantStockRepository;
import com.example.capstoneexercise.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant/stock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchantStocks() {
        return ResponseEntity.status(200).body(merchantStockService.getAllMerchantStocks());
    }

//    @PostMapping("/add")
//    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {
//        if (errors.hasErrors()) {
//            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
//        }
//        Integer addResult = merchantStockService.addMerchantStock(merchantStock);
//        if (addResult == 1) {
//            return ResponseEntity.status(400).body(new ApiResponse("Incorrect Merchant ID"));
//        } else if (addResult == 2) {
//            return ResponseEntity.status(200).body(new ApiResponse("Incorrect Product ID"));
//        }
//        return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Added Successfully"));
//    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isAdded = merchantStockService.addMerchantStock(merchantStock);
        if (isAdded) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Added Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Incorrect ID"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer id, @RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Integer updateResult = merchantStockService.updateMerchantStock(id, merchantStock);
        if (updateResult == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("Merchant Stock ID Not Found"));
        } else if (updateResult == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("Incorrect Product ID"));
        } else if (updateResult == 3) {
            return ResponseEntity.status(400).body(new ApiResponse("Incorrect Merchant ID"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Updated Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id){
        Boolean isDeleted = merchantStockService.deleteMerchantStock(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Deleted Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant Stock Not Found"));
    }

}





