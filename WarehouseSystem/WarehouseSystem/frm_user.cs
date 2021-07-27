using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_user : Form
    {
        public frm_user()
        {
            InitializeComponent();
        }

        private void cmbRole_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.lettersOnly(e);
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void frm_user_Load(object sender, EventArgs e)
        {
            Role.findAllRolesCombo(cmbRole);
            load();
        }

        private void load()
        {
            User.findAllUsers(dgv);
        }

        private void btnNew_Click(object sender, EventArgs e)
        {
            done();
        }

        private void done()
        {
            FormsFunctions.clearAll(groupBox1);
            load();
        }

        private void btnSave_Click_1(object sender, EventArgs e)
        {
            if (cmbRole.Text == "" || txtName.Text == "" || txtUsername.Text == "" || txtPass.Text == "" ||
                txtcPass.Text == "")
                Msg.emptyFields();
            else
            {
                if (txtId.Text != "")
                    Msg.idInSave("User");
                else
                {
                    UserSaveDto dto = new UserSaveDto(cmbRole.SelectedValue.ToString(), txtName.Text,
                    txtUsername.Text, txtPass.Text, txtcPass.Text);
                    if (User.saveUser(dto))
                    {
                        Msg.saved("User");
                        done();
                    }
                }
            }
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (cmbRole.Text == "" || txtName.Text == "" || txtUsername.Text == "" || txtPass.Text == "" ||
                txtcPass.Text == "" || txtId.Text == "")
                Msg.emptyFields();
            else
            {
                if(Msg.editConfirm("User") == DialogResult.Yes)
                {
                    UserSaveDto dto = new UserSaveDto(cmbRole.SelectedValue.ToString(), txtName.Text,
                    txtUsername.Text, txtPass.Text, txtcPass.Text);
                    if (User.editUser(txtId.Text,dto))
                    {
                        Msg.edited("User");
                        done();
                    }
                }
            }
        }

        private void dgv_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex != -1)
            {
                txtId.Text = dgv.Rows[e.RowIndex].Cells["User ID"].Value.ToString();
                cmbRole.Text = dgv.Rows[e.RowIndex].Cells["Role"].Value.ToString();
                txtName.Text = dgv.Rows[e.RowIndex].Cells["Name"].Value.ToString();
            }
        }

        private void btnActivate_Click(object sender, EventArgs e)
        {
            if (txtId.Text == "")
                Msg.emptyFields();
            else
            {
                if(Msg.questionMsg("Do you want to activate this user ?","Confirmation") == DialogResult.Yes)
                {
                    if(User.activateUser(txtId.Text))
                    {
                        Msg.doneMsg("User activated successfully !", "Activated");
                        done();
                    }
                }
            }
        }

        private void btnDeactivate_Click(object sender, EventArgs e)
        {
            if (txtId.Text == "")
                Msg.emptyFields();
            else
            {
                if (Msg.questionMsg("Do you want to deactivate this user ?", "Confirmation") == DialogResult.Yes)
                {
                    if (User.deactivateUser(txtId.Text))
                    {
                        Msg.doneMsg("User deactivated successfully !", "Deactivated");
                        done();
                    }
                }
            }
        }
    }
}
