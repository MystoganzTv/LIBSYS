package com.newtongroup.library.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newtongroup.library.Entity.AbstractBook;
import com.newtongroup.library.Entity.Book;
import com.newtongroup.library.Entity.EBook;
import com.newtongroup.library.Repository.UserRepository;
import com.newtongroup.library.Service.SearchService;
import com.newtongroup.library.Utils.HeaderUtils;

@Controller
@RequestMapping("/search")
public class SearchServiceController {

	@Autowired
	private SearchService searchService;
	@Autowired
	private UserRepository userRepository;

	@GetMapping()
	public String searchForm( @RequestParam(value="search", required =false) String searchText, Model model, Principal principal){

		if(principal == null) {
			model.addAttribute("header", new String("anonymous-user/anonymousheader.html"));
		} else {
			model.addAttribute("header", HeaderUtils.getHeaderString(userRepository.findByUsername(principal.getName())));
		}

		List<Book> bResults = searchService.searchBooks(searchText);
		List<EBook> ebResults = searchService.searchEBooks(searchText);
		model.addAttribute("bResults", bResults);
		model.addAttribute("ebResults", ebResults);

		model.addAttribute("search", searchText);

		return "/search/searchview";
	}

}
