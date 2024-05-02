package org.example.coupons.service;

import org.example.coupons.model.Coupon;
import org.example.coupons.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private final CouponRepository couponRepository;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }
    @Override
    public List<Coupon> getCouponsByStore(String storeName) {
        return couponRepository.findByStoreName(storeName);
    }
}