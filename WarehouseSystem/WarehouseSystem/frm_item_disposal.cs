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
    }
}