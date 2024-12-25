package mk.ukim.finki.wp.lab3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for development
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/songs", "/songDetails/**", "/assets/**", "/h2-console/**","/login", "/access_denied")
                        .permitAll() // Public pages
                        .requestMatchers("/songs/add", "/songs/edit/**", "/songs/delete/**", "/songs/add-form")
                        .hasRole("ADMIN") // Restricted pages for ADMIN role
                        .anyRequest()
                        .authenticated() // All other requests require authentication
                )
                .httpBasic(Customizer.withDefaults()) // Enable basic HTTP authentication
                .formLogin((form) -> form
                        .loginPage("/login") // Custom login page
                        .permitAll()
                        .failureUrl("/login?error=BadCredentials") // Redirect on failed login
                        .defaultSuccessUrl("/songs", true) // Redirect to /songs after login
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/login") // Redirect to /login after logout
                )
                .exceptionHandling((ex) -> ex
                        .accessDeniedPage("/access_denied") // Custom access denied page
                )
                .headers((headers) -> headers
                        .frameOptions((frameOptions) -> frameOptions.sameOrigin()) // Allow frames only from the same origin
                )
        ;

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin")) // Password is 'admin'
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("aana")
                .password(passwordEncoder.encode("pas")) // Password is 'user'
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
