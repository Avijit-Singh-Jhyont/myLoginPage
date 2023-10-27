package login.loginPage.myloginPage.emails_related;

public class EmailNotifications {

  public static String NewRegistrationEmail_Subject(){ return "Registration complete!"; }
  public static String NewRegistrationEmail_Content(){
    return """
      Congrats! Registration compelete!
      """;
  }

}
