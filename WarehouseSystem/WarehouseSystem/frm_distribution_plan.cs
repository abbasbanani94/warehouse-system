using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_distribution_plan : Form
    {
        public frm_distribution_plan()
        {
            InitializeComponent();
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void frm_distribution_plan_Load(object sender, EventArgs e)
        {
            load();
        }

        private void load()
        {
            txtEn.Focus();
            DistributionPlan.findAllDpDgv(dgv);
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (txtAr.Text == "" || txtEn.Text == "")
                Msg.emptyFields();
            else
            {
                if (txtId.Text != "")
                    Msg.errorMsg("click on Edit button if you want to edit", "Error");
                else
                {
                    PlanSaveDto dto = new PlanSaveDto(txtEn.Text, txtAr.Text, dtp.Value.ToShortDateString());
                    if(DistributionPlan.saveDistributionPlan(dto))
                    {
                        Msg.doneMsg("Distribution Plan saved successfully !", "Successfully");
                        done();
                    }
                }
            }
        }

        private void done()
        {
            FormsFunctions.clearAll(groupBox1);
            load();
        }

        private void dgv_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if(e.RowIndex != -1)
            {
                txtId.Text = dgv.Rows[e.RowIndex].Cells["Plan ID"].Value.ToString();
                txtEn.Text = dgv.Rows[e.RowIndex].Cells["Name En"].Value.ToString();
                txtAr.Text = dgv.Rows[e.RowIndex].Cells["Name Ar"].Value.ToString();
                dtp.Value = Convert.ToDateTime(dgv.Rows[e.RowIndex].Cells["Date"].Value).Date;
            }
        }

        private void btnNew_Click(object sender, EventArgs e)
        {
            done();
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (txtId.Text == "" || txtAr.Text == "" || txtEn.Text == "")
                Msg.emptyFields();
            else
            {
                if(Msg.questionMsg("Do you want to edit DP ?","Edit Confirmation") == DialogResult.Yes)
                {
                    PlanSaveDto dto = new PlanSaveDto(txtEn.Text, txtAr.Text, dtp.Value.ToShortDateString());
                    if (DistributionPlan.editDistributionPlan(txtId.Text,dto))
                    {
                        Msg.doneMsg("Distribution Plan edited successfully !", "Successfully");
                        done();
                    }
                }
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (txtId.Text == "")
                Msg.emptyFields();
            else
            {
                if(Msg.questionMsg("Do you want to delete this DP ?","Delete Confirmation") == DialogResult.Yes)
                {
                    if(DistributionPlan.deleteDistributionPlan(txtId.Text))
                    {
                        Msg.doneMsg("Distribution Plan deleted successfully !", "Successfully");
                        done();
                    }
                }
            }
        }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            if (txtEn.Text == "" && txtAr.Text == "" && !chkDate.Checked)
                Msg.emptyFields();
            else
            {
                DistributionPlan.searchDistributionPlan(txtEn.Text, txtAr.Text, dtp.Value.ToShortDateString(),
                    chkDate.Checked, dgv);

                if (dgv.Rows.Count == 0)
                    DistributionPlan.findAllDpDgv(dgv);
            }
        }
    }
}
