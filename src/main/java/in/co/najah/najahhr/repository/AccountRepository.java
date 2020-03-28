package in.co.najah.najahhr.repository;

import in.co.najah.najahhr.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	Account findByUserName(String name);

	Account findByEmail(String string);
}
