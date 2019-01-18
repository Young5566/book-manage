package com.Young.book.service.impl;

import com.Young.book.dao.LibrarianMapper;
import com.Young.book.po.Librarian;
import com.Young.book.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LibrarianServiceImpl implements LibrarianService {

    @Autowired
    private LibrarianMapper librarianMapper;

    @Override
    public int addLibrarian(Librarian librarian) throws Exception {
        return this.librarianMapper.addLibrarian(librarian);
    }

    @Override
    public Librarian getLibrarianById(String id) throws Exception {
        return this.librarianMapper.getLibrarianById(id);
    }

    @Override
    public int updateLibrarian(Librarian librarian) throws Exception {
        return this.librarianMapper.updateLibrarian(librarian);
    }

    @Override
    public int deleteLibrarian(String id) throws Exception {
        return this.librarianMapper.deleteLibrarian(id);
    }

    @Override
    public Librarian getLibrarianByUsername(String username) throws Exception {
        return this.librarianMapper.getLibrarianByUsername(username);
    }

}
