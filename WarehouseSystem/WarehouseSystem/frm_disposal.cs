using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_disposal : Form
    {
        public frm_disposal()
        {
            InitializeComponent();
        }

        private void frm_disposal_Load(object sender, EventArgs e)
        {
            load();
        }

        private void load()
        {
            txtReason.Focus();
            Disposal.findAllDisposalsDgv(dgv);
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (txtReason.Text == "")
                Msg.emptyFields();
            else
            {
                if (txtId.Text != "")
                    Msg.errorMsg("click Edit to edit this disposal", "Error");
                else
                {
                    DisposalSaveDto dto = new DisposalSaveDto(txtReason.Text, dtp.Value.ToShortDateString());
                    if (Disposal.saveDisposal(dto))
                    {
                        Msg.doneMsg("Disposal saved successfully !", "Successfully");
                        done();
                    }
                }
            }
        }

        private void done()
        {
            FormsFunctions.clearAll(groupBox1);
            load();
        }

        private void btnNew_Click(object sender, EventArgs e)
        {
            done();
        }

        private void dgv_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if(e.RowIndex != -1)
            {
                txtId.Text = dgv.Rows[e.RowIndex].Cells["Disposal ID"].Value.ToString();
                txtReason.Text = dgv.Rows[e.RowIndex].Cells["Reason"].Value.ToString();
                dtp.Value = Convert.ToDateTime(dgv.Rows[e.RowIndex].Cells["Date"].Value).Date;
            }
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (txtId.Text == "" || txtReason.Text == "")
                Msg.emptyFields();
            else
            {
                if(Msg.questionMsg("Do you want to edit this disposal ?","Edit Confirmation") == DialogResult.Yes)
                {
                    DisposalSaveDto dto = new DisposalSaveDto(txtReason.Text, dtp.Value.ToShortDateString());
                    if(Disposal.editDisposal(txtId.Text,dto))
                    {
                        Msg.doneMsg("Disposal edited successfully !", "Successfully");
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
                if (Msg.questionMsg("Do you want to deleted this disposal ?", "Delete Confirmation") == DialogResult.Yes)
                {
                    if (Disposal.deleteDisposal(txtId.Text))
                    {
                        Msg.doneMsg("Disposal deleted successfully !", "Successfully");
                        done();
                    }
                }
            }
        }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            if (txtReason.Text == "" && !chkDate.Checked)
                Msg.emptyFields();
            else
            {
                Disposal.searchDisposals(txtReason.Text, dtp.Value.ToShortDateString(), chkDate.Checked,
                    dgv);
                if (dgv.Rows.Count == 0)
                    Disposal.findAllDisposalsDgv(dgv);
            }
        }
    }
}
