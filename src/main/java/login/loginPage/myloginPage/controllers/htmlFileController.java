package login.loginPage.myloginPage.controllers;

import jakarta.servlet.http.HttpServletRequest;
import login.loginPage.myloginPage.emails_related.TokenManager;
import login.loginPage.myloginPage.model.UserEntity;
import login.loginPage.myloginPage.model.UserRepo;
import login.loginPage.myloginPage.emails_related.EmailSender;
import login.loginPage.myloginPage.setup.AdminCredentialsHolder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class htmlFileController {

  @Autowired PasswordEncoder passwordEncoder;
  @Autowired UserRepo userRepo;
  @Autowired EmailSender emailSender;
  @Autowired TokenManager tokenManager;

  @GetMapping("/login")
  public String login(){ return "login"; }

  @GetMapping("/register")
  public String register(){ return "register"; }
  @PostMapping("/register")
  public String submittedRegistration(@RequestParam String userEmailAddr, @RequestParam String password){
    UserEntity createUser = new UserEntity();
    createUser.setUsername(userEmailAddr); createUser.setPassword(passwordEncoder.encode(password));
    createUser.setRoles("USER");
    String token = tokenManager.tokenGenerator(); createUser.setToken(token);
    userRepo.save(createUser); //New User added/registered
    //Sending email now
    String TO, SUBLINE, CONTENT;
    TO = userEmailAddr;
    SUBLINE = tokenManager.newAcctVerifEmailSubline();
    CONTENT = tokenManager.newAcctVerifEmailContent(token);
    emailSender.sendAnEmail(TO,SUBLINE,CONTENT);
    return "redirect:/verifyRegistrationNotification";
  }
  @GetMapping("/verifyRegistrationNotification")
  public String verifyRegistrationNotification(){
    return "verifyRegistrationNotification";
  }

  @GetMapping("/verify")
  public String verifyNewAcct( @RequestParam("token") String token){
    UserEntity newUser = userRepo.findByToken(token);
    //convert below if stmts to switch stmts later on
    if(newUser != null){
      newUser.setActive(true); userRepo.save(newUser);
      return "redirect:/acctActivated";
    }
    else { return "redirect:/err"; }
  }

  @GetMapping("/acctActivated")
  public String acctActivated(){ return "acctActivated"; }

  @SneakyThrows
  @GetMapping("/dashboard")
  public String myDashboard(){
    return "dashboard";
  }

  @GetMapping("/")
  public String welcome(){ return "welcome"; }

}
