package com.deepak.ProductService.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category extends BaseModel{
    private String title;
    private double price;
    private Category category;
    private String description;
    private String image;
}
