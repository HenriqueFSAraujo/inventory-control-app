package service;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DataBaseConnection {
	//Conexão 	
	
	private static EntityManager entityManager;
	
	public static void conectar() {
		try {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BancoPU");
			entityManager = entityManagerFactory.createEntityManager();
			System.out.println("Conexão realizada com sucesso!");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
