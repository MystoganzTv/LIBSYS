package com.newtongroup.library.Controller;

import com.newtongroup.library.Entity.*;
import com.newtongroup.library.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping ("/new-object")
public class AddObjectController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    EBookRepository eBookRepository;

    @Autowired
    SeminaryRepository seminaryRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PlacementRepository placementRepository;

    List<Author> authorList;

    @GetMapping("/new-seminar")
    public String getSeminarForm(Model model){
        Seminary seminary=new Seminary();
        model.addAttribute("seminary", seminary);
        return "object/add-seminar";
    }
    @PostMapping ("/save-seminar")
    public String saveSeminar(Seminary seminary){
        seminaryRepository.save(seminary);
        return "redirect:/new-object/new-seminar";
    }

    @GetMapping("/new-author")
    public String getAuthorForm(Model model){
        Author author = new Author();
        model.addAttribute("author",author);
        return "object/add-author";
    }
    @PostMapping("/save-author")
    public String saveAuthor(Author author){
        authorRepository.save(author);
        return "redirect:/new-object/new-author";
    }

    @GetMapping("/new-book")
    public String getBookForm(Model model){
        Book book = new Book();

        authorList=authorRepository.findAll();
        List<Placement>placementList=placementRepository.findAll();

        model.addAttribute("book", book);
        model.addAttribute("authors", authorList);
        model.addAttribute("placements", placementList);
        return "/object/add-book";
    }

    @PostMapping("/save-book")
    public String saveBook(@ModelAttribute("book") Book book){
        bookRepository.save(book);
        return "redirect:/new-object/new-book";
    }
    @GetMapping("/new-ebook")
    public String getEBookForm(Model model){
        EBook ebook = new EBook();

        authorList=authorRepository.findAll();

        model.addAttribute("ebook", ebook);
        model.addAttribute("authors", authorList);
        return "/object/add-ebook";
    }
    @PostMapping("/save-ebook")
    public String saveEBook(@ModelAttribute("ebook") EBook eBook){
        eBookRepository.save(eBook);
        return "redirect:/new-object/new-ebook";
    }
}
