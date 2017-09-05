package by.htp.webpr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "login")
	@NotNull(message="is required")
	@Size(min=1,message="is required")
	private String login;

	@Column(name = "password")
	@NotNull(message="is required")
	@Size(min=1,message="is required")
	private String password;

	public User() {

	}

	public User(String name, String surname) {
		this.login = name;
		this. password = surname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String name) {
		this.login = name;
	}

	public String getPassword() {
		return  password;
	}

	public void setPassword(String surname) {
		this. password = surname;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + login + ", surname=" +  password + "]";
	}

}
