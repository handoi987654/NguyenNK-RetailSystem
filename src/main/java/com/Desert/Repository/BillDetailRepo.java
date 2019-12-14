package com.Desert.Repository;

import com.Desert.Entity.BillDetail;

import java.util.List;

public interface BillDetailRepo {

    List<BillDetail> getBillDetails(long billID);
}
