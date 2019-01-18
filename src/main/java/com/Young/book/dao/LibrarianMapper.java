package com.Young.book.dao;

import com.Young.book.po.Librarian;
import org.apache.ibatis.annotations.Param;

public interface LibrarianMapper {
    public int addLibrarian(Librarian librarian) throws Exception;
    public Librarian getLibrarianById(@Param("id") String id) throws Exception;
    public int updateLibrarian(Librarian librarian) throws Exception;
    public int deleteLibrarian(@Param("id") String id) throws Exception;
    public Librarian getLibrarianByUsername(@Param("username") String username) throws Exception;
}
