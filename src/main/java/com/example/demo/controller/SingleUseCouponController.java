package com.example.demo.controller;

import java.util.UUID;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SingleUseCouponMessage;
import com.example.demo.dto.SingleUseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import akka.actor.ActorRef;

@RestController
@RequestMapping("/pcms")
public class SingleUseCouponController {
	
	@Autowired
	@Named("singleUseActor")
	private ActorRef singleUseActor;
	
	@RequestMapping("/singleUseCoupon")
	public String createSingleUseCoupon(@RequestParam("promotionId") String promotionId,
			@RequestParam("noOfCoupons") int noOfCoupons) throws JsonProcessingException{
		String batchId = UUID.randomUUID().toString();
		SingleUseCouponMessage message = new SingleUseCouponMessage(batchId, noOfCoupons,promotionId,0);
		singleUseActor.tell(message, ActorRef.noSender());
		return singleUseResponse(batchId,message);
	}

	private String singleUseResponse(String batchId, SingleUseCouponMessage message) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(
				new SingleUseResponse(batchId,message.getPromotionID(),"INPROCESS"));
	}
	
	/*@RequestMapping("/blockingSingleUseCoupon")
	public String createBlockingSingleUseCoupon(@RequestParam("batchId") String batchId,
			@RequestParam("couponRequest") int noOfCouponRequest) throws Exception{
		Timeout timeout = new Timeout(Duration.create(120, "seconds"));
		Future<Object> future = Patterns.ask(singleUseActor, 
				new SingleUseCouponMessage(batchId, noOfCouponRequest), timeout);
		String result = (String) Await.result(future, timeout.duration());
		return "Number Coupon Request are :"+result;
	}*/

}
