package model;

import util.Password;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Пользователь системы
 */
@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = User.ALL_USERS, query = "select u from User u"),
        @NamedQuery(name = User.FIND_BY_LOGIN, query = "select u from User u where u.login = :login")
})
public class User {
    public static final String ALL_USERS = "User.allUsers";
    public static final String FIND_BY_LOGIN = "User.findByLogin";
    @OneToMany
    @JoinColumn
    List<Account> accounts = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String login;

    /**
     * Хеш пароля
     */
    private String passwordHash;

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setPassword(String password) throws Exception {
        this.passwordHash = Password.getSaltedHash(password);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }
}
