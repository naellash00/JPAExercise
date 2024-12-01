package com.example.capstoneexercise.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
@Check(constraints = "length(name) > 3")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Category Name Cannot Be Empty")
    @Size(min = 3, message = "Name Cannot Be Less Than 3 Letters")
    @Column(columnDefinition = "varchar(15)")
    private String name;
}
