import static org.junit.jupiter.api.Assertions.assertEquals;

//import javax.naming.directory.InvalidAttributeValueException;

import org.junit.jupiter.api.Test;

import characters.Dice;
import characters.Dwarf;
import characters.FuriousMummy;

public class DwarfFuriousMummyTest {

	@Test
	public void testDwarf() {
		Dwarf dopey = new Dwarf("Dopey");

		assertEquals(2, dopey.getWeapon().getAttack());
		assertEquals("Handaxe", dopey.getWeapon().getName());

		assertEquals(2, dopey.getAttack());
		dopey.getWeapon().setAttack(10);
		assertEquals(10, dopey.getAttack());
		dopey.setWeapon(null);
		assertEquals(1, dopey.getAttack());
		assertEquals(2, dopey.getDefence());
		assertEquals(6, dopey.getMovement());
		assertEquals(7, dopey.getBody());
	}

	@Test
	public void testFuriousMummy() {
		FuriousMummy eddie = new FuriousMummy("Eddie");

		assertEquals(3, eddie.getAttack());
		assertEquals(4, eddie.getDefence()); // as a normal mummy
		assertEquals(4, eddie.getMovement());
		assertEquals(2, eddie.getBody());

		Dice.setSeed(1L);
		assertEquals(2, eddie.attack());

		// it does not block any impacts
		eddie.defend(1);
		assertEquals(2, eddie.getBody());
	}


}
