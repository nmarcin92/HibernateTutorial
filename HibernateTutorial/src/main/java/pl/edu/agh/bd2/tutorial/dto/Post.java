package main.java.pl.edu.agh.bd2.tutorial.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="POSTS")
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="POST_ID")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="THREAD_ID")
	private ForumThread thread;

	@Lob
	private String content;
	
	@Temporal(TemporalType.DATE)
	@Column(name="WRITING_DATE")
	private Date writingDate;
	
	@Embedded
	private Location location;
	
	public Post() {}
	
	public Post(ForumThread thread, String content, Date writingDate,
			Location location) {
		this.thread = thread;
		this.content = content;
		this.writingDate = writingDate;
		this.location = location;
	}
	
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
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
}
