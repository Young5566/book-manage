package com.Young.book.controller;

import com.Young.book.utils.ResultEnum;
import com.Young.book.utils.Utils;
import com.Young.book.utils.Result;
import com.Young.book.po.Book;
import com.Young.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
@CrossOrigin
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 添加图书
     * @param book 图书信息
     * @return Result
     */
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    @ResponseBody
    public Result addBook(@RequestBody Book book) {
        try {
            book.setId(Utils.getUUid());
            int addResult = this.bookService.addBook(book);
            if(addResult == 1){
                return new Result();
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            return new Result(ResultEnum.SQL_ERROR, "图书添加失败");
        }
    }

    /**
     * 根据id查询图书详情
     * @param id 书的id
     * @return Result
     */
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ResponseBody
    public Result getBookById(String id){
        try {
            Book book = this.bookService.getBookById(id);
            if(book == null){
                return new Result(ResultEnum.NOT_FOUND, "该图书不存在");
            }
            return new Result<>(book);
        } catch (Exception e){
            return new Result(ResultEnum.SQL_ERROR, "查找图书失败");
        }
    }

    /**
     * 获取全部图书
     * @param page 请求页数
     * @return Result
     * @throws Exception
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public Result getAllBook(Integer page) throws Exception{
        Map resultMap = this.bookService.getAllBook(page);
        return new Result<>(resultMap);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateBook(@RequestBody Book book){
        try {
            Book checkBook = this.bookService.getBookById(book.getId());
            if(checkBook == null){
                return new Result(ResultEnum.NOT_FOUND, "该图书不存在");
            }
            int updateResult = this.bookService.updateBook(book);
            if (updateResult == 1){
                return new Result();
            } else if(updateResult == 0){
                return new Result(ResultEnum.SUCCESS , "请修改信息");
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            return new Result(ResultEnum.SQL_ERROR, "修改失败");
        }
    }

    /**
     * 根据id删除书籍
     * @param id 请求目标id
     * @return Result
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteBook(String id){
        try {
            Book checkBook = this.bookService.getBookById(id);
            if(checkBook == null){
                return new Result(ResultEnum.NOT_FOUND, "该图书不存在");
            }
            int deleteResult = this.bookService.deleteBook(id);
            if(deleteResult == 1){
                return new Result();
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            return new Result(ResultEnum.SQL_ERROR, "删除失败");
        }
    }
}
