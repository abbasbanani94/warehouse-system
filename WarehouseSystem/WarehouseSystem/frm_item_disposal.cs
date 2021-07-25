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
            PurchaseOrder.findAllPoCombo(cmbPoNo);
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
                    Msg.errorMsg("click on Edit butto to edit this record", "Error");
                else
                {
                    ItemDisposalSaveDto dto = new ItemDisposalSaveDto(txtDisposalId.Text,
                        txtItemPoId.Text, txtQty.Text);

                    if(ItemDisposal.saveItemDisposal(txtDisposalId.Text, dto))
                    {
                        Msg.doneMsg("Item Disposal saved successfully !", "Successfully");
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
    }
}