package main.org.example.model;

import java.util.Objects;

public class Art
{
    private int artId;
    private String imageName;
    private String caption;
    private String description;
    private int userId;

    public Art(int artId, String imageName, String caption, String description, int userId)
    {
        this.artId = artId;
        this.imageName = imageName;
        this.caption = caption;
        this.description = description;
        this.userId = userId;
    }

    public Art(){}

    public int getArtId()
    {
        return artId;
    }

    public void setArtId(int artId)
    {
        this.artId = artId;
    }

    public String getImageName()
    {
        return imageName;
    }

    public void setImageName(String imageName)
    {
        this.imageName = imageName;
    }

    public String getCaption()
    {
        return caption;
    }

    public void setCaption(String caption)
    {
        this.caption = caption;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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
        Art art = (Art) o;
        return artId == art.artId && userId == art.userId && Objects.equals(imageName, art.imageName) && Objects.equals(caption, art.caption) && Objects.equals(description, art.description);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(artId, imageName, caption, description, userId);
    }

    @Override
    public String toString()
    {
        return "Product" +
                "\nartId: " + artId +
                "\nimageName: '" + imageName + '\'' +
                "\ncaption: '" + caption + '\'' +
                "\ndescription: '" + description + '\'' +
                "\nuserId: " + userId;
    }
}
