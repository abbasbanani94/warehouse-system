using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_waybill_details : Form
    {
        public frm_waybill_details()
        {
            InitializeComponent();
        }

        private void txtWbNo_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.numbersOnly(e);
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void frm_waybill_details_Load(object sender, EventArgs e)
        {
            HealthCenter.findCitiesCombo(cmbCity);
            load();
        }

        private void load()
        {
            Waybill.findAllWaybillCombo(cmbWbNo);
            DistributionPlan.findAllDpCombo(cmbDpName);
        }

        private void cmbDpName_TextChanged(object sender, EventArgs e)
        {
            txtDpId.ResetText();
            if(cmbDpName.Text != "" && cmbDpName.SelectedValue != null)
            {
                txtDpId.Text = cmbDpName.SelectedValue.ToString();
            }
        }

        private void txtDpId_TextChanged(object sender, EventArgs e)
        {
            txtDpName.ResetText();
            txtDpDate.ResetText();
            if(txtDpId.Text != "")
            {
                DistributionPlan.findDpDetails(txtDpId.Text, txtDpName, txtDpDate);
            }
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

        private void cmbCenter_TextChanged(object sender, EventArgs e)
        {
            if(txtDpId.Text != "" && cmbCenter.SelectedValue != null)
            {

            }
        }
    }
}
