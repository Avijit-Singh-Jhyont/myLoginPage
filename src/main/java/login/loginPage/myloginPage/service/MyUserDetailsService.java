/*
package login.loginPage.myloginPage.service;

import login.loginPage.myloginPage.model.UserEntity;
import login.loginPage.myloginPage.model.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    UserEntity userEntity =
      userRepo.findById(username).orElseThrow( ()->new UsernameNotFoundException("Not found: "+username) );

    return new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
  }

}
*/

package login.loginPage.myloginPage.service;

import login.loginPage.myloginPage.model.UserEntity;
import login.loginPage.myloginPage.model.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    UserEntity userEntity =
      userRepo.findById(username).orElseThrow( ()->new UsernameNotFoundException("Username: "+username+"is not found. Register and try again") );
    if(!userEntity.isActive()) throw new DisabledException("Account is not activated. Please check your 'account " +
      "verification' email and click on the link to complete registration. If link expired, Register again");

    return new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
  }

}
