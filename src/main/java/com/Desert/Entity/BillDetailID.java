package com.Desert.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class BillDetailID implements Serializable {

    @ManyToOne
    private Bill bill;
    @ManyToOne
    private Product product;

}
