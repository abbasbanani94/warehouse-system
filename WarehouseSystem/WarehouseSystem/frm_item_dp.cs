using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_item_dp : Form
    {
        public frm_item_dp()
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

        private void frm_item_dp_Load(object sender, EventArgs e)
        {
            load();
            HealthCenter.findCitiesCombo(cmbCity);
            DistributionPlan.findAllDpCombo(cmbDp);
        }

        private void load()
        {
            ItemDp.findItemDpDgv(dgv);
            PurchaseOrder.findAllPoComboItems(cmbPoNo);
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
            if(cmbDistrict.Text != "" && cmbDistrict.SelectedValue != null)
            {
                HealthCenter.findCentersCombo(cmbCenter, cmbDistrict.SelectedValue.ToString());
            }
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
            if(cmbItem.Text != "" && cmbItem.SelectedValue != null)
            {
                if(!dgvClick)
                    txtItemPoId.Text = cmbItem.SelectedValue.ToString();
                ItemPo.findItemPoDpDetailsById(txtItemPoId.Text, txtReceived, txtBatch, txtDesc, txtMan,
                    txtExp,txtPackaging,txtTotalQty,txtInventory);
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

        private void btnNew_Click(object sender, EventArgs e)
        {
            txtDp.ResetText();
            dtpDp.Value = DateTime.Today.Date;
            done();
        }

        private void done()
        {
            load();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (cmbDp.Text == "" || txtDp.Text == "" || txtItemPoId.Text == "" || cmbCenter.SelectedValue == null ||
                txtQty.Text == "")
                Msg.emptyFields();
            else
            {
                if (txtItemDpId.Text != "")
                    Msg.idInSave("Item DP");
                else
                {
                    ItemDpSaveDto dto = new ItemDpSaveDto(txtDpId.Text, cmbDp.Text, txtDp.Text, dtpDp.Value.ToShortDateString(),
                        txtItemPoId.Text, cmbCenter.SelectedValue.ToString(), txtQty.Text);

                    if (ItemDp.saveItemDp(dto))
                    {
                        Msg.saved("Item DP");
                        done();
                    }
                }
            }
        }

        bool dgvClick = false;

        private void dgv_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if(e.RowIndex != -1)
            {
                dgvClick = true;
                txtItemDpId.Text = dgv.Rows[e.RowIndex].Cells["Item DP ID"].Value.ToString();
                cmbDp.Text = dgv.Rows[e.RowIndex].Cells["Plan"].Value.ToString();
                txtDpId.Text = dgv.Rows[e.RowIndex].Cells["DP ID"].Value.ToString();
                cmbPoNo.Text = dgv.Rows[e.RowIndex].Cells["PO NO"].Value.ToString();
                txtPoId.Text = dgv.Rows[e.RowIndex].Cells["PO ID"].Value.ToString();
                cmbItem.Text = dgv.Rows[e.RowIndex].Cells["Item"].Value.ToString();
                txtItemPoId.Text = dgv.Rows[e.RowIndex].Cells["Item PO ID"].Value.ToString();
                txtQty.Text = dgv.Rows[e.RowIndex].Cells["Qty"].Value.ToString();
                dgvClick = false;
            }
        }

        private void cmbDp_TextChanged(object sender, EventArgs e)
        {
            txtDpId.ResetText();
            if (cmbDp.Text != "" && cmbDp.SelectedValue != null)
                txtDpId.Text = cmbDp.SelectedValue.ToString();
        }

        private void txtDpId_TextChanged(object sender, EventArgs e)
        {
            txtDp.ResetText();
            dtpDp.Value = DateTime.Today.Date;
            if (txtDpId.Text != "")
                DistributionPlan.findDpDetails(txtDpId.Text, txtDp, dtpDp);
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (cmbDp.Text == "" || txtDp.Text == "" || txtItemPoId.Text == "" || cmbCenter.SelectedValue == null ||
                txtQty.Text == "" || txtItemDpId.Text == "")
                Msg.emptyFields();
            else
            {
                if(Msg.editConfirm("Item DP") == DialogResult.Yes)
                {
                    ItemDpSaveDto dto = new ItemDpSaveDto(txtDpId.Text, cmbDp.Text, txtDp.Text, dtpDp.Value.ToShortDateString(),
                        txtItemPoId.Text, cmbCenter.SelectedValue.ToString(), txtQty.Text);

                    if (ItemDp.editItemDp(txtItemDpId.Text,dto))
                    {
                        Msg.edited("Item DP");
                        done();
                    }
                }
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (txtItemDpId.Text == "")
                Msg.emptyFields();
            else
            {
                if (Msg.deleteConfirm("Item DP") == DialogResult.Yes)
                {
                    if (ItemDp.deleteItemDp(txtItemDpId.Text))
                    {
                        Msg.deleted("Item DP");
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

            ItemDp.searchItemDpDgv(dgv, txtDpId.Text, dtpDp.Value.ToShortDateString(), txtPoId.Text, txtItemPoId.Text,
                cityId, districtId, centerId, txtQty.Text, chkDate.Checked);

            if (dgv.Rows.Count == 0)
                ItemDp.findItemDpDgv(dgv);
        }
    }
}