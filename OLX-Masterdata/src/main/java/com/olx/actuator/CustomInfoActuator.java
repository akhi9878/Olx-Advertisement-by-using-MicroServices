package com.olx.actuator;

import java.util.Collections;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class CustomInfoActuator implements InfoContributor {

	@Override
	public void contribute(Builder builder) {
		
		builder.withDetail("Info", Collections.singletonMap("key", "value"));
		builder.withDetail("name","Akhilesh");
		
	}

}
