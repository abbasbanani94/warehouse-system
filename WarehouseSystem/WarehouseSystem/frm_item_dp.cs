using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
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
        }

        private void load()
        {
            ItemDp.findItemDpDgv(dgv);
            PurchaseOrder.findAllPoCombo(cmbPoNo);
            DistributionPlan.findAllDpCombo(cmbDp);
        }

        private void cmbCity_TextChanged(object sender, EventArgs e)
        {
            cmbDistrict.DataSource = null;
            if (cmbCity.Text != "" && cmbCity.SelectedValue != null)
            {
                HealthCenter.findDistrictsCombo(cmbDistrict, cmbCity.SelectedValue.ToString());
            }
        }

        private void cmbDistrict_TextChanged(object sender, EventArgs e)
        {
            cmbCenter.DataSource = null;
            if(cmbDistrict.Text != "" && cmbDistrict.SelectedValue != null)
            {
                HealthCenter.findCentersCombo(cmbCenter, cmbDistrict.SelectedValue.ToString());
            }
        }

        private void cmbPoNo_TextChanged(object sender, EventArgs e)
        {
            txtPoId.ResetText();
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
            done();
        }

        private void done()
        {
            FormsFunctions.clearAll(groupBox2);
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
                    Msg.errorMsg("Click on Edit button to edit the record", "Error");
                else
                {
                    ItemDpSaveDto dto = new ItemDpSaveDto(txtDpId.Text, cmbDp.Text, txtDp.Text, dtpDp.Value.ToShortDateString(),
                        txtItemPoId.Text, cmbCenter.SelectedValue.ToString(), txtQty.Text);

                    if(ItemDp.saveItemDp(dto))
                    {
                        Msg.doneMsg("Item DP saved successfully !", "Saved Successfully");
                        done();
                    }
                }
            }
        }

        private void dgv_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if(e.RowIndex != -1)
            {
                txtItemDpId.Text = dgv.Rows[e.RowIndex].Cells["Item DP ID"].Value.ToString();
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
    }
}