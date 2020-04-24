package in.co.najah.najahhr.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 * @since version 1.0.0.0
 *
 */
@Data
public class ChangePasswordModel {

    private String userName;

    private String oldPassword;

    private String newPassword;

    private String confirmPassword;
}