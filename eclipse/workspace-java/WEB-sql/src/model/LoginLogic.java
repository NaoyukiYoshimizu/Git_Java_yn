package model;

public class LoginLogic {
  public boolean execute(Users users) {
    if (users.getPass().equals("1234")) {
      return true;
    }
    return false;
  }
}