package in.co.najah.najahhr.rest;


import in.co.najah.najahhr.entity.*;
import in.co.najah.najahhr.models.*;
import in.co.najah.najahhr.repository.AccountRepository;
import in.co.najah.najahhr.repository.AuthorityRepository;
import in.co.najah.najahhr.repository.UserRepository;
import in.co.najah.najahhr.service.JobsService;
import in.co.najah.najahhr.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import rx.Single;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class LoginController {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value="/login")
    @ResponseBody
    public UserDetailModel login()
    {
        return (UserDetailModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("/anonymous/createAuthority")
    @ResponseBody
    public  ResponseEntity createAuthority(){
        Authority authority = new Authority("industry","Manage Industry");
        authorityRepository.save(authority);
        authority = new Authority("jobs","Manage jobs");

        authorityRepository.save(authority);
        authority = new Authority("resume","Manage resumes");

        authorityRepository.save(authority);

        Account account = new Account();
        account.setUserName("administration");
        account.setEmail("info@najahhr.com");
        account.setPassword(passwordEncoder.encode("c#&129c#!cv"));

        MstUser mstUser = new MstUser();
        mstUser.setAccount(accountRepository.save(account));
        mstUser.setFirstName("Admin");
        mstUser.setLastName("");

        List<UserAuthorityMapping> userAuthorityMappings = authorityRepository.findAll().stream().map(d-> new UserAuthorityMapping(mstUser,d)).collect(Collectors.toList());
        mstUser.setUserAuthorityMappings(userAuthorityMappings);

        userRepository.save(mstUser);

        return ResponseEntity.accepted().build();
    }
}
