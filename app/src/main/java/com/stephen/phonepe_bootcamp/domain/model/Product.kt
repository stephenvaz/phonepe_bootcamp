package com.stephen.phonepe_bootcamp.domain.model

data class Product(
    val name: String,
    val price: Double,
    val shippingMethod: String,
    val imageUrl: String? = null
)

val dummyProducts = listOf(
    Product(
        name = "Aashirvaad Atta - Superior MP Whole Wheat, No Maida (Pouch)",
        price = 548.00,
        shippingMethod = "Same Day Shipping",
        imageUrl = "https://cdn.zeptonow.com/production///tr:w-1000,ar-2560-2560,pr-true,f-auto,q-80/cms/product_variant/8c103774-f622-42ab-871d-95e12a9f783c.jpeg"
    ),
    Product(
        name = "Bingo! Mad Angles Achaari Masti Chips",
        price = 20.00,
        shippingMethod = "Free Shipping",
//        imageUrl = "https://cdn.zeptonow.com/production///tr:w-1000,ar-1021-1021,pr-true,f-auto,q-80/cms/product_variant/0b6b9847-21ce-4a14-8735-fb6c066d7cd3.jpeg"
    ),
//    give different products
    Product(
        name = "Mars Soft Nougat & Caramel Filled Chocolate Bar Combo Pack",
        price = 132.00,
        shippingMethod = "Free Shipping",
        imageUrl = "https://cdn.zeptonow.com/production///tr:w-1000,ar-1494-1494,pr-true,f-auto,q-80/cms/product_variant/7bd835ed-f73d-4302-9794-81347fa36a5f.jpg"
    ),
    Product(
        name = "Raw Pressery Almond Milk -Unsweetend Tetrapack\n",
        price = 299.00,
        shippingMethod = "Same Day Shipping",
        imageUrl = "https://cdn.zeptonow.com/production///tr:w-1000,ar-1000-1000,pr-true,f-auto,q-80/cms/product_variant/03bca1f1-5380-49ce-a745-c30d93adf945.jpeg"
    ),
    Product(
        name = "Tata Sampann Unpolished Toor Dal/Arhar Dal",
        price = 232.00,
        shippingMethod = "Free Shipping",
        imageUrl = "https://cdn.zeptonow.com/production///tr:w-1000,ar-1200-1200,pr-true,f-auto,q-80/cms/product_variant/ddae04ed-05ae-4b5a-846e-0f579b5d2fb5.jpeg"
    ),
    Product(
        name = "Fortune Sunlite Refined Sunflower Oil (Pouch)",
        price = 163.00,
        shippingMethod = "Same Day Shipping",
        imageUrl = "https://cdn.zeptonow.com/production///tr:w-1000,ar-1000-1000,pr-true,f-auto,q-80/cms/product_variant/00463c55-c37c-4b6d-819a-b3b806a418ca.jpeg"
    ),
    Product(
        name = "Nic Gulab Jamun Icecream Cup",
        price = 86.00,
        shippingMethod = "Free Shipping",
        imageUrl = "https://cdn.zeptonow.com/production///tr:w-1000,ar-1021-1021,pr-true,f-auto,q-80/cms/product_variant/85ea8888-4abf-4e06-9d63-fd6c2248d240.jpeg"
    ),
    Product(
        name = "Haldiram Aloo Bhujia",
        price = 52.00,
        shippingMethod = "Free Shipping",
        imageUrl = "https://cdn.zeptonow.com/production///tr:w-1000,ar-1000-1000,pr-true,f-auto,q-80/cms/product_variant/19df2b49-3f85-4f78-ad1c-f33f791292b4.jpeg"
    ),
    Product(
        name = "Kurkure Green Chutney Style",
        price = 20.00,
        shippingMethod = "Free Shipping",
        imageUrl = "https://cdn.zeptonow.com/production///tr:w-1000,ar-1000-1000,pr-true,f-auto,q-80/cms/product_variant/01eab692-d62e-4733-a084-3f3677377340.jpeg"
        ),
)