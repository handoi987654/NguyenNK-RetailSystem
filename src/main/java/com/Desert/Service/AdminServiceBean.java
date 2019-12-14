package com.Desert.Service;

import com.Desert.Entity.Product;
import com.Desert.Repository.ProductRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdminServiceBean implements AdminService {

    private final ProductRepo productRepo;

    public AdminServiceBean(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getProductList() {
        return productRepo.getProductList();
    }
}
