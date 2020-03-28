package in.co.najah.najahhr.repository;

import in.co.najah.najahhr.entity.Account;
import in.co.najah.najahhr.entity.MstUser;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;


@RepositoryDefinition(domainClass= MstUser.class,idClass=Integer.class)
public interface UserRepository {
	
	@Transactional
	public MstUser save(MstUser mstUser);
	
	public MstUser findByAccount(Account account);
	
	public MstUser findById(Integer id);

}
