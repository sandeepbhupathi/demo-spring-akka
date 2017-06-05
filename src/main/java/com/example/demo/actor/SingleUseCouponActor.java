package com.example.demo.actor;

import com.example.demo.dto.SingleUseCouponMessage;
import com.example.demo.service.SingleUseCouponService;

import akka.actor.AbstractActor;

public class SingleUseCouponActor extends AbstractActor{

	private SingleUseCouponService singleUseCouponService;
	
	public SingleUseCouponActor(SingleUseCouponService singleUseCouponService) {
		super();
		this.singleUseCouponService = singleUseCouponService;
	}


	
	
	@Override
	public Receive createReceive() {
		return receiveBuilder().match(SingleUseCouponMessage.class, singleUseMsg ->{
			singleUseCouponService.createSingleUseCoupon(singleUseMsg);
			sender().tell("done completed", self());
		}).build();
	}
	
	

}
