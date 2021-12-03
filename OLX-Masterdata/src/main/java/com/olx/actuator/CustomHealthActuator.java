package com.olx.actuator;

import java.util.Random;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.stereotype.Component;

@Component    //SpringBoot cans this Component or class
public class CustomHealthActuator extends AbstractHealthIndicator {

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		Random rd=new Random();
		int rdno = rd.nextInt();
		
		if(rdno %2 ==0)
			builder.up();
		else
			builder.down();		
	}

}
