package com.Desert.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
    @Column(name = "total")
    private double total;
    @Column(name = "status")
    private String status;
    @Column(name = "address")
    private String address;
    @Column(name = "date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @SuppressWarnings("JpaModelReferenceInspection")
    @OneToMany(mappedBy = "id.bill")
    private List<BillDetail> detailList;
}
