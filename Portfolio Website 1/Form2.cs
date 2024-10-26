using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;
namespace Vote
{
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        [Obsolete]
        private void button1_Click(object sender, EventArgs e)
        {

            
            try
            {
                using (SqlConnection conn = new SqlConnection("data source=LEULLOL\\JAVACONNECT;initial catalog=C#Login;User Id=sa;Password=leul1234;"))
                    {
                    conn.Open(); 
                    SqlCommand cmd = new SqlCommand("Select * FROM login WHERE USERNAME = @u", conn);
                    cmd.Parameters.AddWithValue("@u", textBox1.Text);
                    SqlDataReader reader = cmd.ExecuteReader();
                    if (reader.HasRows)
                    {
                        MessageBox.Show("User is already registered!");
                    }
                    else
                    {
                        cmd = new SqlCommand("INSERT INTO login (username, upassword, gender, age) VALUES (@user, @pass, @gen, @age)", conn);

                        if (textBox4.Text == textBox5.Text)
                        {
                            cmd.Parameters.AddWithValue("@user", textBox1.Text);
                            cmd.Parameters.AddWithValue("@pass", textBox4.Text);
                            cmd.Parameters.AddWithValue("@gen", textBox2.Text);
                            cmd.Parameters.AddWithValue("@age", textBox3.Text);
                            int r = cmd.ExecuteNonQuery();
                            if (r > 0)
                            {
                                MessageBox.Show("Registered Successfully", "success");
                            }
                        }
                        else
                        {
                            MessageBox.Show("Passwords Don't Match", "Error");
                        }

                        conn.Close();
                    }
                }
                    
                    
            }
            catch(Exception ex)
            {
                MessageBox.Show("Error: " + ex.Message);
            }
            
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void textBox4_TextChanged(object sender, EventArgs e)
        {

        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }

        private void textBox3_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
