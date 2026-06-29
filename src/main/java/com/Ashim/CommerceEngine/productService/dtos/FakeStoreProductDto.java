package com.Ashim.CommerceEngine.productService.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto { // For every 3rd party I will create a DTO object, that will be same as this json and which will map the json output in the java object

    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
