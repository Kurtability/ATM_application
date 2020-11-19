package LiteSnacks.backend.UserAccount;

public class UserAccount {

    private String userName;
    private String password;

    private String role;
    //private boolean isLoggedIn;

    public UserAccount() {

    }

    public UserAccount(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String toString(){
        return "Username: " + userName + ", " + "Password: " + password + ", " + "Role: " + role;
    }


//    public boolean getLoggedInStatus(){
//        return isLoggedIn;
//    }
//
//    public void setLoggedInStatus(boolean set){
//        isLoggedIn = set;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String new_role) {
        this.role = new_role;
    }
}