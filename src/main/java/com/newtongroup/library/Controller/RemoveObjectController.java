package com.newtongroup.library.Controller;


import com.newtongroup.library.Entity.*;
import com.newtongroup.library.Repository.*;
import com.newtongroup.library.Utils.HeaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
    @Autowired
    private AuthorRepository authorRepository;


    @RequestMapping("/book")
    public String book(Model theModel, Principal principal){

        theModel.addAttribute("header", HeaderUtils.getHeaderString(userRepository.findByUsername(principal.getName())));
        theModel.addAttribute("book", new Book());
        theModel.addAttribute("removedBook", new RemovedBook());


        return "remove-objects/remove-book";
    }

    @RequestMapping("/e-book")
    public String ebook(Model theModel, Principal principal){
        theModel.addAttribute("header", HeaderUtils.getHeaderString(userRepository.findByUsername(principal.getName())));
        theModel.addAttribute("ebook", new EBook());
        theModel.addAttribute("removedEBook", new RemovedEBook());


        return "remove-objects/remove-e-book";
    }

    @RequestMapping("/seminary")
    public String seminarie(Model theModel, Principal principal){
        theModel.addAttribute("header", HeaderUtils.getHeaderString(userRepository.findByUsername(principal.getName())));
        theModel.addAttribute("seminary", new Seminary());
        theModel.addAttribute("removedSeminary", new RemovedSeminary());


        return "remove-objects/remove-seminary";
    }

    @GetMapping("/delete-book")
    public String deleteBook(@ModelAttribute(name="book")Book theBook, @ModelAttribute(name="removedBook") RemovedBook theRemovedBook, Model theModel, Principal principal){
        theModel.addAttribute("header", HeaderUtils.getHeaderString(userRepository.findByUsername(principal.getName())));

        boolean doesIdAlreadyExist= bookRepository.findById(theBook.getId()).isPresent();
        if(doesIdAlreadyExist){
            Book book=bookRepository.getOne(theBook.getId());



        RemovedBook removedBook= new RemovedBook(book.getId(), book.getTitle(), book.getIsbn(), book.getPublisher(),book.getDescription(),
                                   book.getPurchasePrice(), book.getPlacement().getPlacementId(),theRemovedBook.getCause() );

                removedBookRepository.save(removedBook);
                bookRepository.delete(book);
                return "remove-objects/remove-book-confirmation";
            }
            return "error/id-error";


    }

    @RequestMapping("/delete-e-book")
    public String deleteEBook(@ModelAttribute("EBook") EBook theEBook,  @ModelAttribute("removedEBook") RemovedEBook theRemovedEBook,Model theModel, Principal principal){
        theModel.addAttribute("header", HeaderUtils.getHeaderString(userRepository.findByUsername(principal.getName())));
        String isbn = theEBook.getIsbn();

        List<EBook> bookList = eBookRepository.findAll();

        for(EBook temp : bookList){
            if(temp.getIsbn().equals(isbn)){
                Long id = temp.getId();
                String title = temp.getTitle();
                String publisher = temp.getPublisher();
                String price = temp.getPurchasePrice();
                String cause = theRemovedEBook.getCause();
                String description = temp.getDescription();
                String download_link = temp.getDownloadLink();

                RemovedEBook removedEBook = new RemovedEBook(id,title,isbn,publisher,description,price,download_link,cause);
                removedEBookRepository.save(removedEBook);
                System.out.println(removedEBook);

                eBookRepository.delete(temp);
                System.out.println(temp);
                return "remove-objects/remove-e-book-confirmation";
            }



        }
        return "error/isbn-error";
    }

    @RequestMapping("/delete-seminary")
    public String deleteSeminarie(@ModelAttribute("seminary") Seminary theSeminary,@ModelAttribute("removedSeminary") RemovedSeminary theRemovedSeminary, Model theModel, Principal principal){
        theModel.addAttribute("header", HeaderUtils.getHeaderString(userRepository.findByUsername(principal.getName())));


            Long id = theSeminary.getSeminary_id();

        List<Seminary> seminaryList = seminaryRepository.findAll();

        for(Seminary temp : seminaryList) {
            if (temp.getSeminary_id().equals(id)) {

                String title = temp.getTitle();
                String information = temp.getInformation();
                String cause = theRemovedSeminary.getCause();
                String startTime = temp.getStartTime();
                String endTime = temp.getEndTime();
                String occurrence = temp.getOccurrence();


                RemovedSeminary removedSeminary = new RemovedSeminary(id, title, information, occurrence, startTime, endTime, cause);
                removedSeminaryRepository.save(removedSeminary);

                seminaryRepository.delete(temp);
                return "remove-objects/remove-seminary-confirmation";
            }

        }

        return "error/id-error";


    }
    @GetMapping ("/author")
    public String getAuthorForm (Model model, Principal principal){
        model.addAttribute("header", HeaderUtils.getHeaderString(userRepository.findByUsername(principal.getName())));

        List <Author>authorList=authorRepository.findAll(Sort.by(Sort.Direction.ASC, "lastname"));

        Author author= new Author();

        model.addAttribute("authors", authorList);
        model.addAttribute("author", author);
        return "remove-objects/remove-author";
    }
    @GetMapping ("/delete-author")
    public String deleteAuthor(@ModelAttribute (name = "author") Author author, Principal principal, Model model) {
        model.addAttribute("header", HeaderUtils.getHeaderString(userRepository.findByUsername(principal.getName())));
        Author authorToRemove = authorRepository.getOne(author.getAuthorId());
        boolean isAuthorNotInABookList=authorToRemove.getBookList().isEmpty()&&authorToRemove.geteBookList().isEmpty();
        if (isAuthorNotInABookList) {
            authorRepository.delete(author);
            return "redirect:/remove-object/author";
        }
        return "error/author-could-not-be-removed";
    }
}
