package com.Ashim.CommerceEngine.models;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
class Product extends BaseModel {
    private String title;
    private String description;
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    private String image;
}


/*
   1                    1
Product -------------Category  ==> M:1  ==> category id in teh product Table
   M                    1
 */