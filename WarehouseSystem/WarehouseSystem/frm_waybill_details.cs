using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_waybill_details : Form
    {
        public frm_waybill_details(string wbId)
        {
            InitializeComponent();
            txtWbId.Text = wbId;
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void frm_waybill_details_Load(object sender, EventArgs e)
        {
            Waybill.findWaybillById(txtWbId.Text, txtWbNo, txtWbDate, txtBoxes, txtPallets, txtCity,
                txtDistrict, txtCenter);
        }

        private void btnLoad_Click(object sender, EventArgs e)
        {
            Waybill.findItems(listDp, txtWbId.Text, "/waybills/dp-items/");
            Waybill.findItems(listWb, txtWbId.Text, "/waybills/wb-items/");
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            if (listDp.SelectedItems.Count == 0)
                Msg.errorMsg("You must select atleast one item from DP to move it into WB", "Error");
            else
            {
                FormsFunctions.moveItems(listDp, listWb);
            }
        }

        private void btnAddAll_Click(object sender, EventArgs e)
        {
            if (listDp.Items.Count == 0)
                Msg.errorMsg("no items in DP list to be moved into WB", "Error");
            else
            {
                for(int i=0; i < listDp.Items.Count; i++)
                {
                    listWb.Items.Add(listDp.Items[i]);
                }
                listDp.Items.Clear();
            }
        }

        private void btnRemove_Click(object sender, EventArgs e)
        {
            if(listWb.SelectedItems.Count == 0)
                Msg.errorMsg("You must select atleast one item from WB to move it back into DP", "Error");
            else
            {
                FormsFunctions.moveItems(listWb, listDp);
            }
        }

        private void btnRemoveAll_Click(object sender, EventArgs e)
        {
            if (listWb.Items.Count == 0)
                Msg.errorMsg("no items in WB list to be moved into DP", "Error");
            else
            {
                for (int i = 0; i < listWb.Items.Count; i++)
                {
                    listDp.Items.Add(listWb.Items[i]);
                }
                listWb.Items.Clear();
            }
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if(Msg.questionMsg("Do you want to save Waybill details ?","Save Confirmation") == DialogResult.Yes)
            {
                List<string> dpList = FormsFunctions.addFromListBox(listDp);
                List<string> wbList = FormsFunctions.addFromListBox(listWb);
                WbDetailsSaveDto dto = new WbDetailsSaveDto(dpList, wbList);
                if(Waybill.saveWaybillDetails(txtWbId.Text, dto))
                {
                    Msg.saved("Waybill Details");
                }
            }
        }
    }
}
