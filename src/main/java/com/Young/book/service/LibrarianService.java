package com.Young.book.service;

import com.Young.book.po.Librarian;

public interface LibrarianService {
    public int addLibrarian(Librarian librarian) throws Exception;
    public Librarian getLibrarianById(String id) throws Exception;
    public int updateLibrarian(Librarian librarian) throws Exception;
    public int deleteLibrarian(String id) throws Exception;
    public Librarian getLibrarianByUsername(String username) throws Exception;
}
