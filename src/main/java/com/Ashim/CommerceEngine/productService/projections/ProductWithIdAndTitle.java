package com.Ashim.CommerceEngine.productService.projections;

public interface ProductWithIdAndTitle {
    Long getId();

    String getTitle();

//    String getPrice();
}

/*
Yes — this is a Spring Data JPA Projection.
What is a Projection?
A projection tells Spring Data JPA:
	"I don't need the complete Product object. Just give me these specific fields."

Your repository might have:
List<ProductWithIdAndTitle> findAllBy();
Then Spring Data sees:
ProductWithIdAndTitle
and understands:
	"Only return id and title from Product."
So instead of:
Product
 ├── id
 ├── title
 ├── price
 ├── description
 ├── image
 └── category
you get:
ProductWithIdAndTitle
 ├── id
 └── title

1. Less data retrieved
If you only need 2 fields, don't retrieve 10 fields.
2. Better performance
Especially useful when the table contains many columns or relationships.
3. Cleaner API response
For an endpoint like:
GET /products/titles
you might only want:
[
  {
    "id": 1,
    "title": "iPhone 15"
  },


 */