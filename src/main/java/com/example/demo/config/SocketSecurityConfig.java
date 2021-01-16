package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Configuration
@EnableWebSocketMessageBroker
public class SocketSecurityConfig implements WebSocketMessageBrokerConfigurer {

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
		config.enableSimpleBroker("/topic", "/queue");
		config.setApplicationDestinationPrefixes("/app");
//		config.setUserDestinationPrefix("/user");
//		config.setUserDestinationPrefix("/secured/user");

	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		registry.addEndpoint("/ws").setAllowedOrigins("*").setHandshakeHandler(new DefaultHandshakeHandler() {

			public boolean beforeHandshake(
					ServerHttpRequest request,
					ServerHttpResponse response,
					WebSocketHandler wsHandler,
					Map attributes) throws Exception {

				if (request instanceof ServletServerHttpRequest) {
					ServletServerHttpRequest servletRequest
							= (ServletServerHttpRequest) request;
					HttpSession session = servletRequest
							.getServletRequest().getSession();
					attributes.put("sessionId", session.getId());
				}
				return true;
			}}).withSockJS();
//	}.withSockJS();
//		setHandshakeHandler(new AssignPrincipalHandshakeHandler())
//				.setAllowedOrigins("*").withSockJS();
//				addInterceptors(new HttpHandshakeInterceptor()).withSockJS();
//				.setAllowedOrigins("*").withSockJS();
	}

//	@Override
//	protected boolean sameOriginDisabled() {
//		return true;
//	}

}
