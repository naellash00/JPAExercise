package com.example.capstoneexercise.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Check(constraints = "stock>=10")
public class MerchantStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Product ID Cannot Be Empty")
    @Column(columnDefinition = "int not null")
    private Integer productID;

    @NotNull(message = "Merchant ID Cannot Be Empty")
    @Column(columnDefinition = "int not null")
    private Integer merchantID;

    @NotNull(message = "Stock Cannot Be Empty")
    @Min(value = 10, message = "Stock Cannot Be Less Than 10")
    @Column(columnDefinition = "int not null")
    private int stock;
}
