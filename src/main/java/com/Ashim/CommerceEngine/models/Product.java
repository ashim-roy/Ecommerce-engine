package com.Ashim.CommerceEngine.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseModel {
    @Column(unique = true)
    @NonNull
    private String title;
    private double price;
    @ManyToOne
    private Category category;
    private String description;
    private String image;
}


/*
   1                    1
Product -------------Category  ==> M:1  ==> category id in teh product Table
   M                    1
 */