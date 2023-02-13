package com.base.hitoindividualdwes;

import com.base.hitoindividualdwes.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import javax.sql.DataSource;
import java.util.ArrayList;


@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity  {
    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


   @Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("select username, password from Users where username=?");
        users.setAuthoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username=?");
        return users;
    }




    // Filtros por URL.
     @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests()
// Recursos estáticos que no requieren autentificación.

                .requestMatchers("/css/**").permitAll()
                // No se requiere autenticación para acceso a la raiz y al login
                .requestMatchers("/", "/login", "/register", "/registro_completado", "/exitologin").permitAll()
                .requestMatchers("/admin").hasAuthority("ADMIN")
                // Solo puede acceder a la vista clientes el usuario con rol SUPERUSUARIO
                .requestMatchers("/creartema", "/perfil").hasAnyAuthority("USER", "ADMIN")
                // Se requiere autenticación para el resto de reutas.
                .anyRequest().authenticated()
                // Se permite iniciar y cerrar sesión.
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/exitologin").failureUrl("/errorlogin").permitAll()
                .and().logout().logoutSuccessUrl("/exitologout").invalidateHttpSession(true).permitAll()
                // Error permiso denegado
                .and().exceptionHandling().accessDeniedPage("/denegado");
       /* .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/hola")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll();


        */
        return http.build();
    }


}
