package tools.models;

public class AuthModel {

    private String login;
    private String pwd;

    public AuthModel(String login, String pwd) {
        this.login = login;
        this.pwd = pwd;
    }

    public AuthModel() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}