using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_kit_po : Form
    {
        public frm_kit_po()
        {
            InitializeComponent();
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void cmbPoNo_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.numbersOnly(e);
        }

        private void cmbCountry_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.lettersOnly(e);
        }

        private void frm_kit_po_Load(object sender, EventArgs e)
        {
            load();
        }

        private void load()
        {
            PurchaseOrder.findAllPoCombo(cmbPoNo);
            PurchaseOrder.findAllCountries(cmbCountry);
            Kit.findAllKitsCombo(cmbKit);
            KitPo.findKitPoDgv(dgv);
        }

        private void cmbPoNo_TextChanged(object sender, EventArgs e)
        {
            txtPoId.ResetText();
            if (cmbPoNo.Text != "")
            {
                if (cmbPoNo.SelectedValue != null)
                    txtPoId.Text = cmbPoNo.SelectedValue.ToString();
            }
        }

        private void cmbKit_TextChanged(object sender, EventArgs e)
        {
            txtKitId.ResetText();
            if (cmbKit.Text != "")
            {
                if (cmbKit.SelectedValue != null)
                    txtKitId.Text = cmbKit.SelectedValue.ToString();
            }
        }

        private void txtKitId_TextChanged(object sender, EventArgs e)
        {
            resetKitBoxes();
            if (txtKitId.Text != "")
            {
                Kit.findKitBoxesById(txtKitId.Text, txtMin, txtMax, txtDesc);
            }
        }

        private void resetKitBoxes()
        {
            txtMax.ResetText();
            txtMin.ResetText();
            txtDesc.ResetText();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (cmbPoNo.Text == "" || cmbKit.Text == "" || txtMin.Text == "" || txtMax.Text == "" ||
                cmbCountry.Text == "" || txtLocation.Text == "" || txtTotalQty.Text == "" || 
                cmbType.Text == "")
                Msg.emptyFields();
            else
            {
                if (txtKitPoId.Text != "")
                    Msg.errorMsg("Click edit to edit previously entered data.", "Error");
                else
                {
                    KitPoSaveDto dto = new KitPoSaveDto(txtPoId.Text, cmbPoNo.Text, dtpReceived.Value.ToShortDateString(),
                        cmbKit.Text, txtMin.Text, txtMax.Text, txtDesc.Text, dtpMan.Value.ToShortDateString(),
                        dtpExp.Value.ToShortDateString(), cmbCountry.Text, txtBatch.Text, txtPalletsQty.Text,
                        txtBoxesPallet.Text, txtTotalQty.Text, txtKitPoId.Text, txtLocation.Text, txtKitsPallet.Text,
                        cmbType.Text);
                    if(KitPo.saveKitPo(dto))
                    {
                        Msg.doneMsg("Kit PO saved successfully !", "Done");
                        done();
                    }
                }
            }
        }

        private void done()
        {
            FormsFunctions.clearAll(groupBox1);
            txtMin.ResetText();
            txtMax.ResetText();
            load();
        }

        private void txtPalletsQty_TextChanged(object sender, EventArgs e)
        {
            int pallets = 1, boxes = 1, kitsPerPallet = 1;

            if (txtPalletsQty.Text != "")
                pallets = Convert.ToInt32(txtPalletsQty.Text);
            if (txtBoxesPallet.Text != "")
                boxes = Convert.ToInt32(txtBoxesPallet.Text);
            if (txtKitsPallet.Text != "")
                kitsPerPallet = Convert.ToInt32(txtKitsPallet.Text);

            txtTotalQty.Text = Convert.ToString(pallets * kitsPerPallet);
        }

        private void btnNew_Click(object sender, EventArgs e)
        {
            done();
        }

        private void dgv_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex != -1)
            {
                txtKitPoId.Text = dgv.Rows[e.RowIndex].Cells["Kit PO. ID"].Value.ToString();
                cmbPoNo.Text = dgv.Rows[e.RowIndex].Cells["PO. NO."].Value.ToString();
                cmbKit.Text = dgv.Rows[e.RowIndex].Cells["Kit Name"].Value.ToString();
                dtpReceived.Value = Convert.ToDateTime(dgv.Rows[e.RowIndex].Cells["Date Received"].Value).Date;
                dtpMan.Value = Convert.ToDateTime(dgv.Rows[e.RowIndex].Cells["Man. Date"].Value).Date;
                dtpExp.Value = Convert.ToDateTime(dgv.Rows[e.RowIndex].Cells["Exp. Date"].Value).Date;
                txtPalletsQty.Text = dgv.Rows[e.RowIndex].Cells["Pallets"].Value.ToString();
                txtBoxesPallet.Text = dgv.Rows[e.RowIndex].Cells["Boxes/Pallet"].Value.ToString();
                txtTotalQty.Text = dgv.Rows[e.RowIndex].Cells["Total Qty"].Value.ToString();
                txtMin.Text = dgv.Rows[e.RowIndex].Cells["Min. Temp."].Value.ToString();
                txtMax.Text = dgv.Rows[e.RowIndex].Cells["Max. Temp."].Value.ToString();
                cmbCountry.Text = dgv.Rows[e.RowIndex].Cells["Man. Country"].Value.ToString();
                txtBatch.Text = dgv.Rows[e.RowIndex].Cells["Batch NO."].Value.ToString();
                txtDesc.Text = dgv.Rows[e.RowIndex].Cells["Desc."].Value.ToString();
                txtLocation.Text = dgv.Rows[e.RowIndex].Cells["Location"].Value.ToString();
                txtKitsPallet.Text = dgv.Rows[e.RowIndex].Cells["Kits/Pallet"].Value.ToString();
                cmbType.Text = dgv.Rows[e.RowIndex].Cells["Kit Type"].Value.ToString();
            }
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (cmbPoNo.Text == "" || cmbKit.Text == "" || txtMin.Text == "" || txtMax.Text == "" ||
                cmbCountry.Text == "" || txtLocation.Text == "" || txtTotalQty.Text == "" ||
                cmbType.Text == "" || txtKitPoId.Text == "")
                Msg.emptyFields();
            else
            {
                if(Msg.questionMsg("Do you want to edit Kit PO ?" , "Are you sure ?") == DialogResult.Yes)
                {
                    KitPoSaveDto dto = new KitPoSaveDto(txtPoId.Text, cmbPoNo.Text, dtpReceived.Value.ToShortDateString(),
                        cmbKit.Text, txtMin.Text, txtMax.Text, txtDesc.Text, dtpMan.Value.ToShortDateString(),
                        dtpExp.Value.ToShortDateString(), cmbCountry.Text, txtBatch.Text, txtPalletsQty.Text,
                        txtBoxesPallet.Text, txtTotalQty.Text, txtKitPoId.Text, txtLocation.Text, txtKitsPallet.Text,
                        cmbType.Text);
                    if (KitPo.editKitPo(dto))
                    {
                        Msg.doneMsg("Kit PO edited successfully !", "Done");
                        done();
                    }
                }
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (cmbPoNo.Text == "" || cmbKit.Text == "" || txtMin.Text == "" || txtMax.Text == "" ||
                cmbCountry.Text == "" || txtLocation.Text == "" || txtTotalQty.Text == "" ||
                cmbType.Text == "" || txtKitPoId.Text == "")
                Msg.emptyFields();
            else
            {
                if (Msg.questionMsg("Do you want to delete Kit PO ?", "Are you sure ?") == DialogResult.Yes)
                {
                    KitPoSaveDto dto = new KitPoSaveDto(txtPoId.Text, cmbPoNo.Text, dtpReceived.Value.ToShortDateString(),
                        cmbKit.Text, txtMin.Text, txtMax.Text, txtDesc.Text, dtpMan.Value.ToShortDateString(),
                        dtpExp.Value.ToShortDateString(), cmbCountry.Text, txtBatch.Text, txtPalletsQty.Text,
                        txtBoxesPallet.Text, txtTotalQty.Text, txtKitPoId.Text, txtLocation.Text, txtKitsPallet.Text,
                        cmbType.Text);
                    if (KitPo.deleteKitPo(dto))
                    {
                        Msg.doneMsg("Kit PO deleted successfully !", "Done");
                        done();
                    }
                }
            }
        }

        private void btnDisposal_Click(object sender, EventArgs e)
        {
            Msg.errorMsg("No form yet", "NO FORM");
        }

        private void btnDetails_Click(object sender, EventArgs e)
        {
            Msg.errorMsg("No form yet", "NO FORM");
        }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            string dateReceived = "", manDate = "", expDate = "";
            if (chkExp.Checked)
                expDate = dtpExp.Value.ToShortDateString();
            if (chkMan.Checked)
                manDate = dtpMan.Value.ToShortDateString();
            if (chkReceived.Checked)
                dateReceived = dtpReceived.Value.ToShortDateString();

            KitPoSearchDto dto = new KitPoSearchDto(txtPoId.Text, dateReceived, txtKitId.Text,
                txtMin.Text, txtMax.Text, txtDesc.Text, manDate, expDate, cmbCountry.Text, txtBatch.Text,
                txtLocation.Text, txtPalletsQty.Text, txtBoxesPallet.Text, txtKitsPallet.Text, txtTotalQty.Text,
                cmbType.Text);

            KitPo.searchKitPoDgv(dto, dgv);
        }
    }
}
