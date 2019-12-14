package com.Desert.Service;

import com.Desert.Entity.Bill;
import com.Desert.Entity.BillDetail;
import com.Desert.Entity.Product;

import java.util.List;

public interface ClientService {

    Product getProduct(String barcode);

    Product getProduct(long productID);

    List<Bill> getBills(String username);

    List<BillDetail> getBillDetails(long billID);
}
