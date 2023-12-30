#include <iostream>
#include <fstream>
#include <string>

using namespace std;
int count1;
int count2;
int count3;
int count4;
int count5;
int count6;
int count7;
int count8;
int count9;
int count10;
int i4 = 0;
int i5 = 0;

void checkNames(string temp5[])
{
	for (int i = 0; i < 13; i++)
	{
		if (temp5[2] == temp5[i])
		{
			count1 = count1 + 1;
		}
		if (temp5[3] == temp5[i])
		{
			count2 = count2 + 1;
		}
		if (temp5[4] == temp5[i])
		{
			count3 = count3 + 1;
		}
		if (temp5[5] == temp5[i])
		{
			count4 = count4 + 1;
		}
		if (temp5[6] == temp5[i])
		{
			count5 = count5 + 1;
		}if (temp5[8] == temp5[i])
		{
			count6 = count6 + 1;
		}
		if (temp5[9] == temp5[i])
		{
			count7 = count7 + 1;
		}
		if (temp5[10] == temp5[i])
		{
			count8 = count8 + 1;
		}
		if (temp5[11] == temp5[i])
		{
			count9 = count9 + 1;
		}
		if (temp5[12] == temp5[i])
		{
			count10 = count10 + 1;
		}
	}
}
void displayNames(string temp5[])
{
      cout << "Frequency Of The Names" << endl;
      cout << temp5[2] << ": " << count1 << endl;
      cout << temp5[3] << ": " << count2 << endl;
      cout << temp5[4] << ": " << count3 << endl;
      cout << temp5[5] << ": " << count4 << endl;
      cout << temp5[6] << ": " << count5 << endl;
      cout << temp5[8] << ": " << count6 << endl;
      cout << temp5[9] << ": " << count7 << endl;
      cout << temp5[10] << ": " << count8 << endl;
      cout << temp5[11] << ": " << count9 << endl;
      cout << temp5[12] << ": " << count10 << endl;
}
int main()
{
    fstream file4, file5;
	string read4;
	file4.open("Names.txt", ios::in | ios::app);
	file5.open("Names2.txt", ios::out | ios::app);
	while (file4.good())
	{
		getline(file4, read4);
		file5 << read4 <<endl;

	}
	file4.close();
	file5.close();
	file5.open("Names2.txt", ios::out | ios::app);
	string read5;
	for (int i = 0; i < 5; i++)
	{
		getline(cin, read5);
		file5 << read5 << endl;
	}
	file5.close();
	file5.open("Names2.txt", ios::in | ios::app);
	string temp5[13];
	while (file5.good())
	{
		getline(file5, temp5[i5]);
		i5++;
	};
	file5.close();
	checkNames(temp5);
	displayNames(temp5);
};
