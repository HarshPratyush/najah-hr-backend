package in.co.najah.najahhr.repository;

import in.co.najah.najahhr.entity.Division;
import in.co.najah.najahhr.entity.Industries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DivisionRepository extends JpaRepository<Division,Long> {

    Optional<Division> findById(long id);
    List<Division> findByIndustriesUrl(String url);

    Optional<Division> findByDivisionNameAndIndustries(String divisionName, Industries industries);
}
