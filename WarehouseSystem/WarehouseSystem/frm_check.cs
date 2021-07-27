using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_check : Form
    {
        public frm_check()
        {
            InitializeComponent();
        }

        private void cmbType_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.lettersOnly(e);
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void frm_check_Load(object sender, EventArgs e)
        {
            CheckType.findAllCheckTypes(cmbType);
            load();
        }

        private void load()
        {
            cmbType.Focus();
            Check.findAllChecksDgv(dgv);
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

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (txtNotes.Text == "" || cmbType.Text == "")
                Msg.emptyFields();
            else
            {
                if (txtId.Text != "")
                    Msg.idInSave("Check");
                else
                {
                    CheckSaveDto dto = new CheckSaveDto(cmbType.SelectedValue.ToString(), txtNotes.Text,
                        dtp.Value.ToShortDateString());
                    if (Check.saveCheck(dto))
                    {
                        Msg.saved("Check");
                        done();
                    }
                }
            }
        }

        private void dgv_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if(e.RowIndex != -1)
            {
                txtId.Text = dgv.Rows[e.RowIndex].Cells["Check ID"].Value.ToString();
                txtNotes.Text = dgv.Rows[e.RowIndex].Cells["Notes"].Value.ToString();
                cmbType.Text = dgv.Rows[e.RowIndex].Cells["Type"].Value.ToString();
                dtp.Value = Convert.ToDateTime(dgv.Rows[e.RowIndex].Cells["Date"].Value).Date;
            }
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (txtNotes.Text == "" || cmbType.Text == "" || txtId.Text == "")
                Msg.emptyFields();
            else
            {
                if(Msg.editConfirm("Check") == DialogResult.Yes)
                {
                    CheckSaveDto dto = new CheckSaveDto(cmbType.SelectedValue.ToString(), txtNotes.Text,
                        dtp.Value.ToShortDateString());
                    if (Check.editCheck(txtId.Text,dto))
                    {
                        Msg.edited("Check");
                        done();
                    }
                }
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (txtId.Text == "")
                Msg.emptyFields();
            else
            {
                if(Msg.deleteConfirm("Check") == DialogResult.Yes)
                {
                    if (Check.deleteCheck(txtId.Text))
                    {
                        Msg.deleted("Check");
                        done();
                    }
                }
            }
        }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            if (txtNotes.Text == "" && cmbType.Text == "" && !chkDate.Checked)
                Msg.emptyFields();
            else
            {
                Check.searchChecks(txtNotes.Text, cmbType.Text, dtp.Value.ToShortDateString(),
                    chkDate.Checked, dgv);

                if (dgv.Rows.Count == 0)
                    Check.findAllChecksDgv(dgv);
            }
        }

        private frm_check_worker _check_worker = null;

        private void btnWorkers_Click(object sender, EventArgs e)
        {
            if (txtId.Text == "")
                Msg.errorMsg("Double click on the row you want to add workers for", "Error");
            else
            {
                if (_check_worker == null)
                {
                    _check_worker = new frm_check_worker(txtId.Text);
                    _check_worker.Show();
                    _check_worker.FormClosed += _check_worker_FormClosed;
                }
                else
                    Msg.formAlreadyOpen("Check Workers");
            }
        }

        private void _check_worker_FormClosed(object sender, FormClosedEventArgs e)
        {
            _check_worker = null;
        }
    }
}
