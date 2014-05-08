package main.java.pl.edu.agh.bd2.tutorial.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="USERS")
public class User {

	public enum Sex {
        MALE, FEMALE
    }
	
	@Id
	private String login;
	
	private String city;
	
	@Temporal(TemporalType.DATE)
	@Column(name="JOIN_DATE")
	private Date joinDate;
	
	private int age;
	
	private Sex gender;
	
	public User(){}
	
	public User(String login, String city, Date joinDate, int age, Sex gender) {
		this.login = login;
		this.city = city;
		this.joinDate = joinDate;
		this.age = age;
		this.gender = gender;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getJoinDateDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Sex getGender() {
		return gender;
	}
	public void setGender(Sex gender) {
		this.gender = gender;
	}
	
	
}
