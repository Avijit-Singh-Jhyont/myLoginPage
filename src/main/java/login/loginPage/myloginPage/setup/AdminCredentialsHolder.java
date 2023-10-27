package login.loginPage.myloginPage.setup;

import login.loginPage.myloginPage.setup.key_cryptography.AESEncryption;

public class AdminCredentialsHolder {

  private static AdminCredentialsHolder holderObj;
  private String encryptedPassword;
  private String email;
  private AdminCredentialsHolder() {}
  public static AdminCredentialsHolder getHolderObj(){
    if(holderObj==null) holderObj = new AdminCredentialsHolder();
    return holderObj;
  }

  public String getEncryptedPassword() throws Exception{ return AESEncryption.decryptKey(this.encryptedPassword); }
  public void setEncryptedPassword(String pass) throws Exception{
    if(this.encryptedPassword == null) this.encryptedPassword = AESEncryption.encryptKey(pass);
    else throw new IllegalStateException("Admin Password already assigned");
  }

  public String getEmail() { return email; }
  public void setEmail(String email){
    if(this.email == null) this.email = email;
    else throw new IllegalStateException("Admin Email already assigned");
  }

}
