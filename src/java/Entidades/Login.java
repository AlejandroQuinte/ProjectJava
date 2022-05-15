/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author alequ
 */
public class Login {
    //atributos
    private int ID_Login;
    private String user;
    private String password;
    //Metodos
    public Login(int ID_Login, String user, String password) {
        this.ID_Login = ID_Login;
        this.user = user;
        this.password = password;
    }

    public Login() {
        this.ID_Login = 0;
        this.user = "";
        this.password = "";
    }

    public int getID_Login() {
        return ID_Login;
    }

    public void setID_Login(int ID_Login) {
        this.ID_Login = ID_Login;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

}
