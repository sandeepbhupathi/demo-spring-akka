package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SingleUseCouponMessage;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

@RestController
@RequestMapping("/pcms")
public class SingleUseCouponController {
	
	@Autowired
	private ActorRef singleUseActor;
	
	@RequestMapping("/singleUseCoupon")
	public String createSingleUseCoupon(@RequestParam("batchId") String batchId,
			@RequestParam("couponRequest") int noOfCouponRequest){
		singleUseActor.tell(new SingleUseCouponMessage(batchId, noOfCouponRequest), ActorRef.noSender());
		return "Number Coupon Request are :"+noOfCouponRequest;
	}
	
	@RequestMapping("/blockingSingleUseCoupon")
	public String createBlockingSingleUseCoupon(@RequestParam("batchId") String batchId,
			@RequestParam("couponRequest") int noOfCouponRequest) throws Exception{
		Timeout timeout = new Timeout(Duration.create(120, "seconds"));
		Future<Object> future = Patterns.ask(singleUseActor, 
				new SingleUseCouponMessage(batchId, noOfCouponRequest), timeout);
		String result = (String) Await.result(future, timeout.duration());
		return "Number Coupon Request are :"+result;
	}

}
