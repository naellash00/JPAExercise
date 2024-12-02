package com.example.capstoneexercise.Controller;

import com.example.capstoneexercise.ApiResponse.ApiResponse;
import com.example.capstoneexercise.Model.TheUser;
import com.example.capstoneexercise.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid TheUser user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid TheUser user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isUpdated = userService.updateUser(id, user);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("User Updated Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User Not Found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        Boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("User Deleted Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User Not Found"));
    }

    @PutMapping("/send/gift/{senderuserid}/{receiverusername}/{moneygiftamount}")
    public ResponseEntity sendGift(@PathVariable Integer senderuserid, @PathVariable String receiverusername, @PathVariable double moneygiftamount) {
        Integer sendingGiftResult = userService.sendGift(senderuserid, receiverusername, moneygiftamount);
        if (sendingGiftResult == 1)
            return ResponseEntity.status(400).body(new ApiResponse("User ID Not Found"));
        else if (sendingGiftResult == 2)
            return ResponseEntity.status(400).body(new ApiResponse("Your Balance Not Enough"));
        else if (sendingGiftResult == 4)
            return ResponseEntity.status(400).body(new ApiResponse("Incorrect Username"));

        return ResponseEntity.status(200).body(new ApiResponse("Gift Sent Successfully"));

    }


}











