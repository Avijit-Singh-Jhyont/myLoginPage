package login.loginPage.myloginPage.setup;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class ConfigFxNodes {

  public void btnHovering(Button submitBtn){
    submitBtn.hoverProperty().addListener( (observable, oldValue, isHovering) -> {
      if(isHovering) submitBtn.setCursor(Cursor.HAND);
      else submitBtn.setCursor(Cursor.DEFAULT);
    });
  }

}
