package ba.fit.java.spring.mvc.viewmodels;

public class LoginVM {
    public String username;
    public String password;

    public LoginVM(String username, String password, boolean zapamtiPassword) {
        this.username = username;
        this.password = password;
        this.zapamtiPassword = zapamtiPassword;
    }

    public boolean zapamtiPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isZapamtiPassword() {
        return zapamtiPassword;
    }

    public void setZapamtiPassword(boolean zapamtiPassword) {
        this.zapamtiPassword = zapamtiPassword;
    }
}
