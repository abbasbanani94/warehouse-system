using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class Waybill
    {
        internal static async void findAllWaybillCombo(ComboBox cmbWbNo)
        {
            try
            {
                cmbWbNo.DataSource = null;
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/waybills/combo");
                List<PoCombo> poList = (List<PoCombo>)JsonConvert.DeserializeObject<List<PoCombo>>(response);
                cmbWbNo.DataSource = poList;
                cmbWbNo.DisplayMember = "no";
                cmbWbNo.ValueMember = "id";
                cmbWbNo.ResetText();
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }

        internal static async void findItemsKitsListNoWb(ListBox listDp, string dpId, string centerId)
        {
            try
            {
                listDp.Items.Clear();
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/waybills/items-kits-no-wb/list?dpId=" + dpId +
                    "&centerId=" + centerId);
                List<string> poList = JsonConvert.DeserializeObject<List<string>>(response);
                foreach (string i in poList)
                    listDp.Items.Add(i);
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }

        internal static async void findItemsKitsListWb(ListBox listWb, string dpId, string centerId)
        {
            try
            {
                listWb.Items.Clear();
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/waybills/items-kits-wb/list?dpId=" + dpId +
                    "&centerId=" + centerId);
                List<string> poList = JsonConvert.DeserializeObject<List<string>>(response);
                foreach (string i in poList)
                    listWb.Items.Add(i);
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }
    }
}
