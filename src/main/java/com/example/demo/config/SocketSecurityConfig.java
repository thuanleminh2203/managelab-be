package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class SocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

//	@Override
//	protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
//
////		messages.simpDestMatchers("/secured/**").authenticated().anyMessage().authenticated();
////		messages.anyMessage().hasRole("USER");
//	}
///////////////////////////////////////////////
//	@Override
//	public void configureMessageBroker(MessageBrokerRegistry config) {
////		config.enableSimpleBroker("/topic");
////		config.setApplicationDestinationPrefixes("/app");
//
//		config.enableSimpleBroker("/topic");
//	    config.setApplicationDestinationPrefixes("/app");
////		config.setUserDestinationPrefix("/secured/user");
//	}
//
//	@Override
//	public void registerStompEndpoints(StompEndpointRegistry registry) {
//		registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS();
////		registry.addEndpoint("/chat");
//	}
//
//	@Override
//	protected boolean sameOriginDisabled() {
//		return true;
//	}
	///////////////////////////////////////////////


	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/secured/user/queue/specific-user");
		config.setApplicationDestinationPrefixes("/spring-security-mvc-socket");
		config.setUserDestinationPrefix("/secured/user");

	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/secured/room").setAllowedOrigins("*").withSockJS();
//				.setAllowedOrigins("*").withSockJS();
	}

	@Override
	protected boolean sameOriginDisabled() {
		return true;
	}

}
