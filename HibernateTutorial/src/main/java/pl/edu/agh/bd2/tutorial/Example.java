package main.java.pl.edu.agh.bd2.tutorial;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import main.java.pl.edu.agh.bd2.tutorial.dto.ForumThread;
import main.java.pl.edu.agh.bd2.tutorial.dto.Location;
import main.java.pl.edu.agh.bd2.tutorial.dto.Post;
import main.java.pl.edu.agh.bd2.tutorial.dto.User;
import main.java.pl.edu.agh.bd2.tutorial.dto.User.Sex;
import main.java.pl.edu.agh.bd2.tutorial.persiscence.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class Example {
	
	public static Date parseDate(int year, int month, int day) {
		return new GregorianCalendar(year,month,day).getTime();
	}
	
	public static void waitForInput(String msg) {
		System.out.println("[info] " + msg);
		Scanner sc = new Scanner(System.in);
		while(!sc.nextLine().equals(""));
	}

	public static void createExample() {
		waitForInput("Creating sample data");
		User user1 = new User("user123","Krakow",
				parseDate(2004,12,10),20,Sex.FEMALE);
		User user2 = new User("inny_user", "Gdansk", 
				parseDate(1999,10,2),32,Sex.MALE);
		User user3 = new User("jeszcze_inny","Katowice",
				parseDate(2010,6,2),17,Sex.MALE);
		
		ForumThread thread = new ForumThread("Bazy danych",
				parseDate(2014,04,01),user1);
		
		Post post1 = new Post(thread,"Jakas tresc posta zwiazana albo nie z tematem",
				parseDate(2014,04,01),new Location("Krakow","130.152.10.25"));
		Post post2 = new Post(thread,"Odpowiedz w tym samym watku",
				parseDate(2014,04,02),new Location("Krakow","50.123.51.13"));
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(user1);
		session.save(user2);
		session.save(user3);
		
		session.save(thread);
		
		session.save(post1);
		session.save(post2);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public static void readAndUpdateExample() {
		
		waitForInput("Reading user with login user123 and thread with id 1");
		Session session = HibernateUtil.getSessionFactory().openSession();
		User user = (User) session.load(User.class, "user123");
		System.out.println("Read user with name: " + user.getLogin() + " and age " + user.getAge());
		
		ForumThread thread = (ForumThread) session.load(ForumThread.class, 1l);
		System.out.println("Read thread with title: " + thread.getTitle());
		
		waitForInput("Updating thread title");
		session.beginTransaction();
		thread.setTitle("Zaktualizowany temat watku");
		session.update(thread);
		session.getTransaction().commit();
		
		waitForInput("Getting list of posts related to this thread");
		@SuppressWarnings("unchecked")
		List<Post> posts = session.createQuery("from Post where THREAD_ID = 1").list();
		for (Post p : posts) {
			System.out.println(p.getContent());
		}
		
		waitForInput("Getting list of users with age <= 25 and living in city starting from letter K");
		@SuppressWarnings("unchecked")
		List<User> users = session.createCriteria(User.class)
				     	  .add(Restrictions.le("age", 25))
				     	  .add(Restrictions.like("city", "K%"))
				     	  .list();
		
		for (User u : users) {
			System.out.println(u.getLogin());
		}
		
		session.close();	
	}
	
	public static void deleteAndErrorHandleExample() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		waitForInput("Deleting post with id = 2");
		session.beginTransaction();
		Post post = (Post) session.load(Post.class, 2l);
		session.delete(post);
		
		session.getTransaction().commit();
		
		try {
			waitForInput("Trying to delete it again");
			Post deletedPost = (Post) session.load(Post.class, 2l);
			deletedPost.setContent("abc");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		User user = new User("nowy_user","Poznan",new Date(),40,Sex.FEMALE);
		ForumThread unsavedThread = new ForumThread("niezapisany_watek",new Date(), user);
		post.setThread(unsavedThread);
		
		waitForInput("Trying to add post to unexisting in database thread");
		try {
			session.beginTransaction();
			
			session.save(user);
			session.save(post);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
		
		session.close();
		
	}
	
	public static void main(String[] args) {
		
		createExample();
		readAndUpdateExample();
		deleteAndErrorHandleExample();
		
		waitForInput("Shutting down session factory");
		HibernateUtil.shutdown();
	}

}
