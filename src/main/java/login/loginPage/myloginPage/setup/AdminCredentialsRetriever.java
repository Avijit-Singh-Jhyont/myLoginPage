package login.loginPage.myloginPage.setup;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.apache.commons.validator.routines.EmailValidator;

public class AdminCredentialsRetriever extends Application {

  @Override
  public void start(Stage primaryStage){
    ConfigFxNodes configFxNodes = new ConfigFxNodes();
    //Adding Title,description, and disclaimer
    Label title = new Label("Hello, IMPORTANT NOTE:");
    Label description = new Label(MsgTypeEnum.DESCRIPTION.getMsg());
    Label disclaimer = new Label(MsgTypeEnum.DISCLAIMER.getMsg()); disclaimer.setTextFill(Color.RED);
    //Adding textboxes and btns 2 submit creds, quit app, etc
    Label adminEmailLabel = new Label("ADMIN EMAIL:");
    TextField adminEmailPrompt = new TextField();
    Label generatedAppPasswordLabel = new Label("GENERATED APP PASSWORD:");
    PasswordField appPasswordPrompt = new PasswordField();
    Button submitBtn = new Button("Submit"); Button quitBtn = new Button("Quit");
    //common eventHandlers for appPasswordPrompt & submitBtn
    EventHandler<ActionEvent> submitTrigger = e-> {
      String emailPassword = (String) appPasswordPrompt.getText();
      String potentialEmail = (String)adminEmailPrompt.getText();
      if(validateAdminEmail(potentialEmail)) {
        assignAdminEmail( (String)adminEmailPrompt.getText() );
        assignAdminPassword(emailPassword);
        Stage s = (Stage) submitBtn.getScene().getWindow(); s.close();
      }
      else System.out.println("***In-Valid Email or Password***");
    };
    submitBtn.setOnAction(submitTrigger);
    // Hover property on btns
    configFxNodes.btnHovering(submitBtn); configFxNodes.btnHovering(quitBtn);
    //Organizing items in, and presenting, primaryStage
    HBox hBox = new HBox(submitBtn, quitBtn);
    VBox vbox = new VBox(title, description, adminEmailLabel, adminEmailPrompt, generatedAppPasswordLabel, appPasswordPrompt, hBox, disclaimer);
    vbox.setPadding(new Insets(50,50,50,50));
    Scene scene = new Scene(vbox,880, 880);
    quitBtn.setOnAction(e->{  System.exit(0);  });
    primaryStage.setOnCloseRequest(event->{ System.exit(0); });
    primaryStage.setScene(scene); primaryStage.show();
  }

  private boolean validateAdminEmail(String potentialEmail) {
    if(EmailValidator.getInstance().isValid(potentialEmail)) System.out.println("****a valid email****");
    else System.out.println("****NOT A valid email****");
    return EmailValidator.getInstance().isValid(potentialEmail);
  }

  private void assignAdminEmail(String email) {
    AdminCredentialsHolder.getHolderObj().setEmail(email);
    System.out.println( "Valid Email: " + AdminCredentialsHolder.getHolderObj().getEmail() );
  }

  @SneakyThrows
  private void assignAdminPassword(String emailPassword) {
    AdminCredentialsHolder.getHolderObj().setEncryptedPassword(emailPassword);
//    UnComment Below to display your generated app password in the console
//    System.out.println( "Valid Password: " + AdminCredentialsHolder.getHolderObj().getEncryptedPassword() );
  }

}
