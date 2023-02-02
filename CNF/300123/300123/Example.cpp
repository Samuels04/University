#include <iostream>

using std::cout;
using std::cin;
using std::endl;

void ChangetoZero(int v[], unsigned int vectorLength) {
	for (unsigned int i = 0; i < vectorLength; i++) {
		if (v[i] < 0) {
			v[i] = 0;
		}
	}
	return;
}
void printArray(int v[], unsigned int vectorLength) {
	unsigned int i;
	for (i = 0; i < vectorLength; i++) {
		cout << v[i] << endl;
	}
	cout << endl;
}

int main() {
	unsigned int vectorLength = 6;
	int vector1[] = { -3,-2,-1,1,2,3 };
	int vector2[] = { -4,-5,-6,4,5,6 };

	cout << "vector1: " << endl;
	printArray(vector1, vectorLength);
	cout << "vector2: " << endl;
	printArray(vector2, vectorLength);

	ChangetoZero(vector1, vectorLength);
	ChangetoZero(vector2, vectorLength);

	cout << "vector1 now: " << endl;
	printArray(vector1, vectorLength);
	cout << "vector2 now: " << endl;
	printArray(vector2, vectorLength);

	return 0;

}