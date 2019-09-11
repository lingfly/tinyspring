package com.lingfly.tinyioc.entity;

import lombok.Data;

@Data
public class Car {
    String brand;
    Long id;

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", id=" + id +
                '}';
    }
}
