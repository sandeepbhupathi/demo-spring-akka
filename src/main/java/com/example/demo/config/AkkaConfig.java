package com.example.demo.config;

import javax.inject.Named;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.actor.SingleUseCouponChildActor;
import com.example.demo.actor.SingleUseCouponParentActor;
import com.example.demo.service.SingleUseCouponService;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

@Configuration
public class AkkaConfig {
	@Bean
	public ActorSystem getSystem() {
		return ActorSystem.create("SingleUseActorSystem");
	}

	@Bean(name = "singleUseChildActorConfig")
	public Props singleUseChildActorConfig(SingleUseCouponService singleUseActorService) {
		return Props.create(SingleUseCouponChildActor.class, singleUseActorService);
	}

	@Bean(name = "singleUseChildActor")
	public ActorRef singleUseChildActor(ActorSystem system, @Named("singleUseChildActorConfig") Props singleUseActorConfig) {
		return system.actorOf(singleUseActorConfig);
	}
	
	@Bean(name = "singleUseActorConfig")
	public Props singleUseActorConfig(@Named("singleUseChildActor") ActorRef singleUseActor) {
		return Props.create(SingleUseCouponParentActor.class,singleUseActor);
	}

	@Bean(name = "singleUseActor")
	public ActorRef singleUseActor(ActorSystem system, @Named("singleUseActorConfig") Props singleUseActorConfig) {
		return system.actorOf(singleUseActorConfig);
	}
	
}
