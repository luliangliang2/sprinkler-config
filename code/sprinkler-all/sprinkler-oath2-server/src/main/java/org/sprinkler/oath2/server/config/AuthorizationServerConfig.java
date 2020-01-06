package org.sprinkler.oath2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
//@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法权限注解
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		// 定义了两个客户端应用的通行证
		clients.inMemory()
				.withClient("sheep1")
				.secret(new BCryptPasswordEncoder().encode("123456"))
				.authorizedGrantTypes("authorization_code", "password", "refresh_token")
				.scopes("all").autoApprove(true)
				.and()
				.withClient("sheep2")
				.secret(new BCryptPasswordEncoder().encode("123456"))
				.authorizedGrantTypes("authorization_code", "password", "refresh_token")
				.scopes("all")
				.autoApprove(true);
	}

	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(jwtTokenStore())
				.userDetailsService(userDetailsService);
	}

	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
				.tokenKeyAccess("permitAll()") //获取token请求不进行拦截
				.checkTokenAccess("isAuthenticated()")//验证通过返回token信息
				.allowFormAuthenticationForClients();// 允许 客户端使用client_id和client_secret获取token
	}

	@Bean
	public TokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("testKey");
		return converter;
	}

}