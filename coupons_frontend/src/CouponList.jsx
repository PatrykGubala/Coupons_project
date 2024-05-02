import React, { useState, useEffect } from 'react';
import axios from 'axios';
import CouponWidget from './CouponWidget';

function CouponList() {
    const [coupons, setCoupons] = useState([]);
    const [filter, setFilter] = useState('');

    useEffect(() => {
        fetchCoupons();
    }, []);

    useEffect(() => {
        const filteredCoupons = coupons.filter(coupon => coupon.storeName.toLowerCase().includes(filter.toLowerCase()));
        setCoupons(filteredCoupons);
    }, [filter]);

    const fetchCoupons = async () => {
        try {
            const response = await axios.get('http://localhost:8080/coupons');
            setCoupons(response.data);
        } catch (error) {
            console.error('Error fetching coupons:', error);
        }
    };

    return (
        <div className="coupon-container">
            {coupons.map(coupon => (
                <CouponWidget key={coupon.id} coupon={coupon} />
            ))}
        </div>
    );
}

export default CouponList;
