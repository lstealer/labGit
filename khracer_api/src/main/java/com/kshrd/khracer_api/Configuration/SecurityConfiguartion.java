package com.kshrd.khracer_api.Configuration;


import com.kshrd.khracer_api.Configuration.JwtConfiguration.JwtAuthenticationEntryPoint;
import com.kshrd.khracer_api.Configuration.JwtConfiguration.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguartion extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

    private BCryptPasswordEncoder encoder;
    private JwtRequestFilter jwtRequestFilter;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    public void setJwtAuthenticationEntryPoint(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint){
        this.jwtAuthenticationEntryPoint=jwtAuthenticationEntryPoint;
    }
    @Autowired
    public void setJwtRequestFilter(JwtRequestFilter jwtRequestFilter){
        this.jwtRequestFilter=jwtRequestFilter;
    }
    @Autowired
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(@Qualifier("userServiceImp") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET,"/image/**").antMatchers("/swagger-ui.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/image/**").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()

                //JwtRestController
                //token//login
                .antMatchers(HttpMethod.POST,"/authenticate").permitAll()
                //UserController
                //getAllUser
                .antMatchers(HttpMethod.GET,"/kh-racer/v1/users").hasAnyRole("USER","SUPER_ADMIN")
                //register
                .antMatchers(HttpMethod.POST,"/kh-racer/v1/user").permitAll()
                //homepage
                .antMatchers(HttpMethod.GET,"/kh-racer/v1/top-players").permitAll()
                //ContentController
                //getRandomContent
                .antMatchers(HttpMethod.GET,"/kh-racer/v1/content").permitAll()
                //postContent
                .antMatchers(HttpMethod.POST,"/kh-racer/v1/content").hasAnyRole("ADMIN","SUPER_ADMIN","USER")
                //UpdateContent
                .antMatchers(HttpMethod.PATCH,"/kh-racer/v1/content").hasAnyRole("ADMIN","SUPER_ADMIN","USER")
                //DeleteContent
                .antMatchers(HttpMethod.DELETE,"/kh-racer/v1/content-{id}").hasAnyRole("SUPER_ADMIN","ADMIN")
                //FileController
                //uploads
                .antMatchers(HttpMethod.POST,"/kh-racer/v1/uploads").hasAnyRole("USER","ADMIN","SUPER_ADMIN")
                //getSingleImage
                .antMatchers(HttpMethod.GET,"/kh-racer/v1/file-{id}").permitAll()
                //getAllImages
                .antMatchers(HttpMethod.GET,"/kh-racer/v1/files").permitAll()
                //ReportController
                //getAllReport
                .antMatchers(HttpMethod.GET,"/kh-racer/v1/reports").hasAnyRole("ADMIN","SUPER_ADMIN")
               //postReport
                .antMatchers(HttpMethod.POST,"/kh-racer/v1/report").permitAll()
//                PatchReport
                .antMatchers(HttpMethod.PATCH,"/kh-racer/v1/report").hasAnyRole("ADMIN","SUPER_ADMIN")
//                HistoryController
//                after each match
                .antMatchers(HttpMethod.POST,"/kh-racer/v1/history").permitAll()
//                select user history by id
                 .antMatchers(HttpMethod.GET,"/kh-racer/v1/histories/{id}").permitAll()

                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }
}
