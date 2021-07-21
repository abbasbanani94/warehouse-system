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
            txtDetailId.ResetText();
            Item.findAllItemsCombo(cmbItem);
            KitDetails.findAllKitDetailsByKitPo(txtKitPoId.Text, dgv);
        }

        private void cmbItem_TextChanged(object sender, EventArgs e)
        {
            txtItemId.ResetText();
            resetBoxes();
            if (cmbItem.Text != "" && cmbItem.SelectedValue != null)
            {
                txtItemId.Text = cmbItem.SelectedValue.ToString();
                Item.findItemBoxesById(cmbItem.SelectedValue.ToString(), txtMin, txtMax, txtDesc);
            }
        }

        private void resetBoxes()
        {
            txtMin.ResetText();
            txtMax.ResetText();
            txtDesc.ResetText();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (cmbItem.Text == "" || txtMin.Text == "" || txtMax.Text == "" || txtPackaging.Text == "" ||
                txtPacksBox.Text == "" || txtPiecesPack.Text == "")
                Msg.emptyFields();
            else
            {
                KitDetailSaveDto dto = new KitDetailSaveDto(txtBoxNo.Text, cmbItem.Text, txtMin.Text,
                    txtMax.Text, txtDesc.Text, txtPackaging.Text, txtPacksBox.Text, txtPiecesPack.Text,
                    dtpExp.Value.ToShortDateString());

                if(KitDetails.saveKitDetail(txtKitPoId.Text, dto))
                {
                    Msg.doneMsg("Kit detail saved successfully !", "Successfully");
                    load();
                }
            }
        }

        private void dgv_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if(e.RowIndex != -1)
            {
                txtDetailId.Text = dgv.Rows[e.RowIndex].Cells["Detail ID"].Value.ToString();
                txtBoxNo.Text = dgv.Rows[e.RowIndex].Cells["Box"].Value.ToString();
                cmbItem.Text = dgv.Rows[e.RowIndex].Cells["Item"].Value.ToString();
                txtPackaging.Text = dgv.Rows[e.RowIndex].Cells["Packaging"].Value.ToString();
                txtPacksBox.Text = dgv.Rows[e.RowIndex].Cells["Packs"].Value.ToString();
                txtPiecesPack.Text = dgv.Rows[e.RowIndex].Cells["Pieces"].Value.ToString();
                dtpExp.Value = Convert.ToDateTime(dgv.Rows[e.RowIndex].Cells["Exp. Date"].Value).Date;

            }
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (cmbItem.Text == "" || txtMin.Text == "" || txtMax.Text == "" || txtPackaging.Text == "" ||
                txtPacksBox.Text == "" || txtPiecesPack.Text == "" || txtDetailId.Text == "")
                Msg.emptyFields();
            else
            {
                if(Msg.questionMsg("Do you want to edit Kit Details ?", "Edit Confirmation") == DialogResult.Yes)
                {
                    KitDetailSaveDto dto = new KitDetailSaveDto(txtBoxNo.Text, cmbItem.Text, txtMin.Text,
                    txtMax.Text, txtDesc.Text, txtPackaging.Text, txtPacksBox.Text, txtPiecesPack.Text,
                    dtpExp.Value.ToShortDateString());

                    if (KitDetails.editKitDetails(txtKitPoId.Text, txtDetailId.Text,dto))
                    {
                        Msg.doneMsg("Kit detail edited successfully !", "Successfully");
                        load();
                    }
                }
                
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (txtDetailId.Text == "")
                Msg.emptyFields();
            else
            {
                if(Msg.questionMsg("Do you want to delete Kit Detail ?", "Delete Confirmation") == DialogResult.Yes)
                {
                    if(KitDetails.deleteKitDetail(txtDetailId.Text))
                    {
                        Msg.doneMsg("Kit detail deleted successfully !", "Successfully");
                        load();
                    }
                }
            }
        }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            string exp = "";
            if (chkExp.Checked)
                exp = dtpExp.Value.ToShortDateString();
            KitDetails.searchKitDetails(txtKitPoId.Text, txtBoxNo.Text, txtMin.Text, txtMax.Text,
                txtDesc.Text, txtPackaging.Text, txtPacksBox.Text, txtPiecesPack.Text, exp, txtItemId.Text,
                dgv);

            if (dgv.Rows.Count == 0)
                KitDetails.findAllKitDetailsByKitPo(txtKitPoId.Text, dgv);
        }

        private void btnNew_Click(object sender, EventArgs e)
        {
            load();
        }

        private void btnDisposal_Click(object sender, EventArgs e)
        {
            Msg.errorMsg("no form yet", "error");
        }
    }
}
