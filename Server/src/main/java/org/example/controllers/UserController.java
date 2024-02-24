package org.example.controllers;

import org.example.dao.ArtDao;
import org.example.dao.GalleryDao;
import org.example.dao.UserDao;
import org.example.exception.DaoException;
import org.example.model.Art;
import org.example.model.Gallery;
import org.example.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController
{
    private UserDao userDao;
    private ArtDao artDao;
    private GalleryDao galleryDao;
    public UserController(UserDao userDao, ArtDao artDao, GalleryDao galleryDao)
    {
        this.userDao = userDao;
        this.artDao = artDao;
        this.galleryDao = galleryDao;
    }

    @RequestMapping( method = RequestMethod.GET)
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

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int id){
        try{
            User user = userDao.getUserById(id);
            if(user == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The user with id=%d was not found.", id));
            }
            return user;
        }catch(DaoException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dao Exception. " + e.getMessage());
        }
    }
    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public User getUserByUserName(@PathVariable String username){
        try{
            User user = userDao.getUserByUsername(username);
            if(user == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The user with username=%sername was not found", username));
            }
            return user;
        }catch(DaoException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dao Exception. " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpStatus createUser(@RequestBody User newUser){
        try{
            User user = userDao.createUser(newUser);
            return HttpStatus.CREATED;
        }catch(DaoException e){
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Dao Exception. " + e.getMessage());
        }
    }
    @RequestMapping(method = RequestMethod.PUT)
    public HttpStatus updateUser(@RequestBody User newUser){
        try{
            User user = userDao.updateUser(newUser);
            return HttpStatus.OK;
        }catch(DaoException e){
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Dao Exception. " + e.getMessage());
        }
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    HttpStatus deleteUser(@RequestBody User deletedUser){
        try{
            userDao.deleteUser(deletedUser.getUserId());
            return HttpStatus.NO_CONTENT;
        }catch(DaoException e){
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Dao Exception. " + e.getMessage());
        }
    }
    @RequestMapping(path = "/{id}/art", method = RequestMethod.GET)
    public List<Art> getArtByUserId(@PathVariable int id){
        try{
            List<Art> art = artDao.getArtByUserId(id);
            if(art == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The art with id=%d was not found.", id));
            }
            return art;
        }catch(DaoException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dao Exception. " + e.getMessage());
        }
    }
    @RequestMapping(path = "/{id}/Gallery", method = RequestMethod.GET)
    public List<Gallery> getGalleryByUserId(@PathVariable int id){
        try{
            List<Gallery> gallery = galleryDao.getGalleriesByUserId(id);
            if(gallery == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The Gallery with id=%d was not found.", id));
            }
            return gallery;
        }catch(DaoException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dao Exception. " + e.getMessage());
        }
    }
}
