package com.example.capstoneexercise.Service;

import com.example.capstoneexercise.Model.TheUser;
import com.example.capstoneexercise.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<TheUser> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(TheUser user) {
        userRepository.save(user);
    }

    public Boolean updateUser(Integer id, TheUser user) {
        TheUser oldUser = userRepository.getById(id);
        if (oldUser == null) {
            return false;
        }
        oldUser.setBalance(user.getBalance());
        oldUser.setRole(user.getRole());
        oldUser.setEmail(user.getEmail());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        userRepository.save(oldUser);
        return true;
    }

    public Boolean deleteUser(Integer id) {
        TheUser user = userRepository.getById(id);
        if (user == null) {
            return false;
        }
        userRepository.delete(user);
        return true;
    }

    public boolean isValidUserID(String userID) {
        for (TheUser user : userRepository.findAll()) {
            if (user.getId().equals(userID)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkUserBalance(String userID, double amount) {
        for (TheUser user : userRepository.findAll()) {
            if (user.getId().equals(userID)) {
                if (user.getBalance() >= amount) {
                    return true;
                }
            }
        }
        return false;
    }

    public void deductPriceFromUserBalance(Integer userID, double productPrice) {
        for (TheUser user : userRepository.findAll()) {
            if (user.getId().equals(userID)) {
                user.setBalance(user.getBalance() - productPrice);
                userRepository.save(user);
            }
        }
    }

    public Integer sendGift(Integer senderUserID, String receiverUsername, double moneyGiftAmount) {
        boolean canSend = false;
        for (TheUser user : userRepository.findAll()) {
            if (user.getId().equals(senderUserID)) {
                if (user.getBalance() >= moneyGiftAmount) {
                    canSend = true;
                } else return 2; // balance not enough
            }

        }
        if (!canSend) // if cansend is still false, then it didnt even find the sender id
            return 1; //incorrect id

        for (TheUser user : userRepository.findAll()) {
            if (user.getUsername().equals(receiverUsername)) {
                if (canSend) {
                    deductPriceFromUserBalance(senderUserID, moneyGiftAmount);
                    user.setBalance(user.getBalance() + moneyGiftAmount);
                    userRepository.save(user);
                    return 3; // gift sent successfully
                }
            }
        }
        return 4; //incorrect username
    }

}






