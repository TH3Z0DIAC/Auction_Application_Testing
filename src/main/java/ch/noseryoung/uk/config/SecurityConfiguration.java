package ch.noseryoung.uk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// This class handles basic security concerns
@Configuration
@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests().antMatchers("/").permitAll();
    }
}



/*package ch.noseryoung.uk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// Annotation to show compiler that it is a class declaring Bean methods
@Configuration
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // Configuring Authorisation to receive data from database
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/**").hasAuthority("CREATE_USER")
                .antMatchers(HttpMethod.GET, "/users/**").hasAuthority("RETRIEVE_USER")
                .antMatchers(HttpMethod.PUT, "/users/**").hasAuthority("UPDATE_USER")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasAuthority("DELETE_USER")
                .antMatchers(HttpMethod.POST, "/authorities/**").hasAuthority("CREATE_AUTHORITY")
                .antMatchers(HttpMethod.GET, "/authorities/**").hasAuthority("RETRIEVE_AUTHORITY")
                .antMatchers(HttpMethod.PUT, "/authorities/**").hasAuthority("UPDATE_AUTHORITY")
                .antMatchers(HttpMethod.DELETE, "/authorities/**").hasAuthority("DELETE_AUTHORITY")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        //User Role
        UserDetails theUser = User.withUsername("jens")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .password("jens").roles("USER").authorities("RETRIEVE_USER").build();

        //Manager Role
        UserDetails theManager = User.withUsername("yves")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .password("yves").roles("MANAGER")
                .authorities("CREATE_USER", "UPDATE_USER", "RETRIEVE_USER", "DELETE_USER",
                        "CREATE_AUTHORITY", "UPDATE_AUTHORITY", "RETRIEVE_AUTHORITY", "DELETE_AUTHORITY")
                .build();


        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(theUser);
        userDetailsManager.createUser(theManager);

        return userDetailsManager;
    }
}*/