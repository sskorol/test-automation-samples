package io.github.sskorol.tmp;

/**
 * Author: io.github.sskorol
 */
public class LoginPage {

    public HomePage loginWith(User user) {
        return typeUsername(user.getUsername())
                .typePassword(user.getPassword())
                .login();
    }

    public LoginPage typeUsername(String name) {
        System.out.println("Type " + name);
        return this;
    }



    public LoginPage typePassword(String password) {
        System.out.println("Type" + password);
        return this;
    }

    public HomePage login() {
        System.out.println("Click login");
        return new HomePage();
    }
}
