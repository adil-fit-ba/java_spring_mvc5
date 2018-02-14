package ba.fit.java.spring.mvc.viewmodels;

public class LoginVM {
    public String username;
    public String password;
    public boolean ZapamtiPassword;

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
        return ZapamtiPassword;
    }

    public void setZapamtiPassword(boolean zapamtiPassword) {
        ZapamtiPassword = zapamtiPassword;
    }
}
