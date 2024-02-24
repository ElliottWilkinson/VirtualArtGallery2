package org.example.model;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class Gallery {
    private int galleryId;
    @NotEmpty
    private String galleryName;
    private int userId;

    public Gallery(int galleryId, String galleryName, int userId)
    {
        this.galleryId = galleryId;
        this.galleryName = galleryName;
        this.userId = userId;
    }

    public int getGalleryId()
    {
        return galleryId;
    }

    public void setGalleryId(int galleryId)
    {
        this.galleryId = galleryId;
    }

    public String getGalleryName()
    {
        return galleryName;
    }

    public void setGalleryName(String galleryName)
    {
        this.galleryName = galleryName;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gallery gallery = (Gallery) o;
        return galleryId == gallery.galleryId && userId == gallery.userId && Objects.equals(galleryName, gallery.galleryName);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(galleryId, galleryName, userId);
    }

    @Override
    public String toString()
    {
        return "Gallery" +
                "\ngalleryId: " + galleryId +
                "\ngalleryName: '" + galleryName + '\'' +
                "\nuserId: " + userId;
    }
}
