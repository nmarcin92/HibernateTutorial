package main.java.pl.edu.agh.bd2.tutorial.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Location {

	private String city;
	
	@Column(name="IP_ADDRESS")
	private String IP;
	
	public Location() {}
	public Location(String city, String IP) {
		this.city = city;
		this.IP = IP;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	
	
}
