#include <iostream>
#include <fstream>
using namespace std;
fstream file4;
char num[3][3] =  {{'1','2','3'},
                   {'4','5','6'},
                   {'7','8','9'}};
char xo = 'X';
int Count;
void tictactoeboard();
void check(int num1);
void changeplayer();
void checkwinner(string *p);
void resetboard();
int main()
{
    string welc;
    int num1;
    string player[2];
    file4.open("Board.txt", ios::out);
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            file4 << num[i][j];
        }
    }
    file4.close();
    cout << "---------------------                                                                               ----------------" << endl;
    cout << "                                      WELCOME TO TIC TAC TOE" << endl;
    cout << "---------------------                                                                               ----------------" << endl;
        cout << "PLEASE ENTER THE NAME OF THE FIRST PLAYER:"; 
            cin >> player[0];
        cout << "PLEASE ENTER THE NAME OF THE SECOND PLAYER:"; 
            cin >> player[1];
            tictactoeboard();
    while (1)
    {
        cout << player[0] << ":";
        cin >> num1;
        Count++;
        check(num1);
        tictactoeboard();
        changeplayer();
        checkwinner(player);
        cout << player[1] << ":";
        cin >> num1;
        Count++;
        check(num1);
        tictactoeboard();
        changeplayer();
        checkwinner(player);
    }
}
void tictactoeboard()
{
    cout << endl;
    cout << "                                          | " << num[0][0] << " |  " << num[0][1] << " | " << num[0][2] << " |" << endl;
    cout << "                                          | " << num[1][0] << " |  " << num[1][1] << " | " << num[1][2] << " |" << endl;
    cout << "                                          | " << num[2][0] << " |  " << num[2][1] << " | " << num[2][2] << " |" << endl;
}

void check(int num1)
{
    switch (num1)
    {
    case 1:
        num[0][0] = xo;
        break;
    case 2:
        num[0][1] = xo;
        break;
    case 3:
        num[0][2] = xo;
        break;
    case 4:
        num[1][0] = xo;
        break;
    case 5:
        num[1][1] = xo;
        break;
    case 6:
        num[1][2] = xo;
        break;
    case 7:
        num[2][0] = xo;
        break;
    case 8:
        num[2][1] = xo;
        break;
    case 9:
        num[2][2] = xo;
        break;
    }
}
void changeplayer()
{
    if (xo == 'X')
    {
        xo = 'O';
    }
    else
    {
        xo = 'X';
    }
}
 void checkwinner(string *p)
    {
     char answ;
     if (num[0][0] + num[0][1] + num[0][2] == 264 || num[1][0] + num[1][1] + num[1][2] == 264 || num[2][0] + num[2][1] + num[2][2] == 264 ||
         num[0][0] + num[1][0] + num[2][0] == 264 || num[0][1] + num[1][1] + num[2][1] == 264 || num[0][2] + num[1][2] + num[2][2] == 264 ||
         num[0][0] + num[1][1] + num[2][2] == 264 || num[0][2] + num[1][1] + num[2][0] == 264)
     {
         cout << p[0] << " has won!" << endl;
         cout << "Play again?(Y/N)";
         cin >> answ;
         if (answ == 'Y')
         {
             resetboard();
             tictactoeboard();
         }
         else
         {
             exit(1);
         }
     }
     if (num[0][0] + num[0][1] + num[0][2] == 237 || num[1][0] + num[1][1] + num[1][2] == 237 || num[2][0] + num[2][1] + num[2][2] == 237 ||
         num[0][0] + num[1][0] + num[2][0] == 237 || num[0][1] + num[1][1] + num[2][1] == 237 || num[0][2] + num[1][2] + num[2][2] == 237 ||
         num[0][0] + num[1][1] + num[2][2] == 237 || num[0][2] + num[1][1] + num[2][0] == 237)
     {
         cout << p[1] << " has won!" << endl;
         cout << "Play again?(Y/N)";
         cin >> answ;
         if (answ == 'Y')
         {
             resetboard();
             tictactoeboard();
         }
         else
         {
             exit(1);
         }
     }
     if (Count == 9)
     {
         cout << "tie!" << endl;
         cout << "Play again?(Y/N)";
         cin >> answ;
         if (answ == 'Y')
         {
             resetboard();
             tictactoeboard();
         }
         else
         {
             exit(1);
         }
     }
    }
 void resetboard()
 {
     file4.open("Board.txt", ios::in);
     for (int i = 0; i < 3; i++)
     {
         for (int j = 0; j < 3; j++)
         {
             file4 >> num[i][j];
         }
     }
     file4.close();
 }
