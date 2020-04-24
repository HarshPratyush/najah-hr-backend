package in.co.najah.najahhr.service;

import in.co.najah.najahhr.models.*;

public interface UserService {
    UserDetailModel findByUserName(String userName);
}
