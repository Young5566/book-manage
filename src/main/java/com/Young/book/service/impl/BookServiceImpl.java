package com.Young.book.service.impl;

import com.Young.book.dao.BookMapper;
import com.Young.book.po.Book;
import com.Young.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@PropertySource("classpath:appConfig.properties")
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public int addBook(Book book) throws Exception {
       return this.bookMapper.addBook(book);
    }

    @Override
    public Book getBookById(String id) throws Exception {
        return this.bookMapper.getBookById(id);
    }

    /**
     * 获取全部图书，分页
     * @param page 页数
     * @return 返回图书
     * @throws Exception
     */
    @Override
    public Map getAllBook(Integer page) throws Exception {
        Integer offset = (page -1) * 9 ;
        List<Book> bookList = this.bookMapper.getAllBook(offset, 9);
        int totalCount = this.bookMapper.getTotalCount();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("books", bookList);
        resultMap.put("total", totalCount);
        return resultMap;
    }

    @Override
    public int updateBook(Book book) throws Exception {
        return this.bookMapper.updateBook(book);
    }

    @Override
    public int deleteBook(String id) throws Exception {
        return this.bookMapper.deleteBook(id);
    }

}
