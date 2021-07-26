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
                if (txtDetailId.Text != "")
                    Msg.idInSave("Kit Detail");
                KitDetailSaveDto dto = new KitDetailSaveDto(txtBoxNo.Text, cmbItem.Text, txtMin.Text,
                    txtMax.Text, txtDesc.Text, txtPackaging.Text, txtPacksBox.Text, txtPiecesPack.Text,
                    dtpExp.Value.ToShortDateString());

                if(KitDetails.saveKitDetail(txtKitPoId.Text, dto))
                {
                    Msg.saved("Kit Detail");
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
                if(Msg.editConfirm("Kit Detail") == DialogResult.Yes)
                {
                    KitDetailSaveDto dto = new KitDetailSaveDto(txtBoxNo.Text, cmbItem.Text, txtMin.Text,
                    txtMax.Text, txtDesc.Text, txtPackaging.Text, txtPacksBox.Text, txtPiecesPack.Text,
                    dtpExp.Value.ToShortDateString());

                    if (KitDetails.editKitDetails(txtKitPoId.Text, txtDetailId.Text,dto))
                    {
                        Msg.edited("Kit Detail");
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
                if(Msg.deleteConfirm("Kit Detail") == DialogResult.Yes)
                {
                    if(KitDetails.deleteKitDetail(txtDetailId.Text))
                    {
                        Msg.deleted("Kit Detail");
                        load();
                    }
                }
            }
        }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            KitDetails.searchKitDetails(txtKitPoId.Text, txtBoxNo.Text, txtMin.Text, txtMax.Text,
                txtDesc.Text, txtPackaging.Text, txtPacksBox.Text, txtPiecesPack.Text, 
                dtpExp.Value.ToShortDateString(),txtItemId.Text,dgv,chkExp.Checked);

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
