package rest.services.home.library.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rest.services.home.library.model.ReviewEntity;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

	@Transactional
	@Query(value = "SELECT review FROM review WHERE name=:name", nativeQuery = true)
	String getReview(@Param("name") String name);

	@Transactional
	@Query(value = "SELECT TOP 5 name FROM review GROUP BY rating ORDER BY rating DESC ", nativeQuery = true)
	List<String> getTopFive();
}
