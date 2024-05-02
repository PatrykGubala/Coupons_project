package org.example.coupons.controller;

import org.example.coupons.model.Coupon;
import org.example.coupons.service.CouponService;
import org.example.coupons.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;


    @Autowired
    private ExcelService excelService;

    @PostMapping("/upload-excel")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(excelService.parseExcelFile(file));
    }
    @GetMapping
    public ResponseEntity<List<Coupon>> getCoupons() {
        List<Coupon> coupons = couponService.getAllCoupons();
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    @GetMapping("/store")
    public ResponseEntity<List<Coupon>> getCouponsByStore(@RequestParam String storeName) {
        List<Coupon> coupons = couponService.getCouponsByStore(storeName);
        return ResponseEntity.ok(coupons);
    }
}