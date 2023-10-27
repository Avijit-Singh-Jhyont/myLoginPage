package login.loginPage.myloginPage.security;

import login.loginPage.myloginPage.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    http
      .authorizeHttpRequests(a->a
        .requestMatchers("/register").permitAll()
        .requestMatchers("/acctActivated").permitAll()
        .requestMatchers("/verify/**").permitAll()
        .requestMatchers("/verifyRegistrationNotification").permitAll()
        .anyRequest().authenticated()
      )
      .formLogin(f->f
        .loginPage("/login").permitAll()
      )
      .logout(l->l.logoutSuccessUrl("/login"))
      .httpBasic(HttpBasicConfigurer::disable)
    ;

    return http.build();
  }


  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private MyUserDetailsService myUserDetailsService;
  @Autowired
  public void configAuthManager(AuthenticationManagerBuilder manager) throws Exception{
    manager.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
  }

}





















