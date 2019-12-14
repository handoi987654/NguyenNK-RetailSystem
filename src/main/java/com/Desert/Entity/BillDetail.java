package com.Desert.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "BillDetail")
public class BillDetail {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "bill", column = @Column(name = "bill_id")),
            @AttributeOverride(name = "product", column = @Column(name = "product_id"))
    })
    private BillDetailID id;
    @Column(name = "quantity")
    private long quantity;
    @Column(name = "current_price")
    private double currentPrice;
}
