using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_worker : Form
    {
        public frm_worker()
        {
            InitializeComponent();
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void txtEn_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.lettersOnly(e);
        }

        private void txtmobile_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.numbersOnly(e);
        }

        private void frm_worker_Load(object sender, EventArgs e)
        {
            load();
        }

        private void load()
        {
            txtEn.Focus();
            Worker.findAllWorkersDgv(dgv);
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (txtEn.Text == "" || txtAr.Text == "" || txtmobile.Text == "")
                Msg.emptyFields();
            else
            {
                if (txtWorkerId.Text != "")
                    Msg.errorMsg("click Edit button to edit this worker", "Error");
                else
                {
                    WorkerSaveDto dto = new WorkerSaveDto(txtEn.Text, txtAr.Text, txtmobile.Text);
                    if(Worker.saveWorker(dto))
                    {
                        Msg.doneMsg("Daily Worker saved successfully !", "Successfully");
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

        private void btnNew_Click(object sender, EventArgs e)
        {
            done();
        }

        private void dgv_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if(e.RowIndex != -1)
            {
                txtWorkerId.Text = dgv.Rows[e.RowIndex].Cells["Worker ID"].Value.ToString();
                txtEn.Text = dgv.Rows[e.RowIndex].Cells["Name En"].Value.ToString();
                txtAr.Text = dgv.Rows[e.RowIndex].Cells["Name Ar"].Value.ToString();
                txtmobile.Text = dgv.Rows[e.RowIndex].Cells["Mobile"].Value.ToString();
            }
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (txtEn.Text == "" || txtAr.Text == "" || txtmobile.Text == "" || txtWorkerId.Text == "")
                Msg.emptyFields();
            else
            {
                if (Msg.questionMsg("Do you want to edit this Daily Worker ?","Edit Confirmation") == DialogResult.Yes)
                {
                    WorkerSaveDto dto = new WorkerSaveDto(txtEn.Text, txtAr.Text, txtmobile.Text);
                    if (Worker.editWorker(dto, txtWorkerId.Text))
                    {
                        Msg.doneMsg("Daily Worker edited successfully !", "Successfully");
                        done();
                    }
                }
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (txtEn.Text == "" || txtAr.Text == "" || txtmobile.Text == "" || txtWorkerId.Text == "")
                Msg.emptyFields();
            else
            {
                if (Msg.questionMsg("Do you want to delete this Daily Worker ?", "Delete Confirmation") == DialogResult.Yes)
                {
                    if (Worker.deleteWorker(txtWorkerId.Text))
                    {
                        Msg.doneMsg("Daily Worker deleted successfully !", "Successfully");
                        done();
                    }
                }
            }
        }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            if (txtEn.Text == "" && txtAr.Text == "" && txtmobile.Text == "")
                Msg.emptyFields();
            else
            {
                Worker.searchWorkers(txtEn.Text, txtAr.Text, txtmobile.Text, dgv);
                if (dgv.Rows.Count == 0)
                    Worker.findAllWorkersDgv(dgv);
            }
        }
    }
}
