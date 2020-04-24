package in.co.najah.najahhr.models;

import lombok.Data;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 * @since version 1.0.0.0
 *
 */
public class UserDetailModel extends User {

    private static final long serialVersionUID = -2419594198247611200L;
    private Integer userId;
    private String name;
    private String emailId;
    private boolean isLive;
    private boolean isAttached;
    private Set<String> role;

    public UserDetailModel(String username, String password, boolean enabled, boolean accountNonExpired,
                           boolean credentialsNonExpired, boolean accountNonLocked,Set<GrantedAuthority> grantedAuthorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,grantedAuthorities );
        this.userId = userId;
        this.role = role;
    }

    public boolean isAttached() {
        return isAttached;
    }

    public void setAttached(boolean isAttached) {
        this.isAttached = isAttached;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean isLive) {
        this.isLive = isLive;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

}