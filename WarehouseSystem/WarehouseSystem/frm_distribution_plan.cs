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
                    Msg.idInSave("DP");
                else
                {
                    PlanSaveDto dto = new PlanSaveDto(txtEn.Text, txtAr.Text, dtp.Value.ToShortDateString());
                    if (DistributionPlan.saveDistributionPlan(dto))
                    {
                        Msg.saved("Distribution Plan");
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
                if(Msg.editConfirm("Distribution Plan") == DialogResult.Yes)
                {
                    PlanSaveDto dto = new PlanSaveDto(txtEn.Text, txtAr.Text, dtp.Value.ToShortDateString());
                    if (DistributionPlan.editDistributionPlan(txtId.Text,dto))
                    {
                        Msg.edited("Distribution Plan");
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
                if(Msg.deleteConfirm("Distribution Plan") == DialogResult.Yes)
                {
                    if(DistributionPlan.deleteDistributionPlan(txtId.Text))
                    {
                        Msg.deleted("Distribution Plan");
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
