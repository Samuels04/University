import java.util.Scanner;

//import javax.swing.text.html.HTMLDocument.HTMLReader.FormAction;

import characters.*;

/**
 * Programming Methodology Practice.
 * Jeroquest - An example of Object Oriented Programming.
 * Main program
 * 
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo Cerdán
 * @version 1
 *
 */

public class JeroquestTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Barbarian conan = new Barbarian("Conan2");
		Barbarian atila = new Barbarian("Atila");
		Barbarian kros = new Barbarian("Kros");
		Barbarian kurd = new Barbarian("Kurd");

		Mummy ramses = new Mummy("Ramses");
		Mummy tutankamon = new Mummy("Tutankamon");
		Mummy cleopatra = new Mummy("Cleopatra");
		Mummy keops = new Mummy("Keops");

		System.out.println(ramses);
		System.out.println(conan);

		// Conan attacks Ramses
		int impacts = conan.attack();
		ramses.defend(impacts);

		// if Ramses is alive attacks Conan
		if (ramses.isAlive())
			conan.defend(ramses.attack());

		System.out.println(ramses);
		System.out.println(conan);

		// #region Exercise c)

		System.out.println("   ");
		System.out.println("--------- Exercise c) ---------");
		System.out.println();

		int attacks = 0;
		int attacksCounter = 0;

		System.out.print("How many attacks do you want to perform?");
		attacks = sc.nextInt();

		while ((attacksCounter <= attacks) && (conan.isAlive() && ramses.isAlive())) {

			// Ramses attacks, Conan defends and we increase the nº attacks done
			conan.defend(ramses.attack());
			attacksCounter++;

			// Then Conan attacks, Ramses defends and we increase again the nº attacks done
			ramses.defend(conan.attack());
			attacksCounter++;

		}
		// #endregion
		// #region Exercise d)

		System.out.println("   ");
		System.out.println("--------- Exercise d) ---------");
		System.out.println();

		Barbarian[] vectorBarbarians = { conan, atila, kros, kurd };
		Mummy[] vectorMummies = { ramses, tutankamon, cleopatra, keops };

		// as the lengths are the same in the 2 arrays
		int length = vectorBarbarians.length;

		System.out.print("How many rounds do you want? ");
		int rounds = sc.nextInt();

		int turn = 0;

		for (int roundCounter = 0; roundCounter <= rounds; roundCounter++) {
			while (turn < length) {

				// first is the turn of the barbarians

				int mummyToAttack = Dice.roll(length);

				if (vectorBarbarians[turn].isAlive() && vectorMummies[mummyToAttack - 1].isAlive()) {

					vectorMummies[mummyToAttack - 1].defend(vectorBarbarians[turn].attack());
				}

				// then is the turn of the mummy

				int barbarianToAttack = Dice.roll(length);

				if (vectorMummies[turn].isAlive() && vectorBarbarians[barbarianToAttack - 1].isAlive()) {

					vectorBarbarians[barbarianToAttack - 1].defend(vectorMummies[turn].attack());

				}

				turn++;
			}
			if (allDead(vectorBarbarians) || allDead(vectorMummies))
				break;
		}
		// #endregion
		// #region Exercise e)

		System.out.println("   ");
		System.out.println("--------- Exercise e) ---------");
		System.out.println();

		System.out.print("How many rounds do you want? ");
		int rounds2 = sc.nextInt();

		for (int roundCounter = 0; roundCounter <= rounds2; roundCounter++) {
			while (turn < length) {

				// first is the turn of the barbarians

				Mummy mummyToAttack = getHighestBody(vectorMummies);

				if (vectorBarbarians[turn].isAlive() && mummyToAttack.isAlive()) {

					mummyToAttack.defend(vectorBarbarians[turn].attack());
				}

				// then is the turn of the mummy

				Barbarian barbarianToAttack = getHighestBody(vectorBarbarians);

				if (vectorMummies[turn].isAlive() && barbarianToAttack.isAlive()) {

					barbarianToAttack.defend(vectorMummies[turn].attack());

				}

				turn++;
			}
			if (allDead(vectorBarbarians) || allDead(vectorMummies))
				break;
		}
		// #endregion
		// #region Exercise f)
		BubbleSortHtL(vectorBarbarians);
		bubbleSortHtL(vectorMummies);

		System.out.println("Mummies ordered from highest to lowest Body value: ");
		for (Mummy i : vectorMummies)
			System.out.println(i);

		System.out.println("Barbarians ordered from highest to lowest Body value: ");
		for (Barbarian i : vectorBarbarians)
			;
		System.out.println(i);
		sc.close();
	}

	public static boolean allDead(Barbarian[] vector) {
		int dead = 0;
		for (Barbarian i : vector) {
			if (i.isAlive())
				return false;
			else {
				dead++;
			}
		}
		return dead == vector.length;
	}

	public static boolean allDead(Mummy[] vector) {
		int dead = 0;
		for (Mummy i : vector) {
			if (i.isAlive())
				return false;
			else {
				dead++;
			}
		}
		return dead == vector.length;
	}

	public static Barbarian getHighestBody(Barbarian[] vector) {
		BubbleSort(vector);
		return vector[vector.length - 1];
	}

	public static Mummy getHighestBody(Mummy[] vector) {
		BubbleSort(vector);
		return vector[vector.length - 1];
	}

	public static Barbarian[] BubbleSortLtH(Barbarian[] vector) {
		Barbarian aux;
		for (int i = 0; i < vector.length - 1; i++) {
			for (int j = 0; j < vector.length - i - 1; i++) {
				if (vector[i].getBody() > vector[i + 1].getBody()) {
					aux = vector[i];
					vector[i] = vector[i + 1];
					vector[i + 1] = aux;
				}
			}
		}
		return vector;
	}

	public static Mummy[] BubbleSortLtH(Mummy[] vector) {
		Mummy aux;
		for (int i = 0; i < vector.length - 1; i++) {
			for (int j = 0; j < vector.length - i - 1; i++) {
				if (vector[i].getBody() > vector[i + 1].getBody()) {
					aux = vector[i];
					vector[i] = vector[i + 1];
					vector[i + 1] = aux;
				}
			}
		}
		return vector;
	}

	public static Barbarian[] BubbleSortHtL(Barbarian[] vector) {
		Barbarian aux;
		for (int i = 0; i < vector.length - 1; i++) {
			for (int j = 0; j < vector.length - i - 1; i++) {
				if (vector[i].getBody() < vector[i + 1].getBody()) {
					aux = vector[i];
					vector[i] = vector[i + 1];
					vector[i + 1] = aux;
				}
			}
		}
		return vector;
	}

	public static Mummy[] bubbleSortHtL(Mummy[] vector) {
		Mummy aux;
		for (int i = 0; i < vector.length - 1; i++) {
			for (int j = 0; j < vector.length - i - 1; i++) {
				if (vector[i].getBody() < vector[i + 1].getBody()) {
					aux = vector[i];
					vector[i] = vector[i + 1];
					vector[i + 1] = aux;
				}
			}
		}
		return vector;
	}
}

// TODO Do exercises e & f and add javadoc for created methods