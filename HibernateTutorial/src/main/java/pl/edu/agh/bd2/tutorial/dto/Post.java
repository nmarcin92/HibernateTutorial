package main.java.pl.edu.agh.bd2.tutorial.dto;

import java.util.Date;

public class Post {
	
	private long id;
	private ForumThread thread;
	private String content;
	private Date writingDate;
	private String location;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public ForumThread getThread() {
		return thread;
	}
	public void setThread(ForumThread thread) {
		this.thread = thread;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWritingDate() {
		return writingDate;
	}
	public void setWritingDate(Date writingDate) {
		this.writingDate = writingDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
