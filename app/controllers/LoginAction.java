package controllers;

import play.data.validation.Required;
import play.mvc.Controller;

public class LoginAction extends Controller {

  public static void login(@Required String username, @Required String password){
	
	  //On va chercher en base de donnée le user
	  
  }
}