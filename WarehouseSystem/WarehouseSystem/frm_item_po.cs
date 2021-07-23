using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_item_po : Form
    {
        public frm_item_po()
        {
            InitializeComponent();
        }

        private void txtPoNo_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.numbersOnly(e);
        }

        private void cmbCountry_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.lettersOnly(e);
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void frm_item_po_Load(object sender, EventArgs e)
        {
            load();
        }

        private void load()
        {
            PurchaseOrder.findAllPoCombo(cmbPoNo);
            PurchaseOrder.findAllCountries(cmbCountry);
            Item.findAllItemsCombo(cmbItem);
            ItemPo.findItemPoDgv(dgv);
        }

        private void cmbPoNo_TextChanged(object sender, EventArgs e)
        {
            txtPoId.ResetText();
            if(cmbPoNo.Text != "")
            {
                if(cmbPoNo.SelectedValue != null)
                    txtPoId.Text = cmbPoNo.SelectedValue.ToString();
            }
        }

        private void cmbItem_TextChanged(object sender, EventArgs e)
        {
            txtItemId.ResetText();
            if(cmbItem.Text != "")
            {
                if(cmbItem.SelectedValue != null)
                    txtItemId.Text = cmbItem.SelectedValue.ToString();
            }
        }

        private void txtItemId_TextChanged(object sender, EventArgs e)
        {
            resetItemBoxes();
            if(txtItemId.Text != "")
            {
                Item.findItemBoxesById(txtItemId.Text, txtMin, txtMax, txtDesc);
            }
        }

        private void resetItemBoxes()
        {
            txtMax.ResetText();
            txtMin.ResetText();
            txtDesc.ResetText();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (cmbPoNo.Text == "" || cmbItem.Text == "" || txtMin.Text == "" || txtMax.Text == "" ||
                cmbCountry.Text == "" || txtPackaging.Text == "" || txtPalletsQty.Text == "" || txtBoxesPallet.Text == "" ||
                txtPacksBox.Text == "" || txtPiecesPack.Text == "" || txtTotalQty.Text == "" || txtLocation.Text == "")
                Msg.emptyFields();
            else
            {
                if (txtItemPoId.Text != "")
                    Msg.errorMsg("Click edit to edit previously entered data.", "Error");
                else
                {
                    ItemPoSaveDto dto = new ItemPoSaveDto(txtPoId.Text, cmbPoNo.Text, dtpReceived.Value.ToShortDateString(),
                    cmbItem.Text, txtMin.Text, txtMax.Text, txtDesc.Text, dtpMan.Value.ToShortDateString(),
                    dtpExp.Value.ToShortDateString(), cmbCountry.Text, txtBatch.Text, txtPackaging.Text,
                    txtPalletsQty.Text, txtBoxesPallet.Text, txtPacksBox.Text, txtPiecesPack.Text, txtTotalQty.Text,
                    txtItemPoId.Text, txtLocation.Text);
                    if (ItemPo.saveItemPo(dto))
                    {
                        Msg.doneMsg("Item PO saved successfully !", "Done");
                        done();
                    }
                }
            }
        }

        private void btnNew_Click(object sender, EventArgs e)
        {
            done();
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
            int pallets = 1, boxes = 1, packs = 1, pieces = 1;

            if (txtPalletsQty.Text != "")
                pallets = Convert.ToInt32(txtPalletsQty.Text);
            if (txtBoxesPallet.Text != "")
                boxes = Convert.ToInt32(txtBoxesPallet.Text);
            if (txtPacksBox.Text != "")
                packs = Convert.ToInt32(txtPacksBox.Text);
            if (txtPiecesPack.Text != "")
                pieces = Convert.ToInt32(txtPiecesPack.Text);

            txtTotalQty.Text = Convert.ToString(pallets * boxes * packs * pieces);
        }

        private void dgv_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if(e.RowIndex != -1)
            {
                txtItemPoId.Text = dgv.Rows[e.RowIndex].Cells["Item PO. ID"].Value.ToString();
                cmbPoNo.Text = dgv.Rows[e.RowIndex].Cells["PO. NO."].Value.ToString();
                cmbItem.Text = dgv.Rows[e.RowIndex].Cells["Item Name"].Value.ToString();
                dtpReceived.Value = Convert.ToDateTime(dgv.Rows[e.RowIndex].Cells["Date Received"].Value).Date;
                dtpMan.Value = Convert.ToDateTime(dgv.Rows[e.RowIndex].Cells["Man. Date"].Value).Date;
                dtpExp.Value = Convert.ToDateTime(dgv.Rows[e.RowIndex].Cells["Exp. Date"].Value).Date;
                txtPalletsQty.Text = dgv.Rows[e.RowIndex].Cells["Pallets"].Value.ToString();
                txtBoxesPallet.Text = dgv.Rows[e.RowIndex].Cells["Boxes/Pallet"].Value.ToString();
                txtPacksBox.Text = dgv.Rows[e.RowIndex].Cells["Packs/Box"].Value.ToString();
                txtPiecesPack.Text = dgv.Rows[e.RowIndex].Cells["Pieces/Pack"].Value.ToString();
                txtTotalQty.Text = dgv.Rows[e.RowIndex].Cells["Total Qty"].Value.ToString();
                txtMin.Text = dgv.Rows[e.RowIndex].Cells["Min. Temp."].Value.ToString();
                txtMax.Text = dgv.Rows[e.RowIndex].Cells["Max. Temp."].Value.ToString();
                txtPackaging.Text = dgv.Rows[e.RowIndex].Cells["Packaging"].Value.ToString();
                cmbCountry.Text = dgv.Rows[e.RowIndex].Cells["Man. Country"].Value.ToString();
                txtBatch.Text = dgv.Rows[e.RowIndex].Cells["Batch NO."].Value.ToString();
                txtDesc.Text = dgv.Rows[e.RowIndex].Cells["Desc."].Value.ToString();
                txtLocation.Text = dgv.Rows[e.RowIndex].Cells["Location"].Value.ToString();
            }
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (cmbPoNo.Text == "" || cmbItem.Text == "" || txtMin.Text == "" || txtMax.Text == "" ||
                cmbCountry.Text == "" || txtPackaging.Text == "" || txtPalletsQty.Text == "" || txtBoxesPallet.Text == "" ||
                txtPacksBox.Text == "" || txtPiecesPack.Text == "" || txtTotalQty.Text == "" || txtItemPoId.Text == "" 
                || txtLocation.Text == "")
                Msg.emptyFields();
            else
            {
                if(Msg.questionMsg("Do you want to edit Item PO ?", "Are you sure ?") == DialogResult.Yes)
                {
                    ItemPoSaveDto dto = new ItemPoSaveDto(txtPoId.Text, cmbPoNo.Text, dtpReceived.Value.ToShortDateString(),
                    cmbItem.Text, txtMin.Text, txtMax.Text, txtDesc.Text, dtpMan.Value.ToShortDateString(),
                    dtpExp.Value.ToShortDateString(), cmbCountry.Text, txtBatch.Text, txtPackaging.Text,
                    txtPalletsQty.Text, txtBoxesPallet.Text, txtPacksBox.Text, txtPiecesPack.Text, txtTotalQty.Text,
                    txtItemPoId.Text, txtLocation.Text);
                    if (ItemPo.editItemPo(dto))
                    {
                        Msg.doneMsg("Item PO edited successfully !", "Done");
                        done();
                    }
                }
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (cmbPoNo.Text == "" || cmbItem.Text == "" || txtMin.Text == "" || txtMax.Text == "" ||
                cmbCountry.Text == "" || txtPackaging.Text == "" || txtPalletsQty.Text == "" || txtBoxesPallet.Text == "" ||
                txtPacksBox.Text == "" || txtPiecesPack.Text == "" || txtTotalQty.Text == "" || txtItemPoId.Text == "")
                Msg.emptyFields();
            else
            {
                if (Msg.questionMsg("Do you want to delete Item PO ?", "Are you sure ?") == DialogResult.Yes)
                {
                    if (ItemPo.deleteItemPo(txtItemPoId.Text))
                    {
                        Msg.doneMsg("Item PO deleted successfully !", "Done");
                        done();
                    }
                }
            }
        }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            ItemPoSearchDto dto = new ItemPoSearchDto(txtPoId.Text, dtpReceived.Value.ToShortDateString(),
                txtItemId.Text, txtMin.Text, txtMax.Text, txtDesc.Text, dtpMan.Value.ToShortDateString(),
                dtpExp.Value.ToShortDateString(), cmbCountry.Text,
                txtBatch.Text, txtPackaging.Text, txtPalletsQty.Text, txtBoxesPallet.Text,
                txtPacksBox.Text, txtTotalQty.Text, txtLocation.Text, chkReceived.Checked,
                chkMan.Checked, chkExp.Checked);

            ItemPo.searchItemPoDgv(dto, dgv);

            if (dgv.Rows.Count == 0)
                ItemPo.findItemPoDgv(dgv);
        }

        private void btnDisposal_Click(object sender, EventArgs e)
        {
            Msg.errorMsg("No form yet", "NO FORM");
        }
    }
}