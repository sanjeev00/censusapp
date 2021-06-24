package trainingweb.utilities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtility {
	public static EntityManagerFactory emf  = Persistence.createEntityManagerFactory("jpa-home");;
	public static EntityManager em = emf.createEntityManager();;
    
}
