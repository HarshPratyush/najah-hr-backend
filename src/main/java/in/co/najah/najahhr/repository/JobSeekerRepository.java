package in.co.najah.najahhr.repository;

import in.co.najah.najahhr.entity.Industries;
import in.co.najah.najahhr.entity.JobSeeker;
import in.co.najah.najahhr.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobSeekerRepository extends JpaRepository<JobSeeker,Long> {

    Optional<JobSeeker> findByEmailId(String emailId);

}
