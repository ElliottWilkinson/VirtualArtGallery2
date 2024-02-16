package main.java.org.example.services;
import org.springframework.web.client.RestTemplate; //Add maven repositiory org.springframework
import gallery.model.loginDto;
import gallery.model.tokenDto;
public class authenticationService
{
    private String apiBaseUrl; //Figure out how to find out which host the user is on.
    private final RestTemplate restTemplate = new RestTemplate();

    public String login(String usernameOrEmail, String password){
        loginDto loginDto = new loginDto(usernameOrEmail, password);
    }
}
