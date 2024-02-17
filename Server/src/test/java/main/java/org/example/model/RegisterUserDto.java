package main.java.org.example.model;

import javax.validation.constraints.NotEmpty;

public class RegisterUserDto
{
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmedPassword;

    @NotEmpty(message = "Please select a role for this user.")
    private String role;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getConfirmedPassword()
    {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword)
    {
        this.confirmedPassword = confirmedPassword;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
}
