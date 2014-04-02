package main.java.pl.edu.agh.bd2.tutorial;

import java.util.GregorianCalendar;

import main.java.pl.edu.agh.bd2.tutorial.dto.ForumThread;
import main.java.pl.edu.agh.bd2.tutorial.dto.Location;
import main.java.pl.edu.agh.bd2.tutorial.dto.Post;
import main.java.pl.edu.agh.bd2.tutorial.dto.User;
import main.java.pl.edu.agh.bd2.tutorial.dto.User.Sex;
import main.java.pl.edu.agh.bd2.tutorial.persiscence.HibernateUtil;

import org.hibernate.Session;

public class Example {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		User someUser = new User("someforumuser","Krakow",
				new GregorianCalendar(1992,12,10).getTime(),20,Sex.FEMALE);
		ForumThread thread = new ForumThread("Bazy danych",
				new GregorianCalendar(2014,04,01).getTime(),someUser);
		Post post = new Post(thread,"Jakas tresc posta zwiazana albo nie z tematem",
				new GregorianCalendar(2014,04,01).getTime(),new Location("Krakow","130.152.10.25"));
		
		
		/* Zapisywanie danych do bazy */
		session.beginTransaction();
		
		session.save(someUser);
		//session.save(thread);
		//session.save(post);
		
		session.getTransaction().commit();
		session.close();
		
		someUser = null;
		
		/* Wczytywanie z bazy */
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		/* Przed klucz glowny */
		
		session.getTransaction().commit();
		
		session.close();
		HibernateUtil.shutdown();
	}

}
