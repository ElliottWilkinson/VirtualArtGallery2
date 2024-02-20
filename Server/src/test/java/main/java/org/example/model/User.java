package main.java.org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.java.org.example.dao.UserDao;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class User
{
    private int userId;
    private String userName;
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private boolean activated;
    private Set<Authority> authorities = new HashSet<>();

    public User(){}

    public User(int userId, String userName, String password, String email, String authorities)
    {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.activated = true;
        this.email = email;
        if(authorities != null) this.setAuthorities(authorities);
    }
    public User(String userName, String password, String email, String authorities )
    {
        this(0, userName, password, email,authorities);
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public boolean isActivated()
    {
        return activated;
    }

    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }

    public Set<Authority> getAuthorities()
    {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities)
    {
        this.authorities = authorities;
    }
    public String getAuthoritiesString() {
        String authString = "";
        for (Authority auth : authorities) {
            if (authString.length() == 0) {
                authString += auth.getName();
            } else {
                authString += "," + auth.getName();
            }
        }
        return authString;
    }

    public void setAuthorities(String authorities) {
        String[] roles = authorities.split(",");
        for(String role : roles) {
            String authority = role.contains("ROLE_") ? role : "ROLE_" + role.toUpperCase();
            this.authorities.add(new Authority(authority));
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(userName, user.userName) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, userName, password);
    }

    @Override
    public String toString()
    {
        return "User" +
                "\nuserId= " + userId +
                "\nuserName= '" + userName + '\'' +
                "\npassword= '" + password + '\'';
    }

}
