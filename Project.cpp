#include <iostream>
#include <fstream>
using namespace std;

 char num[3][3] = {{'1','2','3'},
                  {'4','5','6'},
                  {'7','8','9'}};
char xo = 'X';
int num1;
string welc;
string fplay;
string splay;
void tictactoeboard();
void check(int num1);
void changeplayer();
void checkwinner();
int main()
{
    cout << "---------------------                                                                               ----------------" << endl;
    cout << "                                      WELCOME TO TIC TAC TOE" << endl;
    cout << "---------------------                                                                               ----------------" << endl;
    cout << "                                       TYPE START TO BEGIN" << endl;
    cin >> welc;

    if (welc == "START")
    {
        cout << "PLEASE ENTER THE NAME OF THE FIRST PLAYER:"; \
            cin >> fplay;
        cout << "PLEASE ENTER THE NAME OF THE SECOND PLAYER:"; \
            cin >> splay;
        tictactoeboard();
    }
    else
    {
        cout << "TRY AGAIN!";
    }

    while (1)
    {
        cout << fplay << ":";
        cin >> num1;
        check(num1);
        tictactoeboard();
        changeplayer();
        checkwinner();
        cout << splay << ":";
        cin >> num1;
        check(num1);
        tictactoeboard();
        changeplayer();
        checkwinner();
    }



}
void tictactoeboard()
{
    /* for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            cout << "                                          | " << num[i][j] << " |  " << num[i][j] << " | " << num[i][j] << " |" << endl;
        }
    }*/
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

 void checkwinner()
    {
     static int i = 0;
     string count;
     fstream file4;
     file4.open("count.txt", ios::out);
     if (num[0][0] == 'X' && num[1][1] == 'X' && num[2][2] == 'X')
     {
         i = i + 1;
         file4 << i;
         cout << fplay << " has won!";

     }
     file4.close();
     file4.open("count.txt", ios::in);
     file4 >> count;
     if (count == "2")
     {
         cout << fplay << " has won!";
         exit(1);
     }
     
    }
/*cout << endl;
cout << "                                          | " << num[0][0] << " |  " << num[0][1] << " | " << num[0][2] << " |" << endl;
cout << "                                          | " << num[1][0] << " |  " << num[1][1] << " | " << num[1][2] << " |" << endl;
cout << "                                          | " << num[2][0] << " |  " << num[2][1] << " | " << num[2][2] << " |" << endl;*/
