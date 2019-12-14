package com.Desert.Service;

import com.Desert.Entity.Bill;
import com.Desert.Entity.BillDetail;
import com.Desert.Entity.Product;
import com.Desert.Entity.User;
import com.Desert.Repository.BillDetailRepo;
import com.Desert.Repository.BillRepo;
import com.Desert.Repository.ProductRepo;
import com.Desert.Repository.UserRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientServiceBean implements ClientService {

    private final ProductRepo productRepo;
    private final BillRepo billRepo;
    private final UserRepo userRepo;
    private final BillDetailRepo billDetailRepo;

    public ClientServiceBean(ProductRepo productRepo, BillRepo billRepo, UserRepo userRepo, BillDetailRepo billDetailRepo) {
        this.productRepo = productRepo;
        this.billRepo = billRepo;
        this.userRepo = userRepo;
        this.billDetailRepo = billDetailRepo;
    }

    @Override
    public Product getProduct(String barcode) {
        return productRepo.getProduct(barcode);
    }

    @Override
    public Product getProduct(long productID) {
        return productRepo.getProduct(productID);
    }

    @Override
    public List<Bill> getBills(String username) {
        User user = userRepo.getUser(username);
        return billRepo.getBills(user.getId());
    }

    @Override
    public List<BillDetail> getBillDetails(long billID) {
        return billDetailRepo.getBillDetails(billID);
    }
}
