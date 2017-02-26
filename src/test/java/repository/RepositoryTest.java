package repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import anna.model.Item;
import anna.model.Menu;
import anna.repo.ItemRepository;
import anna.repo.MenuRepository;

public class RepositoryTest {
	
    MenuRepository menuRepo;
    ItemRepository itemRepo;
    long menuId;
    long item1Id;
    long item2Id;

	
	
	@Before
	public void setup() throws Exception{
		menuRepo = new MenuRepository();
		itemRepo = new ItemRepository();
		
		item1Id=itemRepo.save(new Item("cake","chocolate cake",12));
		item2Id=itemRepo.save(new Item("frog","chocolate frog",7));

		menuId = menuRepo.save(new Menu("desserts"));
	}
	
	@Test
	public void shouldAddItemToMenu(){
		// arrange
		Menu m = menuRepo.findMenuById(menuId);
		Item i = itemRepo.findItemById(item1Id);
		Item i2 = itemRepo.findItemById(item2Id);

		// act
		m.getItems().add(i);
		m.getItems().add(i2);

		menuRepo.update(m); // menu owns the relationship (unidirectional)
//		itemRepo.update(i); // <-- this saves are not needed
//		itemRepo.update(i2);

		
		// assert
		assertTrue(menuRepo.findMenuById(menuId).getItems().contains(i));
		assertTrue(menuRepo.findMenuById(menuId).getItems().contains(i2));
		assertEquals(menuRepo.findMenuById(menuId).getItems().size(), 2);
	}
	
	
	
	

}
