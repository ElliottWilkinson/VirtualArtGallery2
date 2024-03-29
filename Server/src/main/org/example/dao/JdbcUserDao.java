package main.org.example.dao;

import main.org.example.exception.DaoException;
import main.org.example.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Component
public class JdbcUserDao implements UserDao
{
    private JdbcTemplate template;
    private final RowMapper<User> MAPPER = new RowMapper()
    {
        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException
        {
            int id = rs.getInt("user_id");
            String email = rs.getString("email_address");
            String username = rs.getString("user_name");
            String role = rs.getString("role");
            String password = rs.getString("password_hash");
            return new User(id, username, password, email, role);
        }
    };

    public JdbcUserDao(JdbcTemplate template)
    {
        this.template = template;
    }

    @Override
    public List<User> getUsers()
    {
        List<User> users;
        String sql = "SELECT * FROM user_info ORDER BY user_name";
         try
         {
             users = template.query(sql, MAPPER);
         }catch(CannotGetJdbcConnectionException e)
         {
            throw new DaoException("Cannot get connection. " + e);
         }
         return users;
    }

    @Override
    public User getUserById(int userId)
    {
        List<User> users;
        String sql = "SELECT * FROM user_info WHERE user_id = ?";
        try
        {
            users = template.query(sql, MAPPER, userId);
        }catch(CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Cannot get connection. " + e);
        }
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User getUserByUsername(String username)
    {
        List<User> users;
        String sql = "SELECT * FROM user_info WHERE user_name = ?";
        try
        {
            users = template.query(sql, MAPPER, username);
        }
        catch(CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Cannot get connection. " + e);
        }
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User createUser(User newUser)
    {

        String sql = "INSERT INTO user_info (user_name, email_address, password, role) VALUES (?,?,?,?) RETURNING user_id";
        if(newUser.getPassword() == null)
        {
            throw new DaoException("User cannot be created with null password");
        }
        try
        {
            String passwordHash = new BCryptPasswordEncoder().encode(newUser.getPassword());
            int userId = template.update(sql,int.class, newUser.getUserName(),  newUser.getEmail(), passwordHash, newUser.getAuthoritiesString());
            return getUserById(userId);
        }catch(CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Cannot get connection. " +e);
        }catch(DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation. " +e);
        }
    }

    @Override
    public User updateUser(User updatedUser)
    {
        String sql = "";
        try
        {
            String passwordHash = new BCryptPasswordEncoder().encode(updatedUser.getPassword());
            int userId = template.update(sql, int.class, updatedUser.getUserId(), updatedUser.getPassword(), updatedUser.getEmail(), passwordHash, updatedUser.getAuthoritiesString());
            return getUserById(userId);
        }catch(CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Cannot get connection. " + e);
        }
    }

    @Override
    public void deleteUser(int id)
    {
        String sql = "";
        try
        {
            template.update(sql, id);
        }catch(CannotGetJdbcConnectionException e){
             throw new DaoException("Cannot get connection. " +e);
        }
    }


}
