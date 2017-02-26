package anna.repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import anna.model.Item;
import anna.model.Menu;

public class MenuRepository {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		return new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}
	
	public long save(Menu menu){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		long id = (long)session.save(menu);
		session.getTransaction().commit();
		session.close();
		return id;
	}
	
	public void update(Menu menu){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(menu);
		session.getTransaction().commit();
		session.close();
	}
	
	public Menu findMenuById(long id){
		return findMenuByIdAndLoadItems(id);
	}
	
	public Menu findMenuByIdAndLoadItems(long id){
		Session session = sessionFactory.openSession(); // open
		
		Menu menu = session.get(Menu.class, id);
		
		// forces hibernate to load items lazily, with open session
		for (Item i : menu.getItems()) 
			System.out.println(i);
		
		session.close();
		return menu; // with items already loaded, despite having LAZY fetch
	}
	
	public void delete(Menu menu){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(menu);
		session.getTransaction().commit();
		session.close();
	}
}
