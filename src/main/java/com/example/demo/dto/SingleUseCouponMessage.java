package com.example.demo.dto;

public class SingleUseCouponMessage {

	private String batchId;
	private int noOfCoupons;
	public SingleUseCouponMessage(String batchId, int noOfCoupons) {
		super();
		this.batchId = batchId;
		this.noOfCoupons = noOfCoupons;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public int getNoOfCoupons() {
		return noOfCoupons;
	}
	public void setNoOfCoupons(int noOfCoupons) {
		this.noOfCoupons = noOfCoupons;
	}
}
