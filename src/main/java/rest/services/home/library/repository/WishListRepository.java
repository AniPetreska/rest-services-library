package rest.services.home.library.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rest.services.home.library.model.MyWishList;

@Repository
public interface WishListRepository extends JpaRepository<MyWishList, Long>{
		
	  @Transactional	  
	  @Query(value = "SELECT * FROM Wish_List WHERE name = :name", nativeQuery =
	  true) MyWishList selectByName(@Param("name") String name);
	 
	  @Transactional	  
	  @Query(value = "SELECT * FROM Wish_List", nativeQuery =
	  true) List<MyWishList> getAll();
}
