package org.example.controllers;

import org.example.dao.UserDao;
import org.example.exception.DaoException;
import org.example.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class UserController
{
    private UserDao userDao;
    public UserController(UserDao userDao)
    {
        this.userDao = userDao;
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getUsers(){
        List<User> getUsers = new ArrayList<>();
        try
        {
            getUsers = userDao.getUsers();
        }catch(ResponseStatusException e){
            throw new DaoException("Dao exception: " +e);
        }
        return getUsers;
    }


}
