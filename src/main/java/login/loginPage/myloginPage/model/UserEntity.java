package login.loginPage.myloginPage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data //a shortcut for @Getter, @Setter, @ToString, @RequiredArgsConstructor
public class UserEntity {

  @Id private String username;
  private String password;
  private String roles;
  private String token;
  private boolean isActive=false;

}
