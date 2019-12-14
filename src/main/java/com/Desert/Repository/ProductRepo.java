package com.Desert.Repository;

import com.Desert.Entity.Product;

import java.util.List;

public interface ProductRepo {

    Product getProduct(String barcode);

    Product getProduct(long productID);

    List<Product> getProductList();
}
