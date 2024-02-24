package org.example.controllers;

import org.example.dao.ArtDao;
import org.example.dao.GalleryDao;
import org.example.exception.DaoException;
import org.example.model.Art;
import org.example.model.Gallery;
import org.example.model.GalleryItem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/gallery")
public class GalleryController
{
    private GalleryDao galleryDao;
    private ArtDao artDao;

    public GalleryController(GalleryDao galleryDao, ArtDao artDao)
    {
        this.galleryDao = galleryDao;
        this.artDao = artDao;
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<Gallery> getGallery(){
        try{
            return galleryDao.getGalleries();

        }catch(DaoException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dao Exception. " +e.getMessage());
        }
    }
    @RequestMapping(path = "/{galleryName}", method = RequestMethod.GET)
    public Gallery getGalleryByGalleryName(@PathVariable String galleryName){
        try{
            Gallery Gallery = galleryDao.getGalleryByName(galleryName, true);
            if(Gallery == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The gallery with gallery Name=%alleryName was not found", galleryName));
            }
            return Gallery;

        }catch(DaoException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dao Exception. " +e.getMessage());
        }
    }
    @RequestMapping(path = "/item/{id}", method = RequestMethod.GET)
    public GalleryItem getGalleryItemByGalleryItemId(@PathVariable int id){
        try{
            GalleryItem galleryItem = galleryDao.getGalleryItemByGalleryItemId(id);
            if(galleryItem == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The Gallery with id=%d was not found.", id));
            }
            return galleryItem;
        }catch(DaoException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dao Exception. " + e.getMessage());
        }

    }
    @RequestMapping(path = "/{id}/item", method = RequestMethod.GET)
    public GalleryItem getGalleryItemByGalleryId(@PathVariable int id){
        try{
            GalleryItem galleryItem = galleryDao.getGalleryItemByGalleryId(id);
            if(galleryItem == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The Gallery with id=%d was not found.", id));
            }
            return galleryItem;
        }catch(DaoException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dao Exception. " + e.getMessage());
        }
    }
    @RequestMapping(path = "/{id}/art", method = RequestMethod.GET)
    public List<Art> getArtByGalleryId(@PathVariable int id){
        try{
            List<Art> art = artDao.getArtByGalleryId(id);
            if(art == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The Gallery with id=%d was not found.", id));
            }
            return art;
        }catch(DaoException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dao Exception. " + e.getMessage());
        }
    }
    @RequestMapping(method = RequestMethod.POST)
    public HttpStatus createGallery(@RequestBody Gallery newgallery){
        try{
            Gallery gallery = galleryDao.createGallery(newgallery);
            return HttpStatus.CREATED;
        }catch(DaoException e){
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Dao Exception. " + e.getMessage());
        }
    }
    @RequestMapping(method = RequestMethod.PUT)
    public HttpStatus updateGallery(@RequestBody Gallery newgallery){
        try{
            Gallery gallery = galleryDao.updateGallery(newgallery);
            return HttpStatus.OK;
        }catch(DaoException e){
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Dao Exception. " + e.getMessage());
        }
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    HttpStatus deleteGallery(@RequestBody Gallery deletedgallery){
        try{
            galleryDao.deleteGallery(deletedgallery.getGalleryId());
            return HttpStatus.NO_CONTENT;
        }catch(DaoException e){
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Dao Exception. " + e.getMessage());
        }
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Gallery getByGalleryId(@PathVariable int id){
        try{
            Gallery gallery = galleryDao.getGalleryByGalleryId(id);
            if(gallery == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The Gallery with id=%d was not found.", id));
            }
            return gallery;
        }catch(DaoException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dao Exception. " + e.getMessage());
        }

    }

    @RequestMapping(path = "{id}/item", method = RequestMethod.POST)
    public HttpStatus createGalleryItem(@RequestBody GalleryItem newGalleryItem, @PathVariable int galleryId){
        try{
            GalleryItem galleryItem = galleryDao.createGalleryItem(galleryId, newGalleryItem);
            return HttpStatus.CREATED;
        }catch(DaoException e){
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Dao Exception. " + e.getMessage());
        }
    }
    @RequestMapping(path = "{id}/item/{id}", method = RequestMethod.DELETE)
    HttpStatus deleteGalleryItem(@RequestBody GalleryItem deletedGalleryItem, @RequestBody Gallery galleryLocation){
        try{
            galleryDao.deleteGalleryItem(galleryLocation.getGalleryId(), deletedGalleryItem.getGalleryItemId());
            return HttpStatus.NO_CONTENT;
        }catch(DaoException e){
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Dao Exception. " + e.getMessage());
        }
    }
}
