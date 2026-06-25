package com.Ashim.CommerceEngine.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseModel {
   // @Column(unique = true, nullable = false)
   // @NonNull
    private String title;
    private double price;

    @ManyToOne(cascade = {CascadeType.PERSIST,  CascadeType.REMOVE})
    private Category category;
    private String description;
    private String image;
}


/*
   1                    1
Product -------------Category  ==> M:1  ==> category id in teh product Table
   M                    1

 Eager: fetch the attributess of internal objects along with fetching the externa object - join operation required
LAZY: don’t fetch the attributes of internal objects along with fetching the internal object..  = LAZY me no join
==> by default collection attributes are fetched in LAZY and Non collection are fetch in EAGER way

 */