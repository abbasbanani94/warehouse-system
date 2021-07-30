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
            DistributionPlan.findAllDpCombo(cmbDpEn);
        }

        private void load()
        {
            KitDp.findKitDpDgv(dgv);
            PurchaseOrder.findAllPoComboKits(cmbPoNo);
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

        private void btnNew_Click(object sender, EventArgs e)
        {
            txtDpAr.ResetText();
            dtpDp.Value = DateTime.Today.Date;
            done();
        }

        private void done()
        {
            load();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (cmbDpEn.Text == "" || txtDpAr.Text == "" || txtKitPoId.Text == "" || cmbCenter.SelectedValue == null ||
                txtQty.Text == "")
                Msg.emptyFields();
            else
            {
                if (txtKitDpId.Text != "")
                    Msg.idInSave("Kit DP");
                else
                {
                    KitDpSaveDto dto = new KitDpSaveDto(txtDpId.Text, cmbDpEn.Text, txtDpAr.Text, dtpDp.Value.ToShortDateString(),
                        txtKitPoId.Text, cmbCenter.SelectedValue.ToString(), txtQty.Text);

                    if (KitDp.saveKitDp(dto))
                    {
                        Msg.saved("Kit DP");
                        done();
                    }
                }
            }
        }

        private void cmbDpEn_TextChanged(object sender, EventArgs e)
        {
            txtDpId.ResetText();
            if (cmbDpEn.Text != "" && cmbDpEn.SelectedValue != null)
                txtDpId.Text = cmbDpEn.SelectedValue.ToString();
        }

        private void txtDpId_TextChanged(object sender, EventArgs e)
        {
            txtDpAr.ResetText();
            dtpDp.Value = DateTime.Today.Date;
            if (txtDpId.Text != "")
                DistributionPlan.findDpDetails(txtDpId.Text, txtDpAr, dtpDp);
        }

        private void dgv_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex != -1)
            {
                dgvClick = true;
                txtKitDpId.Text = dgv.Rows[e.RowIndex].Cells["Kit DP ID"].Value.ToString();
                cmbDpEn.Text = dgv.Rows[e.RowIndex].Cells["Plan"].Value.ToString();
                txtDpId.Text = dgv.Rows[e.RowIndex].Cells["DP ID"].Value.ToString();
                cmbPoNo.Text = dgv.Rows[e.RowIndex].Cells["PO NO"].Value.ToString();
                txtPoId.Text = dgv.Rows[e.RowIndex].Cells["PO ID"].Value.ToString();
                cmbKit.Text = dgv.Rows[e.RowIndex].Cells["Kit"].Value.ToString();
                txtKitPoId.Text = dgv.Rows[e.RowIndex].Cells["Kit PO ID"].Value.ToString();
                txtQty.Text = dgv.Rows[e.RowIndex].Cells["Qty"].Value.ToString();
                dgvClick = false;
            }
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (cmbDpEn.Text == "" || txtDpAr.Text == "" || txtKitPoId.Text == "" || cmbCenter.SelectedValue == null ||
                txtQty.Text == "" || txtKitDpId.Text == "")
                Msg.emptyFields();
            else
            {
                if (Msg.editConfirm("Kit DP") == DialogResult.Yes)
                {
                    KitDpSaveDto dto = new KitDpSaveDto(txtDpId.Text, cmbDpEn.Text, txtDpAr.Text, dtpDp.Value.ToShortDateString(),
                        txtKitPoId.Text, cmbCenter.SelectedValue.ToString(), txtQty.Text);

                    if (KitDp.editKitDp(txtKitDpId.Text, dto))
                    {
                        Msg.edited("Kit DP");
                        done();
                    }
                }
            }
        }

        private void cmbKit_SelectedValueChanged(object sender, EventArgs e)
        {
            if (cmbKit.SelectedValue == null)
                txtKitPoId.ResetText();
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (txtKitDpId.Text == "")
                Msg.emptyFields();
            else
            {
                if (Msg.deleteConfirm("Kit DP") == DialogResult.Yes)
                {
                    if (KitDp.deleteKitDp(txtKitDpId.Text))
                    {
                        Msg.deleted("Kit DP");
                        done();
                    }
                }
            }
        }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            string cityId = "", districtId = "", centerId = "";
            if (cmbCity.SelectedValue != null)
                cityId = cmbCity.SelectedValue.ToString();
            if (cmbDistrict.SelectedValue != null)
                districtId = cmbDistrict.SelectedValue.ToString();
            if (cmbCenter.SelectedValue != null)
                centerId = cmbCenter.SelectedValue.ToString();

            KitDp.searchKitDpDgv(dgv, txtDpId.Text, dtpDp.Value.ToShortDateString(), txtPoId.Text, txtKitPoId.Text,
                cityId, districtId, centerId, txtQty.Text, chkDate.Checked);

            if (dgv.Rows.Count == 0)
                KitDp.findKitDpDgv(dgv);
        }
    }
}
