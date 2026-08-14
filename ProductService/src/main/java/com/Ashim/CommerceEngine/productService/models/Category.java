package com.Ashim.CommerceEngine.productService.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "categories")
public class Category extends BaseModel{

   // @Column(unique = true)
    private String value;

    /*
    @JsonManagedReference
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "category",
            cascade = CascadeType.REMOVE)
    private List<Product> products;
    */

    private String description;

}
