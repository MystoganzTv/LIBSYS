package com.newtongroup.library.Service;

import java.util.List;

import com.newtongroup.library.Entity.AbstractBook;

public interface SearchService {

	List<AbstractBook> searchBooks(String searchText);

}
