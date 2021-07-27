using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_check_item : Form
    {
        public frm_check_item(string checkId)
        {
            InitializeComponent();
            txtCheckId.Text = checkId;
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void frm_check_item_Load(object sender, EventArgs e)
        {
            Check.findCheckDetailsById(txtCheckId.Text, txtNotes, txtDate, txtType);
        }

        private void btnLoad_Click(object sender, EventArgs e)
        {
            Check.findListBoxItems(listAll, "/checks/" + txtCheckId.Text + "/all-items");
            Check.findListBoxItems(listCheck, "/checks/" + txtCheckId.Text + "/check-items");
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            if (listAll.SelectedItems.Count == 0)
                Msg.errorMsg("You must select atleast one Item from Items to move it into Check", "Error");
            else
            {
                FormsFunctions.moveItems(listAll, listCheck);
            }
        }

        private void btnAddAll_Click(object sender, EventArgs e)
        {
            if (listAll.Items.Count == 0)
                Msg.errorMsg("no items in Items list to be moved into Check List", "Error");
            else
            {
                for (int i = 0; i < listAll.Items.Count; i++)
                {
                    listCheck.Items.Add(listAll.Items[i]);
                }
                listAll.Items.Clear();
            }
        }

        private void btnRemove_Click(object sender, EventArgs e)
        {
            if (listCheck.SelectedItems.Count == 0)
                Msg.errorMsg("You must select atleast one item from Check List to move it back into Items List", "Error");
            else
            {
                FormsFunctions.moveItems(listCheck, listAll);
            }
        }

        private void btnRemoveAll_Click(object sender, EventArgs e)
        {
            if (listCheck.Items.Count == 0)
                Msg.errorMsg("no items in Check list to be moved into Items List", "Error");
            else
            {
                for (int i = 0; i < listCheck.Items.Count; i++)
                {
                    listAll.Items.Add(listCheck.Items[i]);
                }
                listCheck.Items.Clear();
            }
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (Msg.questionMsg("Do you want to save Check Items ?", "Save Confirmation") == DialogResult.Yes)
            {
                List<string> items = FormsFunctions.addFromListBox(listCheck);
                CheckWorkerDto dto = new CheckWorkerDto(items);
                if (Check.saveCheckItems(txtCheckId.Text, dto))
                {
                    Msg.saved("Check Workers");
                }
            }
        }
    }
}
