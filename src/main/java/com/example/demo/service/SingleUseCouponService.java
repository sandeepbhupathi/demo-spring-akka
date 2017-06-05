package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.SingleUseCouponMessage;

@Service
public class SingleUseCouponService {

	public void createSingleUseCoupon(SingleUseCouponMessage message) throws InterruptedException {
		Thread.sleep(10000);
		System.out.println("got the message with batch id:" + message.getBatchId() + " Number of Coupons"
				+ message.getNoOfCoupons());
	}
}
