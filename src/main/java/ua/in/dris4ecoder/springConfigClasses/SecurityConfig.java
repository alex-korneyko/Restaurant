package ua.in.dris4ecoder.springConfigClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.in.dris4ecoder.model.businessObjects.UserRole;
import ua.in.dris4ecoder.model.businessServices.UserRegistrationService;
import ua.in.dris4ecoder.model.businessServices.UserRegistrationServiceImpl;
import ua.in.dris4ecoder.model.businessObjects.UserGroup;
import ua.in.dris4ecoder.model.businessObjects.UserImpl;
import ua.in.dris4ecoder.model.dao.RestaurantDao;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcDaoImplWithDBSchemas;

import javax.sql.DataSource;

/**
 * Created by Alex Korneyko on 10.10.2016 19:24.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, JdbcDaoImplWithDBSchemas jdbcDao) throws Exception {

        auth.userDetailsService(jdbcDao);
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .and().formLogin().loginPage("/loginPage").successForwardUrl("/user/loginSuccess")
                .and().httpBasic()
                .and().rememberMe().rememberMeParameter("rememberMe").key("restaurant").tokenValiditySeconds(864000)
                .and().csrf().disable()
                .logout().logoutUrl("/user/logout").logoutSuccessUrl("/loginStatusFrame")
                .and().headers().frameOptions().sameOrigin();
    }

    @Bean
    JdbcDaoImplWithDBSchemas securityJdbcDao(DataSource dataSource) {

        JdbcDaoImplWithDBSchemas jdbcDao = new JdbcDaoImplWithDBSchemas();
        jdbcDao.setEnableGroups(true);
        jdbcDao.setEnableAuthorities(false);
        jdbcDao.setDataSource(dataSource);

        return jdbcDao;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder(11);
    }


    @Bean
    DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    UserRegistrationService userRegistrationService(RestaurantDao<UserImpl> userRestaurantDao,
                                                    RestaurantDao<UserGroup> userGroupRestaurantDao,
                                                    BCryptPasswordEncoder passwordEncoder,
                                                    RestaurantDao<UserRole> userRoleRestaurantDao) {

        UserRegistrationServiceImpl userRegistrationController = new UserRegistrationServiceImpl();
        userRegistrationController.setUserRestaurantDao(userRestaurantDao);
        userRegistrationController.setUserGroupRestaurantDao(userGroupRestaurantDao);
        userRegistrationController.setUserRoleRestaurantDao(userRoleRestaurantDao);
        userRegistrationController.setPasswordEncoder(passwordEncoder);

        return userRegistrationController;
    }
}
