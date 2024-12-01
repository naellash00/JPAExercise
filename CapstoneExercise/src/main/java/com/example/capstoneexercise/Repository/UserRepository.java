package com.example.capstoneexercise.Repository;

import com.example.capstoneexercise.Model.TheUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<TheUser,Integer> {
}
