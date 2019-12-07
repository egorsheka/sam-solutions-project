package by.sam.mvc.config;

import by.sam.mvc.security.DefaultUrlAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService);
//
//    }
//
//
//    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
//        return new DefaultUrlAuthenticationSuccessHandler();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/viewMenu", "/selectMenu", "/bookMenu", "/resources/**", "/webapp/**",  "/registration**", "/js/**", "/css/**", "/img/**", "/webjars/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/signIn")
//                .failureUrl("/login?error")
//                .successHandler(myAuthenticationSuccessHandler())
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .permitAll();
//
//
//        http.logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")
//                .permitAll();
//    }
}

//


