package org.example.controllers;

import org.example.dao.ArtDao;
import org.example.exception.DaoException;
import org.example.model.Art;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/art")
public class ArtController
{
    private ArtDao artDao;
    public ArtController(ArtDao artDao)
    {
        this.artDao = artDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Art> getArt(){
        try{
                return artDao.getArt();

        }catch(DaoException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dao Exception. " +e.getMessage());
        }
    }

    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public Art getArtByUsername(@PathVariable String username){
        try{
            Art art = artDao.getArtByName(username, true);
            if(art == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The user with username=%sername was not found", username));
            }
                return art;

        }catch(DaoException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dao Exception. " +e.getMessage());
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Art getByArtId(@PathVariable int id){
        try{
            Art art = artDao.getArtById(id);
            if(art == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The art with id=%d was not found.", id));
            }
            return art;
        }catch(DaoException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dao Exception. " + e.getMessage());
        }

    }



}
