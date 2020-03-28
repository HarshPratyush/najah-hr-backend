/**
 * 
 */
package in.co.najah.najahhr.repository;

import in.co.najah.najahhr.entity.AccountDesignationMapping;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;


@RepositoryDefinition(domainClass= AccountDesignationMapping.class,idClass=Integer.class)
public interface AccountDesignationMappingRepository {
	
	
	@Transactional
	public AccountDesignationMapping save(AccountDesignationMapping accountDesignationMapping);

}
