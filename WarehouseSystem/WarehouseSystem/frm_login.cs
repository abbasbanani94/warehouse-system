using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_login : Form
    {
        public frm_login()
        {
            InitializeComponent();
        }

        private void btnLogin_Click(object sender, EventArgs e)
        {
            if (txtUsername.Text == "" || txtPass.Text == "")
                Msg.emptyFields(); 
            else
            {
                if(User.login(new LoginDto(txtUsername.Text, txtPass.Text)))
                {
                    frm_parent f = new frm_parent();
                    f.Show();
                    Hide();
                }
            }
        }

        private frm_connection _connection = null;

        private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            if (_connection == null)
            {
                _connection = new frm_connection();
                _connection.Show();
                _connection.FormClosed += _connection_FormClosed;
            }
            else
                Msg.formAlreadyOpen("Server Configuration");
        }

        private void _connection_FormClosed(object sender, FormClosedEventArgs e)
        {
            _connection = null;
        }
    }
}
