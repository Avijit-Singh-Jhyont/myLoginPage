package login.loginPage.myloginPage.setup;

public enum MsgTypeEnum {

  DESCRIPTION("""
    To continue, please provide below your google email address. Also, you would have to generate and provide a google app password for 
    use with this application(google serach "how to generate an app password for a google account" and choose a more recent result for instructions). 
    The provided credentials will be used to send registration-activation emails to newly registered user accounts in this demonstration:\n\n
    """),
  DISCLAIMER("""
    \nThis application is for demonstration purposes only. Please do not create user accounts
      with email addresses that you do not have explicit permission to use for the purpose stated above. as every account
      created will send emails to registered email addresses for account activation, registration confirmation, and
      other relavant notification-purposes at appropriate times. Creator not liable for any damages, grief,
      distress, or for any other negative circumstances arising from the use/misuse of this application, as a whole or
       in part, or through any features therein.
       Your admin credentials will be stored in your local machine's RAM only and will only remain for the duration of each session.
       The app password is encrypted under AESEncryption technology during the session; use of app passwords is highly recommended.
                             
       Hope you enjoy this demonstration :)
    """);

  private final String msg;
  MsgTypeEnum(String msg){ this.msg = msg; }
  public String getMsg(){ return msg; }

}
