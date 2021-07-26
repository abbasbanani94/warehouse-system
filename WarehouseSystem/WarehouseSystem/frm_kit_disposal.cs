using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_kit_disposal : Form
    {
        public frm_kit_disposal(string disposalId)
        {
            InitializeComponent();
            txtDisposalId.Text = disposalId;
        }

        private void cmbPoNo_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.numbersOnly(e);
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void frm_kit_disposal_Load(object sender, EventArgs e)
        {
            Disposal.findDisposalDetails(txtDisposalId.Text, txtReason, txtDate);
            PurchaseOrder.findAllPoComboKits(cmbPoNo);
            load();
        }

        private void load()
        {
            cmbPoNo.Focus();
            KitDisposal.findAllKitDisposalsDgv(txtDisposalId.Text, dgv);
        }

        private void cmbPoNo_TextChanged(object sender, EventArgs e)
        {
            txtPoId.ResetText();
            cmbKit.SelectedValue = -1;
            cmbKit.DataSource = null;
            if (cmbPoNo.Text != "" && cmbPoNo.SelectedValue != null)
            {
                KitPo.findKitsComboByPo(cmbKit, cmbPoNo.SelectedValue.ToString());
                txtPoId.Text = cmbPoNo.SelectedValue.ToString();
            }
        }

        private void cmbKit_TextChanged(object sender, EventArgs e)
        {
            resetDetails();
            txtKitPoId.ResetText();
            if (cmbKit.Text != "" && cmbKit.SelectedValue != null)
            {
                txtKitPoId.Text = cmbKit.SelectedValue.ToString();
                KitPo.findKitPoDpDetailsById(txtKitPoId.Text, txtReceived, txtBatch, txtDesc, txtMan,
                    txtExp, txtType, txtTotalQty, txtInventory);
            }
        }

        private void resetDetails()
        {
            txtReceived.ResetText();
            txtBatch.ResetText();
            txtDesc.ResetText();
            txtMan.ResetText();
            txtExp.ResetText();
            txtType.ResetText();
            txtTotalQty.ResetText();
            txtInventory.ResetText();
        }

        private void btnNew_Click(object sender, EventArgs e)
        {
            done();
        }

        private void done()
        {
            FormsFunctions.clearAll(groupBox2);
            load();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (txtKitPoId.Text == "" || txtQty.Text == "")
                Msg.emptyFields();
            else
            {
                if (txtKitDisposalId.Text != "")
                    Msg.idInSave("Kit Disposal");
                else
                {
                    KitDisposalSaveDto dto = new KitDisposalSaveDto(txtKitPoId.Text, txtQty.Text);

                    if (KitDisposal.saveKitDisposal(txtDisposalId.Text, dto))
                    {
                        Msg.saved("Kit Disposal");
                        done();
                    }
                }
            }
        }

        private void dgv_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex != -1)
            {
                txtKitDisposalId.Text = dgv.Rows[e.RowIndex].Cells["Kit Disposal ID"].Value.ToString();
                cmbPoNo.Text = dgv.Rows[e.RowIndex].Cells["PO NO"].Value.ToString();
                txtPoId.Text = dgv.Rows[e.RowIndex].Cells["PO ID"].Value.ToString();
                cmbKit.Text = dgv.Rows[e.RowIndex].Cells["Kit"].Value.ToString();
                txtKitPoId.Text = dgv.Rows[e.RowIndex].Cells["Kit PO ID"].Value.ToString();
                txtQty.Text = dgv.Rows[e.RowIndex].Cells["Qty"].Value.ToString();
            }
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (txtKitPoId.Text == "" || txtQty.Text == "" || txtKitDisposalId.Text == "")
                Msg.emptyFields();
            else
            {
                if (Msg.editConfirm("Kit Disposal") == DialogResult.Yes)
                {
                    KitDisposalSaveDto dto = new KitDisposalSaveDto(txtKitPoId.Text, txtQty.Text);

                    if (KitDisposal.editKitDisposal(txtDisposalId.Text, txtKitDisposalId.Text, dto))
                    {
                        Msg.edited("Kit Disposal");
                        done();
                    }
                }
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (txtKitPoId.Text == "" || txtQty.Text == "" || txtKitDisposalId.Text == "")
                Msg.emptyFields();
            else
            {
                if (Msg.deleteConfirm("Kit Disposal") == DialogResult.Yes)
                {
                    if (KitDisposal.deleteItemDisposal(txtKitDisposalId.Text))
                    {
                        Msg.deleted("Kit Disposal");
                        done();
                    }
                }
            }
        }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            if (txtPoId.Text == "" && txtKitPoId.Text == "" && txtQty.Text == "")
                Msg.emptyFields();
            else
            {
                KitDisposal.searchKitDisposal(txtDisposalId.Text, txtPoId.Text, txtKitPoId.Text,
                    txtQty.Text, dgv);
                if (dgv.Rows.Count == 0)
                    KitDisposal.findAllKitDisposalsDgv(txtDisposalId.Text, dgv);
            }
        }
    }
}
