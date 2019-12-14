package com.Desert.Repository;

import com.Desert.Entity.Bill;

import java.util.List;

public interface BillRepo {

    List<Bill> getBills(long userID);
}
