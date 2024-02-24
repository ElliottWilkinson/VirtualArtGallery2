package org.example.dao;

import org.example.exception.DaoException;
import org.example.model.Gallery;
import org.example.model.GalleryItem;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcGalleryDao implements GalleryDao
{
    private JdbcTemplate template;
    private final RowMapper<Gallery> GALLERY_MAPPER = new RowMapper<Gallery>()
    {
        @Override
        public Gallery mapRow(ResultSet rs, int i) throws SQLException
        {
            int galleryId = rs.getInt("gallery_id");
            String galleryName = rs.getString("gallery_name");
            int userId = rs.getInt("user_id");
            return new Gallery(galleryId, galleryName, userId);
        }
    };
    private final RowMapper<GalleryItem> GALLERY_ITEM_MAPPER = new RowMapper<GalleryItem>()
    {
        @Override
        public GalleryItem mapRow(ResultSet rs, int i) throws SQLException
        {
            int galleryItemId = rs.getInt("gallery_item_id");
            int galleryId = rs.getInt("gallery_id");
            int GalleryId = rs.getInt("Gallery_id");
            return new GalleryItem(galleryItemId, galleryId, GalleryId);
        }
    };
    public JdbcGalleryDao(JdbcTemplate template)
    {
        this.template = template;
    }

    @Override
    public List<Gallery> getGalleries()
    {
        List<Gallery> getGallery;
        String sql = "";
        try{
            getGallery = template.query(sql, GALLERY_MAPPER);
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot get connection. " +e);
        }
        return getGallery;
    }

    @Override
    public List<Gallery> getGalleriesByUserId(int userId)
    {
        List<Gallery> getGallery;
        String sql = "";
        try{
            getGallery = template.query(sql, GALLERY_MAPPER, userId);
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot get connection. " +e);
        }
        return getGallery;
    }

    @Override
    public Gallery getGalleryByGalleryId(int galleryId)
    {
        List<Gallery> getGallery;
        String sql = "";
        try{
            getGallery = template.query(sql, GALLERY_MAPPER, galleryId);
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot get connection. " +e);
        }
        return getGallery.isEmpty() ? null : getGallery.get(0);
    }

    @Override
    public Gallery getGalleryByName(String name, boolean useWildcard)
    {
        List<Gallery> getGallery;
        String sql = "";
        if (useWildcard) {
            name = "%" + (name == null ? "" : name) + "%";
        }
        try {
            getGallery = template.query(sql, GALLERY_MAPPER, name);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot get connection. " +e);
        }
        return getGallery.isEmpty() ? null: getGallery.get(0);
    }


    @Override
    public Gallery createGallery(Gallery gallery)
    {
        String sql = "";

        try
        {
            int galleryId = template.update(sql,int.class, gallery.getGalleryName(), gallery.getUserId());
            return getGalleryByGalleryId(galleryId);
        }catch(CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Cannot get connection. " +e);
        }catch(DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation. " +e);
        }
    }

    @Override
    public Gallery updateGallery(Gallery gallery)
    {
        String sql = "";
        try{
            int galleryId = template.update(sql, int.class, gallery.getGalleryId(), gallery.getGalleryName(), gallery.getUserId());
            return getGalleryByGalleryId(galleryId);
        }catch(CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Cannot get connection. " + e);
        }
    }

    @Override
    public void deleteGallery(int galleryId)
    {
        String sql = "";
        try
        {
            template.update(sql, galleryId);
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot get connection. " +e);
        }
    }

    @Override
    public GalleryItem getGalleryItemByGalleryId(int galleryId)
    {

        List<GalleryItem> getGalleryItem;
        String sql = "";
        try{
            getGalleryItem = template.query(sql, GALLERY_ITEM_MAPPER, galleryId);
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot get connection. " +e);
        }
        return getGalleryItem.isEmpty() ? null : getGalleryItem.get(0);
    }

    @Override
    public GalleryItem getGalleryItemByGalleryItemId(int galleryItemId)
    {

        List<GalleryItem> getGalleryItem;
        String sql = "";
        try{
            getGalleryItem = template.query(sql, GALLERY_ITEM_MAPPER, galleryItemId);
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot get connection. " +e);
        }
        return getGalleryItem.isEmpty() ? null : getGalleryItem.get(0);
    }

    @Override
    public GalleryItem createGalleryItem(int galleryId, GalleryItem galleryItem)
    {
        String sql = "";

        try
        {
            int galleryItemId = template.update(sql,int.class, galleryItem.getGalleryId(), galleryItem.getGalleryId());
            return getGalleryItemByGalleryItemId(galleryItemId);
        }catch(CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Cannot get connection. " +e);
        }catch(DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation. " +e);
        }
    }

    @Override
    public void deleteGalleryItem(int galleryLocationId, int galleryItemId)
    {
        String sql = "";
        try
        {
            template.update(sql, galleryItemId);
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot get connection. " +e);
        }
    }
}
