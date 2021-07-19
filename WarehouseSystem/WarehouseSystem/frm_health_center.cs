using Newtonsoft.Json;
using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_health_center : Form
    {
        public frm_health_center()
        {
            InitializeComponent();
        }

        private void cmbCity_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.lettersOnly(e);
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void frm_health_center_Load(object sender, EventArgs e)
        {
            HealthCenter.findCitiesCombo(cmbCity);
            load();
        }

        private void load()
        {
            HealthCenter.findAllHealthCenters(dgv);
        }

        private void cmbCity_SelectedIndexChanged(object sender, EventArgs e)
        {
            if(cmbCity.Text != "")
            {
                HealthCenter.findDistrictsCombo(cmbDistrict, cmbCity.SelectedValue.ToString());
            }
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
            if (cmbCity.Text == "" || cmbDistrict.Text == "" || txtAr.Text == "" || txtEn.Text == "")
                Msg.emptyFields();
            else
            {
                if (txtCenterId.Text != "")
                    Msg.errorMsg("Click edit to edit this record", "Error");
                else
                {
                    CenterSaveDto dto = new CenterSaveDto("", cmbDistrict.SelectedValue.ToString(), txtEn.Text,
                    txtAr.Text);

                    if(HealthCenter.saveHealthCenter(dto))
                    {
                        Msg.doneMsg("Health Center saved successfully !", "Saved Successfully");
                        done();
                    }
                }
                
            }
        }

        private void dgv_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if(e.RowIndex != -1)
            {
                txtCenterId.Text = dgv.Rows[e.RowIndex].Cells["Center ID"].Value.ToString();
                cmbCity.Text = dgv.Rows[e.RowIndex].Cells["City"].Value.ToString();
                cmbDistrict.Text = dgv.Rows[e.RowIndex].Cells["District"].Value.ToString();
                txtEn.Text = dgv.Rows[e.RowIndex].Cells["Center En"].Value.ToString();
                txtAr.Text = dgv.Rows[e.RowIndex].Cells["Center Ar"].Value.ToString();
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (cmbCity.Text == "" || cmbDistrict.Text == "" || txtAr.Text == "" || txtEn.Text == "" ||
                txtCenterId.Text == "")
                Msg.emptyFields();
            else
            {
                if (Msg.questionMsg("Do you want to delete health center ?", "Are You Sure") == DialogResult.Yes)
                {
                    if (HealthCenter.deleteHealthCenter(txtCenterId.Text))
                    {
                        Msg.doneMsg("Health Center deleted successfully !", "Deleted Successfully");
                        done();
                    }
                }
            }
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (cmbCity.Text == "" || cmbDistrict.Text == "" || txtAr.Text == "" || txtEn.Text == "" || 
                txtCenterId.Text == "")
                Msg.emptyFields();
            else
            {
                if (Msg.questionMsg("Do you want to edit health center ?","Are You Sure") == DialogResult.Yes)
                {
                    CenterEditDto dto = new CenterEditDto(cmbDistrict.Text, txtEn.Text, txtAr.Text);

                    if (HealthCenter.editHealthCenter(dto,txtCenterId.Text))
                    {
                        Msg.doneMsg("Health Center edited successfully !", "Edited Successfully");
                        done();
                    }
                }
            }
        }

        private void cmbDistrict_TextChanged(object sender, EventArgs e)
        {
            
        }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            HealthCenter.searchHealthCenter(cmbCity.Text, cmbDistrict.Text, txtEn.Text, txtAr.Text,
                dgv);
            if (dgv.Rows.Count == 0)
                HealthCenter.findAllHealthCenters(dgv);
        }
    }
}
