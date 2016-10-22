package ua.in.dris4ecoder.springConfigClasses;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.sql.DataSource;

/**
 * Created by Alex Korneyko on 10.10.2016 19:24.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {


}
