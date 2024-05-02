package org.example.coupons.service;


import org.example.coupons.model.Coupon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CouponService {
    List<Coupon> getAllCoupons();
    List<Coupon> getCouponsByStore(String storeName);
}