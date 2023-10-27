package login.loginPage.myloginPage.emails_related;

import jakarta.annotation.PostConstruct;
import login.loginPage.myloginPage.setup.AdminCredentialsHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import java.util.Properties;

@Service
public class EmailSender{

  private final JavaMailSenderImpl emailSender;
  private final String username = AdminCredentialsHolder.getHolderObj().getEmail();
  private final String password = AdminCredentialsHolder.getHolderObj().getEncryptedPassword();

  @Value("${spring.mail.host}")  private String host;
  @Value("${spring.mail.port}") private int port;
  @Value("${spring.mail.properties.mail.transport.protocol}") private String protocol;
  @Value("${spring.mail.properties.mail.smtp.auth}") private String auth;
  @Value("${spring.mail.properties.mail.smtp.starttls.enable}") private String starttls;

  public EmailSender() throws Exception {  //Exception added for password var sake
    this.emailSender = new JavaMailSenderImpl();
    this.emailSender.setUsername(username);
    this.emailSender.setPassword(password);
  }

  @PostConstruct
  public void init(){
    this.emailSender.setHost(host);
    this.emailSender.setPort(port);
    Properties mailProperties = new Properties();
    mailProperties.put("mail.transport.protocol", protocol);
    mailProperties.put("mail.smtp.auth", auth);
    mailProperties.put("mail.smtp.starttls.enable", starttls);
    this.emailSender.setJavaMailProperties((mailProperties));
  }

  public void sendAnEmail(String TO, String SUBLINE, String CONTENT){
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setTo(TO);
    msg.setSubject(SUBLINE);
    msg.setText(CONTENT);
    emailSender.send(msg);
  }

}