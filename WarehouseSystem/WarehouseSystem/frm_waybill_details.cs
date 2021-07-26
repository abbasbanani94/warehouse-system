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
                moveItems(listDp, listWb);
            }
        }

        private void moveItems(ListBox source, ListBox destination)
        {
            List<string> items = new List<string>();
            for (int i = 0; i < source.SelectedItems.Count; i++)
            {
                for (int j = 0; j < source.Items.Count; j++)
                {
                    if ((string)source.SelectedItems[i] == source.Items[j].ToString())
                    {
                        destination.Items.Add(source.Items[j]);
                        items.Add(source.Items[j].ToString());
                        break;
                    }
                }
            }
            for (int i = 0; i < items.Count; i++)
            {
                for (int j = 0; j < source.Items.Count; j++)
                {
                    if (items[i] == source.Items[j].ToString())
                    {
                        source.Items.RemoveAt(j);
                        break;
                    }
                }
            }
        }

        private void btnAddAll_Click(object sender, EventArgs e)
        {
            if (listDp.Items.Count == 0)
                Msg.errorMsg("no items in DP list to be moved into WB", "Error");
            else
            {
                for(int i=listDp.Items.Count - 1; i > -1; i--)
                {
                    listWb.Items.Add(listDp.Items[i]);
                    listDp.Items.RemoveAt(i);
                }
            }
        }

        private void btnRemove_Click(object sender, EventArgs e)
        {
            if(listWb.SelectedItems.Count == 0)
                Msg.errorMsg("You must select atleast one item from WB to move it back into DP", "Error");
            else
            {
                moveItems(listWb, listDp);
            }
        }

        private void btnRemoveAll_Click(object sender, EventArgs e)
        {
            if (listWb.Items.Count == 0)
                Msg.errorMsg("no items in WB list to be moved into DP", "Error");
            else
            {
                for (int i = listWb.Items.Count - 1; i > -1; i--)
                {
                    listDp.Items.Add(listWb.Items[i]);
                    listWb.Items.RemoveAt(i);
                }
            }
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if(Msg.questionMsg("Do you want to save Waybill details ?","Save Confirmation") == DialogResult.Yes)
            {
                List<string> dpList = addFromListBox(listDp);
                List<string> wbList = addFromListBox(listWb);
                WbDetailsSaveDto dto = new WbDetailsSaveDto(dpList, wbList);
                if(Waybill.saveWaybillDetails(txtWbId.Text, dto))
                {
                    Msg.saved("Waybill Details");
                }
            }
        }

        private List<string> addFromListBox(ListBox listBox)
        {
            List<string> list = new List<string>();
            for(int i=0; i<listBox.Items.Count; i++)
            {
                list.Add(listBox.Items[i].ToString());
            }
            return list;
        }
    }
}
