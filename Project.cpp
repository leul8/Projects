#include <iostream>
using namespace std;

int num[3][3] = { {1,2,3},
                  {4,5,6},
                  {7,8,9} };
char xo = 'x';
string welc;
string fplay;
string splay;

void welcome()
{
    cout << "---------------------                                                                               ----------------" << endl;
    cout << "                                      WELCOME TO TIC TAC TOE" << endl;
    cout << "---------------------                                                                               ----------------" << endl;
    cout << "                                       TYPE START TO BEGIN" << endl;
}
void tictactoetable()
{
    
    cout << endl;
    cout << "                                          | " << num[0][0] << " |  " << num[0][1] << " | " << num[0][2] << " |" << endl;
    cout << "                                          | " << num[1][0] << " |  " << num[1][1] << " | " << num[1][2] << " |" << endl;
    cout << "                                          | " << num[2][0] << " |  " << num[2][1] << " | " << num[2][2] << " |" << endl; 
    
}



int main()
{
    welcome();
    
    cin >> welc;
    ;
    if (welc == "START")
    {
        cout << "PLEASE ENTER THE NAME OF THE FIRST PLAYER:"; \
        cin >> fplay;
        cout << "PLEASE ENTER THE NAME OF THE SECOND PLAYER:"; \
        cin >> splay;
        tictactoetable();
    }
    else
    {
        cout << "TRY AGAIN!";
    }

}
