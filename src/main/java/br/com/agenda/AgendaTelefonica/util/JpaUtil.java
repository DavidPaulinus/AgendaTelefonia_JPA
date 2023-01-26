package br.com.agenda.AgendaTelefonica.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("agendaTelefonica");
	
	
	public static EntityManager getEntityManager() {
		return EMF.createEntityManager();
	}
}
