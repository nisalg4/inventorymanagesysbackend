package com.example.springsecuritymongo.config;

import com.example.springsecuritymongo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/dashboard").hasAnyAuthority("user", "admin")
                .antMatchers("/manage").hasAnyAuthority("admin")
                .antMatchers("/getEmployeebyID/{ID}").hasAnyAuthority( "admin")
                .antMatchers("/addAssetAssignment").hasAnyAuthority("admin")
                .antMatchers("/AssetAssignment/employeeName/{ID}").hasAnyAuthority("admin","user")
                .antMatchers("/AssetAssignment/asset/{ID}").hasAnyAuthority("admin")
                .antMatchers("/AssetAssignment/delete/{ID}").hasAnyAuthority("admin")
                .antMatchers("/addAssetAssignmentforemployee").hasAnyAuthority("admin")
                .antMatchers("/Asset/delete/{ID}").hasAnyAuthority("admin")
                .antMatchers("/Asset/getAll").hasAnyAuthority("admin")
                .antMatchers("/updateAsset").hasAnyAuthority("admin")
                .antMatchers("/Employee/delete/{ID}").hasAnyAuthority("admin")
                .antMatchers("/Employee/getAll").hasAnyAuthority("admin")
                .antMatchers("/updateEmployee").hasAnyAuthority("admin")
                .antMatchers("/signup").hasAnyAuthority("admin")
                .antMatchers("/AssetAssignment/getAll").hasAnyAuthority("admin")
                .antMatchers("/deleteAssetAssignmentAsset").hasAnyAuthority("admin")
                .antMatchers("/updateUser").hasAnyAuthority("admin")
                .anyRequest().authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
