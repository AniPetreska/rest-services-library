package rest.services.home.library.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rest.services.home.library.model.BorrowedBook;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long>{
	
	  @Transactional	  
	  @Query(value = "SELECT * FROM borrowed_books", nativeQuery =
	  true) List<BorrowedBook> getAll();
}
