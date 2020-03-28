/**
 * @author Rohit Patnaik (rohitpatnaik27@gmail.com)
 */
package in.co.najah.najahhr.repository;

import in.co.najah.najahhr.entity.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DesignationRepository extends JpaRepository<Designation, Integer>{

	List<Designation> findByIdIn(List<Integer> ids);

}
