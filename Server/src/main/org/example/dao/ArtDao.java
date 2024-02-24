package main.org.example.dao;

import main.org.example.model.Art;

import java.util.List;

public interface ArtDao
{
    List<Art> getArt();
    Art getArtById(int artId);
    List<Art> getArtByUserId(int userId);
    List<Art> getArtByGalleryId(int galleryId);
    Art getArtByName(String name, boolean useWildCard);
}
