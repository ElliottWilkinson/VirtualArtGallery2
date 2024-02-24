package org.example.model;

import java.util.Objects;

public class GalleryItem
{
    private int galleryItemId;
    private int galleryId;
    private int artId;

    public GalleryItem(int galleryItemId, int galleryId, int artId)
    {
        this.galleryItemId = galleryItemId;
        this.galleryId = galleryId;
        this.artId = artId;
    }

    public int getGalleryItemId()
    {
        return galleryItemId;
    }

    public void setGalleryItemId(int galleryItemId)
    {
        this.galleryItemId = galleryItemId;
    }

    public int getGalleryId()
    {
        return galleryId;
    }

    public void setGalleryId(int galleryId)
    {
        this.galleryId = galleryId;
    }

    public int getArtId()
    {
        return artId;
    }

    public void setArtId(int artId)
    {
        this.artId = artId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GalleryItem that = (GalleryItem) o;
        return galleryItemId == that.galleryItemId && galleryId == that.galleryId && artId == that.artId;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(galleryItemId, galleryId, artId);
    }

    @Override
    public String toString()
    {
        return "GalleryItem" +
                "\ngalleryItemId: " + galleryItemId +
                "\ngalleryId: " + galleryId +
                "\nartId:" + artId;
    }
}
