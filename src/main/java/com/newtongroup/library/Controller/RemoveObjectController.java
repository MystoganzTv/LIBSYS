package com.newtongroup.library.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newtongroup.library.Entity.Book;
import com.newtongroup.library.Entity.EBook;
import com.newtongroup.library.Entity.RemovedBook;
import com.newtongroup.library.Entity.RemovedEBook;
import com.newtongroup.library.Entity.RemovedSeminary;
import com.newtongroup.library.Entity.Seminary;
import com.newtongroup.library.Repository.BookRepository;
import com.newtongroup.library.Repository.EBookRepository;
import com.newtongroup.library.Repository.RemovedBookRepository;
import com.newtongroup.library.Repository.RemovedEBookRepository;
import com.newtongroup.library.Repository.RemovedSeminaryRepository;
import com.newtongroup.library.Repository.SeminaryRepository;
import com.newtongroup.library.Repository.UserRepository;

@Controller
@RequestMapping("/remove-object")
public class RemoveObjectController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private EBookRepository eBookRepository;
	@Autowired
	private SeminaryRepository seminaryRepository;

	@Autowired
	private RemovedBookRepository removedBookRepository;
	@Autowired
	private RemovedEBookRepository removedEBookRepository;
	@Autowired
	private RemovedSeminaryRepository removedSeminaryRepository;

	private String adminheader = "admin/adminheader.html";
	private String librarianheader = "librarian/librarianheader.html";

	@RequestMapping("/book")
	public String book(Model theModel, Principal principal) {

		theModel.addAttribute("header", getHeader(principal));
		theModel.addAttribute("book", new Book());
		theModel.addAttribute("removedBook", new RemovedBook());

		return "remove-objects/remove-book";
	}

	@RequestMapping("/e-book")
	public String ebook(Model theModel, Principal principal) {

		theModel.addAttribute("header", getHeader(principal));
		theModel.addAttribute("ebook", new EBook());
		theModel.addAttribute("removedEBook", new RemovedEBook());

		return "remove-objects/remove-e-book";
	}

	@RequestMapping("/seminary")
	public String seminarie(Model theModel, Principal principal) {

		theModel.addAttribute("header", getHeader(principal));
		theModel.addAttribute("seminary", new Seminary());
		theModel.addAttribute("removedSeminary", new RemovedSeminary());

		return "remove-objects/remove-seminary";
	}

	@RequestMapping("/delete-book")
	public String deleteBook(@ModelAttribute("book") Book theBook,
			@ModelAttribute("removedBook") RemovedBook theRemovedBook, Model theModel, Principal principal) {
		theModel.addAttribute("header", getHeader(principal));
		String isbn = theBook.getIsbn();

		List<Book> bookList = bookRepository.findAll();

		for (Book temp : bookList) {
			if (temp.getIsbn().equals(isbn)) {

				RemovedBook removedBook = new RemovedBook();
				removedBook.setBook(temp);
				removedBookRepository.save(removedBook);

				return "remove-objects/remove-book-confirmation";
			}

		}

		return "error/isbn-error";
	}

	@RequestMapping("/delete-e-book")
	public String deleteEBook(@ModelAttribute("EBook") EBook theEBook,
			@ModelAttribute("removedEBook") RemovedEBook theRemovedEBook, Model theModel, Principal principal) {
		theModel.addAttribute("header", getHeader(principal));
		String isbn = theEBook.getIsbn();

		List<EBook> bookList = eBookRepository.findAll();

		for (EBook temp : bookList) {
			if (temp.getIsbn().equals(isbn)) {
				RemovedEBook removedEBook = new RemovedEBook();
				removedEBook.setEbook(temp);
				removedEBookRepository.save(removedEBook);
				return "remove-objects/remove-e-book-confirmation";
			}

		}
		return "error/isbn-error";
	}

	@RequestMapping("/delete-seminary")
	public String deleteSeminarie(@ModelAttribute("seminary") Seminary theSeminary,
			@ModelAttribute("removedSeminary") RemovedSeminary theRemovedSeminary, Model theModel,
			Principal principal) {
		theModel.addAttribute("header", getHeader(principal));

		Long id = theSeminary.getSeminary_id();

		List<Seminary> seminaryList = seminaryRepository.findAll();

		for (Seminary temp : seminaryList) {
			if (temp.getSeminary_id().equals(id)) {

				String title = temp.getTitle();
				String information = temp.getInformation();
				String cause = theRemovedSeminary.getCause();
				String startTime = temp.getStartTime();
				String endTime = temp.getEndTime();
				String occurrence = temp.getOccurrence();

				RemovedSeminary removedSeminary = new RemovedSeminary(id, title, information, occurrence, startTime,
						endTime, cause);
				removedSeminaryRepository.save(removedSeminary);

				seminaryRepository.delete(temp);
				return "remove-objects/remove-seminary-confirmation";
			}

		}

		return "error/id-error";

	}

	private String getHeader(Principal principal) {
		String header = new String();
		if (userRepository.findByUsername(principal.getName()).getAuthority().getAuthorityName().equals("ROLE_ADMIN")) {
			return adminheader;
		} else if (userRepository.findByUsername(principal.getName()).getAuthority().getAuthorityName()
				.equals("ROLE_LIBRARIAN")) {
			return librarianheader;
		}

		return header;
	}

}
