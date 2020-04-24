package in.co.najah.najahhr.service;

import in.co.najah.najahhr.entity.Account;
import in.co.najah.najahhr.entity.MstUser;
import in.co.najah.najahhr.models.UserDetailModel;
import in.co.najah.najahhr.repository.AccountRepository;
import in.co.najah.najahhr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetailModel findByUserName(String userName) {

        Account account = accountRepository.findByUserName(userName);

        UserDetailModel userDetailModel = null;
        if (account != null) {
            MstUser mstUser = account.getMstUser();


            Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
            mstUser.getUserAuthorityMappings().forEach(d->{
                grantedAuthoritySet.add(new SimpleGrantedAuthority(d.getAuthority().getAuthority()));
            });

            userDetailModel = new UserDetailModel(mstUser.getAccount().getUserName(),mstUser.getAccount().getPassword(),true,true,
                    true, true,grantedAuthoritySet);



        }

        return userDetailModel;
    }
}
