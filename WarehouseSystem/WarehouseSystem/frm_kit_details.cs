using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_kit_details : Form
    {
        public frm_kit_details(string kitPoId)
        {
            InitializeComponent();
            txtKitPoId.Text = kitPoId;
        }

        private void txtMin_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.numbersOnly(e);
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void frm_kit_details_Load(object sender, EventArgs e)
        {
            KitPo.findKitNameByKitPoId(txtKitPoId.Text, txtKitName);
            load();
        }

        private void load()
        {
            Item.findAllItemsCombo(cmbItem);
        }

        private void cmbItem_TextChanged(object sender, EventArgs e)
        {
            resetBoxes();
            if (cmbItem.Text != "" && cmbItem.SelectedValue != null)
                Item.findItemBoxesById(cmbItem.SelectedValue.ToString(), txtMin, txtMax, txtDesc);
        }

        private void resetBoxes()
        {
            txtMin.ResetText();
            txtMax.ResetText();
            txtDesc.ResetText();
        }
    }
}
