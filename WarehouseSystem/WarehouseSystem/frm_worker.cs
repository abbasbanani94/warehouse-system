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
                    Msg.idInSave("Worker");
                else
                {
                    WorkerSaveDto dto = new WorkerSaveDto(txtEn.Text, txtAr.Text, txtmobile.Text);
                    if (Worker.saveWorker(dto))
                    {
                        Msg.saved("Worker");
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
                if (Msg.editConfirm("Worker") == DialogResult.Yes)
                {
                    WorkerSaveDto dto = new WorkerSaveDto(txtEn.Text, txtAr.Text, txtmobile.Text);
                    if (Worker.editWorker(dto, txtWorkerId.Text))
                    {
                        Msg.edited("Worker");
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
                if (Msg.deleteConfirm("Worker") == DialogResult.Yes)
                {
                    if (Worker.deleteWorker(txtWorkerId.Text))
                    {
                        Msg.deleted("Worker");
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
