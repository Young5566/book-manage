package com.Young.book.controller;

import com.Young.book.po.Librarian;
import com.Young.book.service.LibrarianService;
import com.Young.book.utils.Result;
import com.Young.book.utils.ResultEnum;
import com.Young.book.utils.Utils;
import com.Young.book.utils.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/librarian")
@CrossOrigin
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;

    /**
     * 修改个人信息
     * @param librarian 要修改的人员信息
     * @return Result
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateLibrarian(@RequestBody Librarian librarian){
        try {
            Librarian checkibrarian = this.librarianService.getLibrarianById(librarian.getId());
            if(checkibrarian == null){
                return new Result(ResultEnum.NOT_FOUND, "该管理员不存在");
            }
            int updateResult = this.librarianService.updateLibrarian(librarian);
            if(updateResult == 1){
                return new Result();
            } else {
                throw new Exception();
            }
        }catch (Exception e){
            System.out.println(e.toString());
            return new Result(ResultEnum.SQL_ERROR, "修改图书管理员信息失败");
        }
    }

    /**
     * 查询用户信息
     * @param id 请求用户的
     * @return Result
     */
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ResponseBody
    public Result getLibrarianById(String id){
        try {
            Librarian librarian = this.librarianService.getLibrarianById(id);
            if(librarian == null){
                return new Result(ResultEnum.NOT_FOUND, "该图书管理员不存在");
            } else {
                return new Result<>(librarian);
            }
        } catch (Exception e){
            System.out.println(e.toString());
            return new Result(ResultEnum.SQL_ERROR, "查询图书管理员信息失败");
        }
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteLibrarian(String id){
        try {
            Librarian checkibrarian = this.librarianService.getLibrarianById(id);
            if(checkibrarian == null){
                return new Result(ResultEnum.NOT_FOUND, "该管理员不存在");
            }
            int deleteResult = this.librarianService.deleteLibrarian(id);
            if(deleteResult == 1){
                return new Result();
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            System.out.println(e.toString());
            return new Result(ResultEnum.SQL_ERROR, "删除失败");
        }
    }

    /**
     * 注册人员信息
     * @param registInfo 用户信息
     * @return Result
     */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ResponseBody
    public Result regist(@RequestBody Librarian registInfo){
        try {
            Librarian librarian = this.librarianService.getLibrarianByUsername(registInfo.getUsername());
            if(librarian != null){
                return new Result(ResultEnum.EXITED, "该用户名已存在");
            }
            registInfo.setId(Utils.getUUid());
            int addResult = this.librarianService.addLibrarian(registInfo);
            if(addResult == 1){
                return new Result();
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            System.out.println(e.toString());
            return new Result(ResultEnum.SQL_ERROR, "注册失败");
        }
    }

    /**
     * 登陆
     * @param loginInfo 登陆信息
     * @return Result
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody Librarian loginInfo){
        try {
            if(loginInfo.getUsername().equals("") || loginInfo.getPassword().equals("")){
                return new Result(ResultEnum.PARAMS_ERROR, "用户名或密码不可为空");
            }
            Librarian librarian = this.librarianService.getLibrarianByUsername(loginInfo.getUsername());
            if(librarian == null){
                return new Result(ResultEnum.NOT_FOUND, "该图书管理员不存在");
            }
            if(!librarian.getPassword().equals(loginInfo.getPassword())){
                return new Result(ResultEnum.LOGIN_ERROR, "密码错误");
            }
            String token = JWT.createToken(librarian);
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("token", token);
            resultMap.put("username", librarian.getUsername());
            resultMap.put("id", librarian.getId());
            return new Result<>(resultMap);
        } catch (Exception e){
            System.out.println(e.toString());
            return new Result(ResultEnum.SQL_ERROR, "登陆失败");
        }
    }
}
