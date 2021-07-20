using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_waybill : Form
    {
        public frm_waybill()
        {
            InitializeComponent();
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void txtWbNo_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.numbersOnly(e);
        }

        private void frm_waybill_Load(object sender, EventArgs e)
        {
            load();
            HealthCenter.findCitiesCombo(cmbCity);
        }

        private void load()
        {
            txtWbNo.Focus();
            Waybill.findAllWaybillsDgv(dgv);
        }

        private void cmbCity_TextChanged(object sender, EventArgs e)
        {
            cmbDistrict.DataSource = null;
            cmbDistrict.ResetText();
            if (cmbCity.SelectedValue != null && cmbCity.Text != "")
                HealthCenter.findDistrictsCombo(cmbDistrict, cmbCity.SelectedValue.ToString());
        }

        private void cmbDistrict_TextChanged(object sender, EventArgs e)
        {
            cmbCenter.DataSource = null;
            cmbCenter.ResetText();
            if (cmbDistrict.SelectedValue != null && cmbDistrict.Text != "")
                HealthCenter.findCentersCombo(cmbCenter, cmbDistrict.SelectedValue.ToString());
        }

        private void btnNew_Click(object sender, EventArgs e)
        {
            done();
        }

        private void done()
        {
            FormsFunctions.clearAll(groupBox1);
            load();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (txtWbNo.Text == "" || cmbCity.Text == "" || cmbDistrict.Text == "" || cmbCenter.Text == "" ||
                cmbCenter.SelectedValue == null)
                Msg.emptyFields();
            else
            {
                if (txtWbId.Text != "")
                    Msg.errorMsg("Click on Edit to edit the record", "Error");
                else
                {
                    WbSaveDto dto = new WbSaveDto(txtWbNo.Text, dtpWb.Value.ToShortDateString(),
                        txtBoxes.Text, txtPallets.Text, cmbCenter.SelectedValue.ToString());

                    if(Waybill.saveWaybill(dto))
                    {
                        Msg.doneMsg("Waybill saved successfully !", "Successfully");
                        done();
                    }
                }
            }
        }

        private void dgv_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if(e.RowIndex != -1)
            {
                txtWbId.Text = dgv.Rows[e.RowIndex].Cells["WB ID"].Value.ToString();
                txtWbNo.Text = dgv.Rows[e.RowIndex].Cells["WB NO"].Value.ToString();
                dtpWb.Value = Convert.ToDateTime(dgv.Rows[e.RowIndex].Cells["WB Date"].Value).Date;
                txtBoxes.Text = dgv.Rows[e.RowIndex].Cells["Boxes"].Value.ToString();
                txtPallets.Text = dgv.Rows[e.RowIndex].Cells["Pallets"].Value.ToString();
            }
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (txtWbNo.Text == "" || cmbCity.Text == "" || cmbDistrict.Text == "" || cmbCenter.Text == "" ||
                cmbCenter.SelectedValue == null || txtWbId.Text == "")
                Msg.emptyFields();
            else
            {
                if (Msg.questionMsg("Do you want to edit waybill ?","Are You Sure ?") == DialogResult.Yes)
                {
                    WbSaveDto dto = new WbSaveDto(txtWbNo.Text, dtpWb.Value.ToShortDateString(),
                        txtBoxes.Text, txtPallets.Text, cmbCenter.SelectedValue.ToString());

                    if (Waybill.editWaybill(txtWbId.Text,dto))
                    {
                        Msg.doneMsg("Waybill edited successfully !", "Successfully");
                        done();
                    }
                }
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (txtWbId.Text == "")
                Msg.emptyFields();
            else
            {
                if(Msg.questionMsg("Do you want to delete this waybill ?","Are You Sure ?") == DialogResult.Yes)
                {
                    if(Waybill.deleteWaybill(txtWbId.Text))
                    {
                        Msg.doneMsg("Waybill deleted successfully !", "Successfully");
                        done();
                    }
                }
            }
        }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            string date = "", cityId = "", districtId = "", centerId = "";
            if (chkDate.Checked)
                date = dtpWb.Value.ToShortDateString();
            if (cmbCity.SelectedValue != null)
                cityId = cmbCity.SelectedValue.ToString();
            if (cmbDistrict.SelectedValue != null)
                districtId = cmbDistrict.SelectedValue.ToString();
            if (cmbCenter.SelectedValue != null)
                centerId = cmbCenter.SelectedValue.ToString();

            Waybill.searchWaybill(txtWbNo.Text, date, txtBoxes.Text, txtPallets.Text, cityId, districtId,
                centerId, dgv);

            if(dgv.Rows.Count == 0)
            {
                Waybill.findAllWaybillsDgv(dgv);
            }
        }

        private frm_waybill_details _waybill_details = null;

        private void btnDetails_Click(object sender, EventArgs e)
        {
            if (txtWbId.Text == "")
                Msg.errorMsg("you must double click on the row you want to enter details for then click on Details", "Error");
            else
            {
                if (_waybill_details == null)
                {
                    _waybill_details = new frm_waybill_details(txtWbId.Text);
                    _waybill_details.Show();
                    _waybill_details.FormClosed += _waybill_details_FormClosed;
                }
                else
                    Msg.formAlreadyOpen("Waybill Details");
            }
        }

        private void _waybill_details_FormClosed(object sender, FormClosedEventArgs e)
        {
            _waybill_details = null;
        }
    }
}
