package main.org.example.dao;

import main.org.example.model.Gallery;
import main.org.example.model.GalleryItem;

import java.util.List;

public interface GalleryDao
{
    List<Gallery> getGalleries();
    List<Gallery> getGalleriesByUserId(int userId);
    Gallery getGalleryByGalleryId(int galleryId);
    Gallery getGalleryByName(String name, boolean useWildcard);
    Gallery createGallery(Gallery gallery);
    Gallery updateGallery(Gallery gallery);
    void deleteGallery(int galleryId);
    GalleryItem getGalleryItemByGalleryId(int galleryId);
    GalleryItem getGalleryItemByGalleryItemId(int galleryItemId);
    GalleryItem createGalleryItem(int galleryId, GalleryItem galleryItem);
    void deleteGalleryItem(int galleryLocationId, int galleryItemId);

}
