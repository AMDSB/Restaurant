package dev.sn.config;

import dev.sn.repositories.User1Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
public class ApplicationConfiguration {

    private final User1Repository userRepository;

    public ApplicationConfiguration(User1Repository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    UserDetailsService userDetailsService(){
       return username -> userRepository.findByEmail(username)
               .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public HandlerExceptionResolver handlerExceptionResolver() {
//        return new org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver();
//    }





//    @Bean
//    BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
            return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
