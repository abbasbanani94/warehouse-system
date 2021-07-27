using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_check_worker : Form
    {
        public frm_check_worker(string checkId)
        {
            InitializeComponent();
            txtCheckId.Text = checkId;
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void frm_check_worker_Load(object sender, EventArgs e)
        {
            Check.findCheckDetailsById(txtCheckId.Text,txtNotes,txtDate,txtType);
        }

        private void btnLoad_Click(object sender, EventArgs e)
        {
            Check.findWorkers(listWorkers,"/checks/" + txtCheckId.Text + "/all-workers");
            Check.findWorkers(listCheck, "/checks/" + txtCheckId.Text + "/check-workers");
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            if (listWorkers.SelectedItems.Count == 0)
                Msg.errorMsg("You must select atleast one Worker from Workers to move it into Check", "Error");
            else
            {
                FormsFunctions.moveItems(listWorkers, listCheck);
            }
        }

        private void btnAddAll_Click(object sender, EventArgs e)
        {
            if (listWorkers.Items.Count == 0)
                Msg.errorMsg("no workers in Workers list to be moved into Check List", "Error");
            else
            {
                for (int i = 0; i < listWorkers.Items.Count; i++)
                {
                    listCheck.Items.Add(listWorkers.Items[i]);
                }
                listWorkers.Items.Clear();
            }
        }

        private void btnRemove_Click(object sender, EventArgs e)
        {
            if (listCheck.SelectedItems.Count == 0)
                Msg.errorMsg("You must select atleast one worker from Check List to move it back into Workers List", "Error");
            else
            {
                FormsFunctions.moveItems(listCheck, listWorkers);
            }
        }

        private void btnRemoveAll_Click(object sender, EventArgs e)
        {
            if (listCheck.Items.Count == 0)
                Msg.errorMsg("no workers in Check list to be moved into Workers List", "Error");
            else
            {
                for (int i = 0; i < listCheck.Items.Count; i++)
                {
                    listWorkers.Items.Add(listCheck.Items[i]);
                }
                listCheck.Items.Clear();
            }
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if(Msg.questionMsg("Do you want to save Check Workers ?","Save Confirmation") == DialogResult.Yes)
            {
                List<string> worker = FormsFunctions.addFromListBox(listCheck);
                CheckWorkerDto dto = new CheckWorkerDto(worker);
                if(Check.saveCheckWorkers(txtCheckId.Text,dto))
                {
                    Msg.saved("Check Workers");
                }
            }
        }
    }
}
