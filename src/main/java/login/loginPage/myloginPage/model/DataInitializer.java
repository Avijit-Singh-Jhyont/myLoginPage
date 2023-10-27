package login.loginPage.myloginPage.model;

import login.loginPage.myloginPage.emails_related.TokenManager;
import login.loginPage.myloginPage.model.UserEntity;
import login.loginPage.myloginPage.model.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//This service generates a default user for use on the account
@Service
public class DataInitializer implements ApplicationRunner {

  @Autowired UserRepo userRepo;
  @Autowired PasswordEncoder passwordEncoder;

  @Override
  public void run(ApplicationArguments args) throws Exception{
    UserEntity userEntity=new UserEntity();
    userEntity.setUsername("user@example.com");
    userEntity.setPassword(passwordEncoder.encode("pass"));
    userEntity.setRoles("USER");
    userEntity.setActive(true);
    TokenManager tokenManager = new TokenManager();
    userEntity.setToken(tokenManager.tokenGenerator());
    userRepo.save(userEntity);
  }

}
