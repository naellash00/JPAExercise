package com.example.capstoneexercise.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Check(constraints = "role IN ('admin', 'customer') AND balance >= 0")
public class TheUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username Cannot Be Empty")
    @Size(min = 5, max = 15, message = "Username Cannot Be Less Than 5 Letters")
    @Column(columnDefinition = "varchar(15) not null unique")
    private String username;

    @NotEmpty(message = "Password Cannot Be Empty")
    @Size(min = 6, max = 10, message = "Password Cannot Be Less Than 6")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "Password Must Contains Characters and Digits")
    @Column(columnDefinition = "varchar(10) not null")
    private String password;

    @NotEmpty(message = "Email Cannot Be Empty")
    @Email(message = "Enter A Valid Email")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

    @NotEmpty(message = "Role Cannot Be Empty")
    @Pattern(regexp = "(admin|customer)", message = "Role Must Be Either Admin or Customer")
    @Column(columnDefinition = "varchar(10) not null")
    private String role;

    @NotNull(message = "Balance Cannot Be Empty")
    @Min(value = 0, message = "Balance Cannot Be Less Than Zero")
    @Column(columnDefinition = "double not null")
    private double balance;
}
