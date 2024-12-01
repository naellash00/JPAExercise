package com.example.capstoneexercise.Service;

import com.example.capstoneexercise.Model.Merchant;
import com.example.capstoneexercise.Model.MerchantStock;
import com.example.capstoneexercise.Repository.MerchantRepository;
import com.example.capstoneexercise.Repository.MerchantStockRepository;
import com.example.capstoneexercise.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final MerchantStockRepository merchantStockRepository;
    private final ProductRepository productRepository;
    private final MerchantRepository merchantRepository;

    public List<MerchantStock> getAllMerchantStocks() {
        return merchantStockRepository.findAll();
    }

//    public Integer addMerchantStock(MerchantStock merchantStock) {
//        // check productID and merchantID
//        for (int i = 0; i < merchantRepository.findAll().size(); i++) {
//            for (int j = 0; j < productRepository.findAll().size(); j++) {
//                if (merchantStock.getMerchantID().equals(merchantRepository.findAll().get(i).getId())
//                        && merchantStock.getProductID().equals(productRepository.findAll().get(j).getId())) {
//                    merchantStockRepository.save(merchantStock);
//                    return 0; // everything correct
//                } else return 2; // incorrect product id
//            }
//
//        }
//        return 1; // incorrect merchant id
//    }

    public Boolean addMerchantStock(MerchantStock merchantStock) {
        for (int i = 0; i < merchantRepository.findAll().size(); i++) {
            for (int j = 0; j < productRepository.findAll().size(); j++) {
                if (merchantStock.getMerchantID().equals(merchantRepository.findAll().get(i).getId())
                        && merchantStock.getProductID().equals(productRepository.findAll().get(j).getId())) {
                    merchantStockRepository.save(merchantStock);
                    return true;
                }
            }
        }
        return false;
    }

    public Integer updateMerchantStock(Integer id, MerchantStock merchantStock) {
        MerchantStock oldMerchantStock = merchantStockRepository.getById(id);
        if (oldMerchantStock == null) {
            return 1; // incorrect merchant stock id
        }

        if (productRepository.existsById(merchantStock.getProductID())) {
            // if product id exist
            oldMerchantStock.setProductID(merchantStock.getProductID());
            if (merchantRepository.existsById(merchantStock.getMerchantID())) {
                oldMerchantStock.setMerchantID(merchantStock.getMerchantID());
                oldMerchantStock.setStock(merchantStock.getStock());
                merchantStockRepository.save(oldMerchantStock);
            } else return 3; //incorrect merchant id
        } else return 2; // incorrect product id
        return 0; // updated successfully
    }

    public Boolean deleteMerchantStock(Integer id){
        MerchantStock merchantStock = merchantStockRepository.getById(id);
        if(merchantStock==null){
            return false;
        }
        merchantStockRepository.delete(merchantStock);
        return true;
    }


}
