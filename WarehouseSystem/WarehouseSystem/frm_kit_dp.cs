using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_kit_dp : Form
    {
        public frm_kit_dp()
        {
            InitializeComponent();
        }

        private void cmbPoNo_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.numbersOnly(e);
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void frm_kit_dp_Load(object sender, EventArgs e)
        {
            load();
            HealthCenter.findCitiesCombo(cmbCity);
        }

        private void load()
        {
            KitDp.findKitDpDgv(dgv);
            PurchaseOrder.findAllPoCombo(cmbPoNo);
            DistributionPlan.findAllDpCombo(cmbDpEn);
        }

        private void cmbCity_TextChanged(object sender, EventArgs e)
        {
            cmbDistrict.SelectedValue = -1;
            cmbDistrict.DataSource = null;
            if (cmbCity.Text != "" && cmbCity.SelectedValue != null)
            {
                HealthCenter.findDistrictsCombo(cmbDistrict, cmbCity.SelectedValue.ToString());
            }
        }

        private void cmbDistrict_TextChanged(object sender, EventArgs e)
        {
            cmbCenter.SelectedValue = -1;
            cmbCenter.DataSource = null;
            if (cmbDistrict.Text != "" && cmbDistrict.SelectedValue != null)
            {
                HealthCenter.findCentersCombo(cmbCenter, cmbDistrict.SelectedValue.ToString());
            }
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

        bool dgvClick = false;

        private void cmbKit_TextChanged(object sender, EventArgs e)
        {
            resetDetails();
            txtKitPoId.ResetText();
            if (cmbKit.Text != "" && cmbKit.SelectedValue != null)
            {
                if (!dgvClick)
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
    }
}
