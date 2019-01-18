package com.Young.book.dao;

import com.Young.book.po.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    public int addBook(Book book) throws Exception;
    public Book getBookById(@Param("id") String id) throws Exception;
    public List<Book> getAllBook(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize) throws Exception;
    public int updateBook(Book book) throws Exception;
    public int deleteBook(@Param("id") String id) throws Exception;
    public int getTotalCount() throws Exception;
}
