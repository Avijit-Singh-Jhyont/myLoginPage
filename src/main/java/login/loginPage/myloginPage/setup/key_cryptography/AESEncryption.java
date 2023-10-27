package login.loginPage.myloginPage.setup.key_cryptography;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;


public class AESEncryption {
  private static final String ALGO = "AES";
  private static final byte[] keyValue = new byte[]{'w','j','k','k','w','j','k','f','f','j','g','u','r','u','j','i',};
  public static String encryptKey(String plainTextVal) throws Exception{
    Key key = generateKey();
    Cipher c = Cipher.getInstance(ALGO);
    c.init(Cipher.ENCRYPT_MODE, key);
    byte[] encValue = c.doFinal( plainTextVal.getBytes() );
    return Base64.getEncoder().encodeToString(encValue);
  }
  public static String decryptKey(String encryptedVal) throws Exception{
    Key key = generateKey();
    Cipher c = Cipher.getInstance(ALGO);
    c.init(Cipher.DECRYPT_MODE, key);
    byte[] decodedVal = Base64.getDecoder().decode(encryptedVal);
    byte[] decValue = c.doFinal(decodedVal);
    return new String(decValue);
  }

  private static Key generateKey() throws Exception{
    return new SecretKeySpec(keyValue, ALGO);
  }

}
