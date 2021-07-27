using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_password : Form
    {
        public frm_password()
        {
            InitializeComponent();
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void btnNew_Click(object sender, EventArgs e)
        {
            done();
        }

        private void done()
        {
            FormsFunctions.clearAll(groupBox1);
            txtUsername.Focus();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (txtUsername.Text == "" || txtOld.Text == "" || txtPass.Text == "" || txtConfirm.Text == "")
                Msg.emptyFields();
            else
            {
                PasswordDto dto = new PasswordDto(txtUsername.Text, txtOld.Text, txtPass.Text, txtConfirm.Text);
                if(User.changePassword(dto))
                {
                    Msg.doneMsg("Password changed successfully !", "Success");
                    done();
                }
            }
        }
    }
}
