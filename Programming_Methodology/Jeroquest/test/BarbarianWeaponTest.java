

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import characters.*;
import weapons.Weapon;


public class BarbarianWeaponTest {

	@Test
	public void testWeapon() {
		Weapon weapon = new Weapon("Broadsword", 3);

		assertEquals("Broadsword", weapon.getName());
		assertEquals(3, weapon.getAttack());

		// Weapon.setAttack() must update the attack of the weapon
		weapon.setAttack(10);
		assertEquals(10, weapon.getAttack());
		// Weapon.toString() returns the name of the weapon
		assertTrue(weapon.toString().indexOf("Broadsword") > -1);
		// Weapon.toString() returns the attack of the weapon
		assertTrue(weapon.toString().indexOf("10") > -1);
	}

	@Test
	public void testWeaponAttribute() {
		Barbarian conan = new Barbarian("Conan");

		assertEquals(3, conan.getWeapon().getAttack());
		assertEquals("broadsword", conan.getWeapon().getName());
		// Barbarian.toString() returns the name of the weapon
		assertTrue(conan.toString().indexOf("broadsword") > -1);
		// Barbarian.toString() returns the attack of the weapon
		assertTrue(conan.toString().indexOf("3") > -1);
	}

	@Test
	public void testBarbarianGetAttack() {
		Barbarian conan = new Barbarian("Conan");

		assertEquals(3, conan.getAttack());
		conan.setWeapon(null);
		assertEquals(1, conan.getAttack());
	}

	@Test
	public void testBarbarianAttack() {
		Barbarian conan = new Barbarian("Conan");
		conan.getWeapon().setAttack(10);

		Dice.setSeed(0L);
		assertEquals(7, conan.attack());
		conan.setWeapon(null);
		assertEquals(1, conan.attack());
	}
}
