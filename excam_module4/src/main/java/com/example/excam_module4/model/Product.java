package com.example.excam_module4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", columnDefinition = "INT")
    private Integer id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 5, max = 50, message = "Tên sản phẩm phải có từ 5 đến 50 ký tự")
    @Column(name = "name", columnDefinition = "VARCHAR(50)", unique = true)
    private String name;

    @NotNull(message = "Giá không được để trống")
    @DecimalMin(value = "100000", message = "Giá phải tối thiểu 100.000 VNĐ")
    @Column(name = "price", columnDefinition = "DECIMAL(15,2)")
    @NumberFormat(pattern = "#,###")
    private BigDecimal price;

    @NotBlank(message = "Status không được để trống")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_product_type", columnDefinition = "INT")
    private ProductType productType;
}
