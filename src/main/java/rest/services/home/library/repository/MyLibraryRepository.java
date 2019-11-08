package rest.services.home.library.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rest.services.home.library.model.MyLibrary;

@Repository
public interface MyLibraryRepository extends JpaRepository<MyLibrary, Long>{

	  @Transactional	  
	  @Query(value = "SELECT * FROM my_library WHERE name=:name", nativeQuery =
	  true) MyLibrary selectByName(@Param("name") String name);
	  
	  @Transactional	  
	  @Query(value = "SELECT * FROM my_library", nativeQuery =
	  true) List<MyLibrary> getAll();
}
