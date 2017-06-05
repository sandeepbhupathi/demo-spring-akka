package com.example.demo.config;

import javax.inject.Named;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.actor.SingleUseCouponActor;
import com.example.demo.service.SingleUseCouponService;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

@Configuration
public class AkkaConfig {
	
	@Bean
	public ActorSystem getSystem(){
		return ActorSystem.create("SingleUseActorSystem");
	}
	
	@Bean(name="singleUseActorConfig")
	public Props singleUseActorConfig(SingleUseCouponService singleUseActor){
		return Props.create(SingleUseCouponActor.class,singleUseActor);
	}
	
	@Bean(name="singleUseActor")
	public ActorRef singleUseActor(ActorSystem system,@Named("singleUseActorConfig") Props singleUseActorConfig ){
		System.out.println("Creating actor ref bean.......");
		return system.actorOf(singleUseActorConfig);
	}
}
