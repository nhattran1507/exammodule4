package com.example.excam_module4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", columnDefinition = "INT")
    private Integer id;

    @NotNull(message = "Loại sản phẩm không được để trống")
    @Column(name = "name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;
}
