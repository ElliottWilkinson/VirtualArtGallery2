package main.java.org.example.model;

public class LoginDto
{
    private String username;
    private String password;
    private String emailAddress;

    public LoginDto(String usernameOrEmailAddress, String password)
    {
        this.username = usernameOrEmailAddress;
        this.password = password;
        this.emailAddress = usernameOrEmailAddress;
    }

    public LoginDto(String username, String password, String emailAddress)
    {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

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

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }
}
