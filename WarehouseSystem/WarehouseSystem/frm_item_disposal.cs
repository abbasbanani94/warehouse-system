using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_item_disposal : Form
    {
        public frm_item_disposal(string disposalId)
        {
            InitializeComponent();
            txtDisposalId.Text = disposalId;
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void cmbPoNo_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.numbersOnly(e);
        }

        private void frm_item_disposal_Load(object sender, EventArgs e)
        {
            Disposal.findDisposalDetails(txtDisposalId.Text, txtReason, txtDate);
            PurchaseOrder.findAllPoComboItems(cmbPoNo);
            load();
        }

        private void cmbPoNo_TextChanged(object sender, EventArgs e)
        {
            txtPoId.ResetText();
            cmbItem.SelectedValue = -1;
            cmbItem.DataSource = null;
            if (cmbPoNo.Text != "" && cmbPoNo.SelectedValue != null)
            {
                ItemPo.findItemsComboByPo(cmbItem, cmbPoNo.SelectedValue.ToString());
                txtPoId.Text = cmbPoNo.SelectedValue.ToString();
            }
        }

        private void cmbItem_TextChanged(object sender, EventArgs e)
        {
            resetDetails();
            txtItemPoId.ResetText();
            if (cmbItem.Text != "" && cmbItem.SelectedValue != null)
            {
                txtItemPoId.Text = cmbItem.SelectedValue.ToString();
                ItemPo.findItemPoDpDetailsById(txtItemPoId.Text, txtReceived, txtBatch, txtDesc, txtMan,
                    txtExp, txtPackaging, txtTotalQty, txtInventory);
            }
        }

        private void resetDetails()
        {
            txtReceived.ResetText();
            txtBatch.ResetText();
            txtDesc.ResetText();
            txtMan.ResetText();
            txtExp.ResetText();
            txtPackaging.ResetText();
            txtTotalQty.ResetText();
            txtInventory.ResetText();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (txtItemPoId.Text == "" || txtQty.Text == "")
                Msg.emptyFields();
            else
            {
                if (txtItemDisposalId.Text != "")
                    Msg.idInSave("Item Disposal");
                else
                {
                    ItemDisposalSaveDto dto = new ItemDisposalSaveDto(txtItemPoId.Text, txtQty.Text);

                    if (ItemDisposal.saveItemDisposal(txtDisposalId.Text, dto))
                    {
                        Msg.saved("Item Disposal");
                        done();
                    }
                }
            }
        }

        private void done()
        {
            FormsFunctions.clearAll(groupBox2);
            load();
        }

        private void load()
        {
            cmbPoNo.Focus();
            ItemDisposal.findAllItemDisposalsDgv(txtDisposalId.Text, dgv);
        }

        private void btnNew_Click(object sender, EventArgs e)
        {
            done();
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (txtItemPoId.Text == "" || txtQty.Text == "" || txtItemDisposalId.Text == "")
                Msg.emptyFields();
            else
            {
                if (Msg.editConfirm("Item Disposal") == DialogResult.Yes)
                {
                    ItemDisposalSaveDto dto = new ItemDisposalSaveDto(txtItemPoId.Text, txtQty.Text);

                    if (ItemDisposal.editItemDisposal(txtDisposalId.Text,txtItemDisposalId.Text, dto))
                    {
                        Msg.edited("Item Disposal");
                        done();
                    }
                }
            }
        }

        private void dgv_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if(e.RowIndex != -1)
            {
                txtItemDisposalId.Text = dgv.Rows[e.RowIndex].Cells["Item Disposal ID"].Value.ToString();
                cmbPoNo.Text = dgv.Rows[e.RowIndex].Cells["PO NO"].Value.ToString();
                txtPoId.Text = dgv.Rows[e.RowIndex].Cells["PO ID"].Value.ToString();
                cmbItem.Text = dgv.Rows[e.RowIndex].Cells["Item"].Value.ToString();
                txtItemPoId.Text = dgv.Rows[e.RowIndex].Cells["Item PO ID"].Value.ToString();
                txtQty.Text = dgv.Rows[e.RowIndex].Cells["Qty"].Value.ToString();
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (txtItemPoId.Text == "" || txtQty.Text == "" || txtItemDisposalId.Text == "")
                Msg.emptyFields();
            else
            {
                if (Msg.deleteConfirm("Item Disposal") == DialogResult.Yes)
                {
                    if (ItemDisposal.deleteItemDisposal(txtItemDisposalId.Text))
                    {
                        Msg.deleted("Item Disposal");
                        done();
                    }
                }
            }
        }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            if (txtPoId.Text == "" && txtItemPoId.Text == "" && txtQty.Text == "")
                Msg.emptyFields();
            else
            {
                ItemDisposal.searchItemDisposal(txtDisposalId.Text, txtPoId.Text, txtItemPoId.Text,
                    txtQty.Text, dgv);
                if (dgv.Rows.Count == 0)
                    ItemDisposal.findAllItemDisposalsDgv(txtDisposalId.Text, dgv);
            }
        }
    }
}