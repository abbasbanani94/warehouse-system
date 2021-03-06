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
                    Msg.idInSave("Disposal");
                else
                {
                    DisposalSaveDto dto = new DisposalSaveDto(txtReason.Text, dtp.Value.ToShortDateString());
                    if (Disposal.saveDisposal(dto))
                    {
                        Msg.saved("Disposal");
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
                if(Msg.editConfirm("Disposal") == DialogResult.Yes)
                {
                    DisposalSaveDto dto = new DisposalSaveDto(txtReason.Text, dtp.Value.ToShortDateString());
                    if(Disposal.editDisposal(txtId.Text,dto))
                    {
                        Msg.edited("Disposal");
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
                if (Msg.deleteConfirm("Disposal") == DialogResult.Yes)
                {
                    if (Disposal.deleteDisposal(txtId.Text))
                    {
                        Msg.deleted("Disposal");
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

        private frm_item_disposal _item_disposal = null;

        private void btnItemDisposals_Click(object sender, EventArgs e)
        {
            if (txtId.Text == "")
                Msg.errorMsg("click on the row you want to enter item disposal for", "Error");
            else
            {
                if (_item_disposal == null)
                {
                    _item_disposal = new frm_item_disposal(txtId.Text);
                    _item_disposal.Show();
                    _item_disposal.FormClosed += _item_disposal_FormClosed;
                }
                else
                    Msg.formAlreadyOpen("Item Disposal");
            }
        }

        private void _item_disposal_FormClosed(object sender, FormClosedEventArgs e)
        {
            _item_disposal = null;
        }

        private frm_kit_disposal _kit_disposal = null;

        private void btnKitDisposals_Click(object sender, EventArgs e)
        {
            if (txtId.Text == "")
                Msg.errorMsg("click on the row you want to enter kit disposal for", "Error");
            else
            {
                if (_kit_disposal == null)
                {
                    _kit_disposal = new frm_kit_disposal(txtId.Text);
                    _kit_disposal.Show();
                    _kit_disposal.FormClosed += _kit_disposal_FormClosed;
                }
                else
                    Msg.formAlreadyOpen("Kit Disposals");
            }
        }

        private void _kit_disposal_FormClosed(object sender, FormClosedEventArgs e)
        {
            _kit_disposal = null;
        }
    }
}