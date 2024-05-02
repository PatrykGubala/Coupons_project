package org.example.coupons.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "coupons")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String title;

    @Column(length = 1024)
    private String content;

    private Double discountValue;

    @Column(length = 255)
    private String type;

    @Column(length = 100)
    private String code;

    private Date date;

    private Date expires;

    @Column(length = 255)
    private String store;

    @Column(length = 255)
    private String category;

    @Column(length = 1024)
    private String linkZeStrony;

    @Column(length = 1024)
    private String destinationUrl;

    private Boolean exclusive;

    @Column(length = 255)
    private String storeName;
}
