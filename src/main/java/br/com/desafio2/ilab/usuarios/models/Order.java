package br.com.desafio2.ilab.usuarios.models;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Order {

    private Long id;

    private Integer userId;

    private Double totalValue;

    private String productsDescription;

    private Timestamp createdAt;

    private String status;
}