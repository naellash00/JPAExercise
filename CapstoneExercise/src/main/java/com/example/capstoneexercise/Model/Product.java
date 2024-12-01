package com.example.capstoneexercise.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
@Check(constraints = "price >= 0")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name Cannot Be Empty")
    @Size(min = 3, message = "Name Must Be Longer Than 3 Letters")
    @Column(columnDefinition = "varchar(15) not null")
    private String name;

    @NotNull(message = "Price Cannot Be Empty")
    @Min(value = 0, message = "Price Cannot Be Less Than Zero")
    @Column(columnDefinition = "double not null")
    private double price;

    @NotNull(message = "Category ID Cannot Be Empty")
    @Column(columnDefinition = "int not null")
    private Integer categoryID;

    //private ArrayList<String> reviews;
}
