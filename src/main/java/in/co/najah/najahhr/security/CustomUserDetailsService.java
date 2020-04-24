package in.co.najah.najahhr.security;

import in.co.najah.najahhr.models.UserDetailModel;
import in.co.najah.najahhr.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {



    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDetailModel user = userService.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password !");
        }

        return user;
    }

}
