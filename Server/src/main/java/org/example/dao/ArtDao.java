package org.example.dao;

import org.example.model.Art;

import java.util.List;

public interface ArtDao
{
    List<Art> getArt();
    Art getArtById(int artId);
    List<Art> getArtByUserId(int userId);
    List<Art> getArtByGalleryId(int galleryId);
    List<Art> getArtByName(String name, boolean useWildCard);
}
