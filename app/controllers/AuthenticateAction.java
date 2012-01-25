package controllers;

import com.google.gson.Gson;

import play.data.validation.Required;
import play.mvc.Controller;
import models.SecurityIdent;
import models.User;

public class AuthenticateAction extends Controller {

  public static void login(@Required String username, @Required String password){
	
	  //Création de l'objet de retour
	  SecurityIdent securityIdent = new SecurityIdent();
	  //On va chercher en base de donnée le user
	  User user = User.findById(username);
	  	  
	  //Si le user n'existe pas en base on le créé
	  if(user == null){
		  User newUser = new User(username, password);
		  newUser.save();
		  
		  //On ne renvoie pas le mdp
		  newUser.password = "";
		  securityIdent.identStatus = true;
		  securityIdent.userConnect = newUser;
	  }else{
		  if(user.password.equals(password)){
			  securityIdent.identStatus = true;
			  //On ne renvoie pas le mdp
			  user.password = "";
			  securityIdent.userConnect = user;
		  }else{
			  securityIdent.identStatus = false;
		  }
	  }
	 renderJSON(securityIdent);
  }
  
  /**
   * Temporaire : pour le débuggage .
   */
  public static void showAll(){
	  renderJSON(User.findAll());
  }
  
  public static void getPassword(String username){
	  User user = User.findById(username);
	  renderJSON(user.password);
  }  
}