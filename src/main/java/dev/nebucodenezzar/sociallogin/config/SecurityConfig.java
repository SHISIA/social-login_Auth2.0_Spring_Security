package dev.nebucodenezzar.sociallogin.config;

import dev.nebucodenezzar.sociallogin.service.CustomOAuth2UserService;
import dev.nebucodenezzar.sociallogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomOAuth2UserService oauthUserService;
    @Autowired
    private UserService userService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/oauth/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(auth -> auth
                        .loginPage("/login")
                        .userInfoEndpoint(endpoint -> endpoint
                                .userService(oauthUserService))
                        .successHandler(
                                (request, response, authentication) -> {

                                    DefaultOidcUser oauthUser = (DefaultOidcUser) authentication.getPrincipal();
                                    String email = oauthUser.getAttribute("email");
                                    String picture = oauthUser.getAttribute("picture");
                                    String first_name = oauthUser.getAttribute("given_name");
                                    String last_name = oauthUser.getAttribute("family_name");


                                    System.out.println("Oauth "+email+" picture "+picture+" last name "+
                                            last_name+" first_name "+first_name);
                                    userService.processOAuthPostLogin(email);

                                    response.sendRedirect("/secured");
                                }
                        )
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
