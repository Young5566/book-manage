package com.Young.book.service;

import com.Young.book.po.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    public int addBook(Book book) throws Exception;
    public Book getBookById(String id) throws Exception;
    public Map getAllBook(Integer page) throws Exception;
    public int updateBook(Book book) throws Exception;
    public int deleteBook(String id) throws Exception;
}
