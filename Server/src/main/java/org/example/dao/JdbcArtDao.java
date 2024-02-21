package org.example.dao;

import org.example.exception.DaoException;
import org.example.model.Art;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcArtDao implements ArtDao
{
    private JdbcTemplate template;
    private final RowMapper<Art> MAPPER = new RowMapper<Art>()
    {
        @Override
        public Art mapRow(ResultSet rs, int i) throws SQLException
        {
            int artId = rs.getInt("art_id");
            String imageName = rs.getString("image_name");
            String caption = rs.getString("caption");
            String description = rs.getString("description");
            int userId = rs.getInt("user_id");
            return new Art(artId, imageName, caption, description, userId);
        }
    };

    public JdbcArtDao(JdbcTemplate template)
    {
        this.template = template;
    }

    @Override
    public List<Art> getArt()
    {
        List<Art> getArt = new ArrayList<>();
        String sql = "";
        try{
            getArt = template.query(sql, MAPPER);
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot get connection. " +e);
        }
        return getArt;
    }

    @Override
    public Art getArtById(int artId)
    {
        List<Art> getArt;
        String sql = "";
        try{
            getArt = template.query(sql, MAPPER, artId);
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot get connection. " +e);
        }
        return getArt.isEmpty() ? null : getArt.get(0);
    }

    @Override
    public List<Art> getArtByUserId(int userId)
    {
        List<Art> getArt;
        String sql = "";
        try{
            getArt = template.query(sql, MAPPER, userId);
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot get connection. " +e);
        }
        return getArt;
    }

    @Override
    public List<Art> getArtByGalleryId(int galleryId)
    {
        List<Art> getArt;
        String sql = "";
        try{
            getArt = template.query(sql, MAPPER, galleryId);
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot get connection. " +e);
        }
        return getArt;
    }

    @Override
    public List<Art> getArtByName(String name, boolean useWildcard)
    {
        List<Art> getArt;
        String sql = "";
        if (useWildcard) {
            name = "%" + (name == null ? "" : name) + "%";
        }
        try {
            getArt = template.query(sql, MAPPER, name);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot get connection. " +e);
        }
        return getArt;
    }
}
