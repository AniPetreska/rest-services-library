package rest.services.home.library.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.services.home.library.model.BorrowedBook;
import rest.services.home.library.model.MyLibrary;
import rest.services.home.library.model.MyWishList;
import rest.services.home.library.model.ReviewEntity;
import rest.services.home.library.repository.BorrowedBookRepository;
import rest.services.home.library.repository.MyLibraryRepository;
import rest.services.home.library.repository.ReviewRepository;
import rest.services.home.library.repository.WishListRepository;

@RestController
@RequestMapping(value="/books/*", produces={"application/json","application/xml"})
public class LibraryController {

	@Autowired
	MyLibraryRepository myLibrary;

	@Autowired
	WishListRepository wishList;
	
	@Autowired
	ReviewRepository reviewRepo;
	
	@Autowired
	BorrowedBookRepository borrowedBookRepo;

	@PostMapping("/myLibrary")
	public ResponseEntity<String> insertIntoMyLibrary(@Valid @RequestBody MyLibrary books) {
		myLibrary.save(books);
		return ResponseEntity.ok().body("Successfully inserted");
	}
	
	@PostMapping("/wishList")
	public ResponseEntity<String> insertIntoMyWishList(@Valid @RequestBody MyWishList books) {
		wishList.save(books);
		return ResponseEntity.ok().body("Succesfully inserted");
	}
	
	@GetMapping("/selectBook/{name}")
	public ResponseEntity<Object> getBookInfo(@PathVariable(value = "name") String name) {

		MyLibrary books = myLibrary.selectByName(name);
		MyWishList myWishList = wishList.selectByName(name);

		if (null == books && null == myWishList) {
			return ResponseEntity.ok().body("There is no such book in the database.");
		} else if (null == myWishList) {
			return ResponseEntity.ok().body(books);
		} else {
			return ResponseEntity.ok().body(myWishList);
		}
	}
	
	@GetMapping("/selectAllBooks/{table}")
	public ResponseEntity<Object> getAllBooks(@PathVariable(value = "table") String table){
		
		List<MyWishList> myWishList = new ArrayList<MyWishList>();
		List<MyLibrary> myLib = new ArrayList<MyLibrary>();
		
		if("wishlist".equalsIgnoreCase(table)) {
			myWishList = wishList.getAll();
			return ResponseEntity.ok().body(myWishList);
		}else {
			myLib = myLibrary.getAll();
			return ResponseEntity.ok().body(myLib);
		}
	}
	
	@PostMapping("/writeReview")
	public ResponseEntity<String> writeReview(@Valid @RequestBody ReviewEntity review) {
		reviewRepo.save(review);
		return ResponseEntity.ok().body("Review successfully written.");
	}
	
	@GetMapping("/getReview/{name}")
	public ResponseEntity<String> getReview(@PathVariable(value = "name") String name){
		
		String review = reviewRepo.getReview(name);
		if(null == review)
			return ResponseEntity.ok().body("There is no review for the book " + name + ".");
		else return ResponseEntity.ok().body(review);
	}
	
	@GetMapping("/getTopFive")
	public ResponseEntity<Object> getTopFive(){
		
		List<String> names = reviewRepo.getTopFive();
		return ResponseEntity.ok().body(names);
		
	}
	
	@PostMapping("/borrowBook")
	public ResponseEntity<String> borrowBook(@Valid @RequestBody BorrowedBook borrowedBook) {
		
		borrowedBookRepo.save(borrowedBook);
		return ResponseEntity.ok().body("Successfully inserted.");
	}
	
	@GetMapping("/getBorrowedBooks")
	public ResponseEntity<Object> getBorrowedBooks(){
		
		List<BorrowedBook> borrowedBooks = new ArrayList<>();
		borrowedBooks = borrowedBookRepo.getAll();
		
		return ResponseEntity.ok().body(borrowedBooks);
	}
	
	@DeleteMapping("/returnBook/{id}")
	public ResponseEntity<String> returnBorrowedBook(@PathVariable(value = "id") Long id){
		
		borrowedBookRepo.deleteById(id);
		
		return ResponseEntity.ok().body("Successfully deleted record.");
	}
	
	@GetMapping("/insertBatchMyLibrary")
	public ResponseEntity<String> insertBatchMyLib(){
		
		MyLibrary myLib1 = new MyLibrary("Gone with the wind", "Margaret Mitchelle", "English", "Roman", 1200, true);
		MyLibrary myLib2 = new MyLibrary("Ana Karenina", "Leo Tolstoy", "Macedonian", "Roman", 1200, true);
		MyLibrary myLib3 = new MyLibrary("Resurrection", "Leo Tolstoy", "Macedonian", "Roman", 500, true);
		MyLibrary myLib4 = new MyLibrary("War and Peace", "Leo Tolstoy", "Macedonian", "Roman", 2000, false);
		
		List<MyLibrary> books = Arrays.asList(myLib1, myLib2, myLib3, myLib4);
		
		myLibrary.saveAll(books);
		
		return ResponseEntity.ok().body("Saved all of the books in MyLibrary");
	}
	
	@GetMapping("/insertBatchWishList")
	public ResponseEntity<String> insertBatchWishList(){
		
		MyWishList wishList1 = new MyWishList("The Gambler", "Dostoevsky", "English", "Roman", 500, false);
		MyWishList wishList2 = new MyWishList("Humiliated and Insulter", "Dostoevsky", "English", "Roman", 500, true);
		MyWishList wishList3 = new MyWishList("Crime and Punishment", "Dostoevsky", "English", "Roman", 500, true);
		MyWishList wishList4 = new MyWishList("Demons", "Dostoevsky", "English", "Roman", 500, false);
					
		List<MyWishList> books = Arrays.asList(wishList1, wishList2, wishList3, wishList4);
		
		wishList.saveAll(books);
		
		return ResponseEntity.ok().body("Saved all of the books in MyWishList");
	}
}
