using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_waybill_details : Form
    {
        public frm_waybill_details()
        {
            InitializeComponent();
        }

        private void txtWbNo_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.numbersOnly(e);
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void frm_waybill_details_Load(object sender, EventArgs e)
        {
            HealthCenter.findCitiesCombo(cmbCity);
            load();
        }

        private void load()
        {
            Waybill.findAllWaybillCombo(cmbWbNo);
            DistributionPlan.findAllDpCombo(cmbDpName);
        }

        private void cmbDpName_TextChanged(object sender, EventArgs e)
        {
            txtDpId.ResetText();
            if(cmbDpName.Text != "" && cmbDpName.SelectedValue != null)
            {
                txtDpId.Text = cmbDpName.SelectedValue.ToString();
            }
        }

        private void txtDpId_TextChanged(object sender, EventArgs e)
        {
            txtDpName.ResetText();
            txtDpDate.ResetText();
            if(txtDpId.Text != "")
            {
                DistributionPlan.findDpDetails(txtDpId.Text, txtDpName, txtDpDate);
            }
        }

        private void cmbCity_TextChanged(object sender, EventArgs e)
        {
            cmbDistrict.SelectedValue = -1;
            cmbDistrict.DataSource = null;
            if (cmbCity.Text != "" && cmbCity.SelectedValue != null)
            {
                HealthCenter.findDistrictsCombo(cmbDistrict, cmbCity.SelectedValue.ToString());
            }
        }

        private void cmbDistrict_TextChanged(object sender, EventArgs e)
        {
            cmbCenter.SelectedValue = -1;
            cmbCenter.DataSource = null;
            if (cmbDistrict.Text != "" && cmbDistrict.SelectedValue != null)
            {
                HealthCenter.findCentersCombo(cmbCenter, cmbDistrict.SelectedValue.ToString());
            }
        }

        private void btnLoad_Click(object sender, EventArgs e)
        {
            if(txtDpId.Text == "" || cmbCenter.SelectedValue == null)
                Msg.errorMsg("You must choose DP Name and Center to load the items","Error");
            else
            {
                Waybill.findItemsKitsListNoWb(listDp, txtDpId.Text, cmbCenter.SelectedValue.ToString());
                Waybill.findItemsKitsListWb(listWb, txtDpId.Text, cmbCenter.SelectedValue.ToString());
            }
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
    }
}
