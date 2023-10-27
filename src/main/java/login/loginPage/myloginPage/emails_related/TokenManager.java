package login.loginPage.myloginPage.emails_related;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenManager {

  public String tokenGenerator(){ return UUID.randomUUID().toString(); }
  public String verifLinkGenerator(String token){ return "http://localhost:8080/verify?token=" + token; }
  public String newAcctVerifEmailSubline(){ return "Activate your Springboot App account"; }
  public String newAcctVerifEmailContent(String token) {
    return "Thank you for Registering with us.\n Please click below link to complete your registration and " +
      "activate your account:\n"+verifLinkGenerator(token);
  }

}
