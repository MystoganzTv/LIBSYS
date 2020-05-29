package com.newtongroup.library.Controller;

import com.newtongroup.library.Entity.*;
import com.newtongroup.library.Repository.*;
import com.newtongroup.library.Utils.HeaderUtils;
import com.newtongroup.library.Wrapper.UserPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/librarian")
public class LibrarianController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookrepository;

    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private EbookLoanRepository ebookLoanRepository;

    @Autowired
    private EBookRepository eBookRepository;

    @Autowired
    private LibraryCardRepository libraryCardRepository;


    private String header = "librarian/bootstrapheader.html";

    @RequestMapping("/")
    public String mainView(Model theModel, Principal principal) {
        theModel.addAttribute("header", HeaderUtils.getHeaderString(userRepository.findByUsername(principal.getName())));
        return "librarian/start";
    }

    @RequestMapping("/delete-visitor-menu")
    public String deleteUserMenu(Model theModel, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        theModel.addAttribute("header", HeaderUtils.getHeaderString(user));
        theModel.addAttribute("email", new String());
        return "librarian/delete-user-menu";
    }

    @RequestMapping("/delete-visitor")
    public String deleteUser(@RequestParam(name = "email") String email, Model theModel, Principal principal) {
        theModel.addAttribute("header", HeaderUtils.getHeaderString(userRepository.findByUsername(principal.getName())));
        User user = userRepository.findByUsername(email);
        if (user == null) {
            return "error/email-cannot-be-found";
        } else if (user.getAuthority().getAuthorityName().equals("ROLE_VISITOR")) {
            Visitor visitor = visitorRepository.findByEmail(user.getUsername());

            for (BookLoan bookLoan : visitor.getActiveLibraryCard().getBookLoans()) {
                if (!bookLoan.getBookReturned()) {
                    List<BookLoan> bookLoans = visitor.getActiveLibraryCard().getBookLoans()
                            .stream()
                            .filter(loan -> loan.getBookReturned() == false)
                            .collect(Collectors.toList());

                    theModel.addAttribute("visitor", visitor);
                    theModel.addAttribute("bookLoans", bookLoans);
                    return "admin/delete-failed-user-has-loans";
                }
            }

            hashAllUSerData(user);
        } else {
            return "error/email-cannot-be-found";
        }

        return "admin/delete-confirmation";
    }

    @RequestMapping("/input-book-to-return")
    private String prepareToReturnBook(Model theModel, Principal principal) {
        System.out.println(principal.getName());
        theModel.addAttribute("header", HeaderUtils.getHeaderString(userRepository.findByUsername(principal.getName())));

        List<Book> bookList = getActiveBookList();


        Book book = new Book();


        theModel.addAttribute("bookList", bookList);
        theModel.addAttribute("book", book);

        return "loan/return-book";
    }

    @RequestMapping("/return-book")
    private String returnBook(@RequestParam(name = "bookId", required = false) Long bookIdTEST,
                              @RequestParam(name = "eBookId", required = false) Long eBookId,
                              @ModelAttribute("book") Book book,
                              @ModelAttribute("bookLoan") BookLoan bookLoan,
                              Model theModel, Principal principal) {

        theModel.addAttribute("header", HeaderUtils.getHeaderString(userRepository.findByUsername(principal.getName())));
        Long bookId = bookLoan.getBook().getId();
        System.out.println(bookId);


        if (bookId != null) {
            Book bookToReturn = bookrepository.findById(bookId).orElse(null);
            BookLoan loan = bookLoanRepository.findByBookAndIsBookReturned(bookToReturn, false);

            if (loan == null) {
                return "error/book-or-no-active-librarycard";
            }

            bookToReturn.setAvailable(true);
            loan.setDateReturned(new Date(Calendar.getInstance().getTime().getTime()));
            loan.setBookReturned(true);
            bookLoanRepository.save(loan);

        } else if (eBookId != null) {
            EBook bookToReturn = eBookRepository.findById(eBookId).orElse(null);
            EbookLoan loan = ebookLoanRepository.findByEbookAndIsEbookReturned(bookToReturn, false);

            if (loan == null) {
                return "error/book-or-no-active-librarycard";
            }

            bookToReturn.setAvailable(true);
            loan.setDateReturned(new Date(Calendar.getInstance().getTime().getTime()));
            loan.setEbookReturned(true);
            ebookLoanRepository.save(loan);

        } else {
            return "error/book-or-no-active-librarycard";
        }

        return "loan/return-success";
    }

    @GetMapping("/new-librarycard")
    public String createNewLibrary(Model theModel, Principal principal) {
        theModel.addAttribute("header", HeaderUtils.getHeaderString(userRepository.findByUsername(principal.getName())));

        List<LibraryCard> libraryCardsList = libraryCardRepository.findAll()
                .stream()
                .filter(card -> card.getVisitor().isActive())
                .collect(Collectors.toList());

        Visitor visitor = new Visitor();

        List<Visitor> visitorList = visitorRepository.findAll();


        theModel.addAttribute("libraryCards", libraryCardsList);
        theModel.addAttribute("visitorList", visitorList);
        theModel.addAttribute("visitor", visitor);


        return "lock/lock-register";
    }

    @GetMapping("/save-new-librarycard")
        public String saveNewLibraryCard(
                @ModelAttribute (name = "user") User user,
                @ModelAttribute (name = "libraryCard") LibraryCard libraryCard,
                Model model, Principal principal){
        model.addAttribute("header", HeaderUtils.getHeaderString(userRepository.findByUsername(principal.getName())));
       User user1 = userRepository.getOne(user.getId());
       UserPerson userPerson=new UserPerson();

        if(libraryCard.isActive()) {
            newLibraryCard(userPerson);
            return "register-visitor/visitor-registration-confirmation";
        } else {
            return "error/not-valid-card-already-unlocked";
        }
    }

//    public LibraryCard getNewLibraryCard() {
//        for (LibraryCard card : libraryCards) {
//            if (card.isActive()) {
//                setLibraryCards(libraryCards);
//                return card;
//            }
//        }
//        return null;
//    }

    private void newLibraryCard(UserPerson userPerson) {
        ArrayList<LibraryCard> libraryCards = new ArrayList<>();
        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setActive(true);
        libraryCard.setVisitor(userPerson.getVisitor());
        libraryCards.add(libraryCard);
        userPerson.getVisitor().setLibraryCards(libraryCards);
    }


    private void hashAllUSerData(User user) {
        Visitor visitor = visitorRepository.findByEmail(user.getUsername());
        visitor.setFirstName(passwordEncoder.encode(visitor.getFirstName()));
        visitor.setLastName(passwordEncoder.encode(visitor.getLastName()));
        visitor.setStreet(passwordEncoder.encode(visitor.getStreet()));
        visitor.setPostalCode(passwordEncoder.encode(visitor.getPostalCode()));
        visitor.setCity(passwordEncoder.encode(visitor.getCity()));
        visitor.setPhone(passwordEncoder.encode(visitor.getPhone()));
        visitor.setPersonalNumber(passwordEncoder.encode(visitor.getPersonalNumber()));
        visitor.setEmail(passwordEncoder.encode(visitor.getEmail()));
        visitor.setActive(false);
        visitorRepository.save(visitor);
    }

    private List<Book> getActiveBookList() {
        List<Book> tempList = bookrepository.findAll();
        List<Book> bookList = new ArrayList<>();

        for (Book temp : tempList) {
            if (!temp.isAvailable()) {
                bookList.add(temp);
            }
        }
        return bookList;
    }
}
