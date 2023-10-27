package login.loginPage.myloginPage;

import javafx.application.Application;
import login.loginPage.myloginPage.setup.AdminCredentialsRetriever;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MyloginPageApplication {

	public static void main(String[] args) {
		Application.launch(AdminCredentialsRetriever.class,args);
		SpringApplication.run(MyloginPageApplication.class, args);
		System.out.println("""
   *************************************************************************
 		THANK YOU FOR ENTERING YOUR CREDENTIALS
 		PLEASE PROCEED TO A BROWSER AND OPEN LINK: http://localhost:8080
 		""");
	}

}
