#include <iostream>

using std::cout;
using std::endl;
using std::string;

int NumberOfVowels(string str) {
	int numberOfVowels = 0;
	for (char c : str) {
		if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
			numberOfVowels++;
		}
	}
	return numberOfVowels;
}
int NumberOfConsonants(string str) {
	int numberOfConsonants = 0;
	for (char c : str) {
		if (!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
			numberOfConsonants++;
		}
	}
	return numberOfConsonants;
}

int main() {
	string str = "Marta";
	string str2 = "Samuel";

	cout << "String 1 has " << NumberOfVowels(str) << " vowels and " << NumberOfConsonants(str) << " consonants. " << endl;
	cout << "String 2 has " << NumberOfVowels(str2) << " vowels and " << NumberOfConsonants(str2) << " consonants. " << endl;

}