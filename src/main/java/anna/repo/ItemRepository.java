package anna.repo;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import anna.model.Item;
import anna.model.Menu;

public class ItemRepository {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		return new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}
	
	public long save(Item item){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		long id = (long)session.save(item);
		session.getTransaction().commit();
		session.close();
		return id;
	}
	
	public void update(Item item){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
		session.close();
	}
	
	public Item findItemById(long id){
		Session session = sessionFactory.openSession();
		Item item = session.get(Item.class, id);
		session.close();
		return item;
	}
	
	public void delete(Item item){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
		session.close();
	}

}
