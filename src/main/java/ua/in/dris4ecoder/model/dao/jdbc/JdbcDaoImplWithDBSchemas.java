package ua.in.dris4ecoder.model.dao.jdbc;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import java.util.List;

/**
 * Created by Alex Korneyko on 28.10.2016 19:21.
 */
public class JdbcDaoImplWithDBSchemas extends JdbcDaoImpl{

    private static final String DEF_USERS_BY_USERNAME_QUERY = "select username,password,enabled "
            + "from security.users " + "where username = ?";
    private static final String DEF_AUTHORITIES_BY_USERNAME_QUERY = "select username,authority "
            + "from security.authorities " + "where username = ?";
    private static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY = "select g.id, g.group_name, ga.authority "
            + "from security.groups g, security.group_members gm, security.group_authorities ga "
            + "where gm.username = ? " + "and g.id = ga.group_id "
            + "and g.id = gm.group_id";

    private String authoritiesByUsernameQuery;
    private String groupAuthoritiesByUsernameQuery;
    private String usersByUsernameQuery;
    private String rolePrefix = "";

    public JdbcDaoImplWithDBSchemas() {

        this.usersByUsernameQuery = DEF_USERS_BY_USERNAME_QUERY;
        this.authoritiesByUsernameQuery = DEF_AUTHORITIES_BY_USERNAME_QUERY;
        this.groupAuthoritiesByUsernameQuery = DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY;
    }

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        return getJdbcTemplate().query(this.usersByUsernameQuery,
                new String[] { username }, (rs, rowNum) -> {
                    String username1 = rs.getString(1);
                    String password = rs.getString(2);
                    boolean enabled = rs.getBoolean(3);
                    return new User(username1, password, enabled, true, true, true,
                            AuthorityUtils.NO_AUTHORITIES);
                });
    }

    @Override
    protected List<GrantedAuthority> loadGroupAuthorities(String username) {
        return getJdbcTemplate().query(this.groupAuthoritiesByUsernameQuery,
                new String[] { username }, (rs, rowNum) -> {
                    String roleName = getRolePrefix() + rs.getString(3);

                    return new SimpleGrantedAuthority(roleName);
                });
    }

    @Override
    protected List<GrantedAuthority> loadUserAuthorities(String username) {
        return getJdbcTemplate().query(this.authoritiesByUsernameQuery,
                new String[] { username }, (rs, rowNum) -> {
                    String roleName = JdbcDaoImplWithDBSchemas.this.rolePrefix + rs.getString(2);

                    return new SimpleGrantedAuthority(roleName);
                });
    }
}
