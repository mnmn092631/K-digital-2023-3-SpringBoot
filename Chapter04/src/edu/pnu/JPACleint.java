package edu.pnu;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPACleint {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();

		insertData(em);
		updateData(em, (long) 4, "새로운 타이틀");
		deleteData(em, (long) 6);

		em.close();
		emf.close();

	}

	private static void insertData(EntityManager em) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			for (int i = 1; i <= 10; i++) {
				Board b = new Board();
				b.setTitle("Title" + i);
				b.setWriter("Writer" + i);
				b.setContent("Content" + i);
				b.setCreateDate(new Date());
				b.setCnt((long) i);
				em.persist(b);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	private static void updateData(EntityManager em, long seq, String title) {
		Board b = em.find(Board.class, seq); // select
		b.setTitle(title);

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(b);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	private static void deleteData(EntityManager em, long seq) {
		EntityTransaction tx = em.getTransaction();

		Board b = em.find(Board.class, seq);
		try {
			tx.begin();
			em.remove(b);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

}
