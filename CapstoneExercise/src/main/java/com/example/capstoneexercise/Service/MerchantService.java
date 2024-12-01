package com.example.capstoneexercise.Service;

import com.example.capstoneexercise.Model.Merchant;
import com.example.capstoneexercise.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // for the dependency injection with merchant repository
public class MerchantService {
    private final MerchantRepository merchantRepository;

    public List<Merchant> getAllMerchants(){
        return merchantRepository.findAll();
    }

    public void addMerchant(Merchant merchant){
        merchantRepository.save(merchant);
    }

    public Boolean updateMerchant(Integer id, Merchant merchant){
        // check if there is a merchant with this id
        Merchant oldMerchant = merchantRepository.getById(id);
        if(oldMerchant==null){ // if it dosent exist
            return false;
        }
        // update the old merchant values with the new ones
        oldMerchant.setName(merchant.getName());
        // and save them
        merchantRepository.save(oldMerchant);
        return true;
    }

    public Boolean deleteMerchant(Integer id){
        Merchant merchant = merchantRepository.getById(id);
        if(merchant==null){
            return false;
        }
        merchantRepository.delete(merchant);
        return true;
    }

}
