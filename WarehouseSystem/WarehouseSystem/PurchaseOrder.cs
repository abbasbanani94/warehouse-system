using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class PurchaseOrder
    {
        static string baseUrl = "/po";
        internal static async void findAllPoCombo(ComboBox cmbPoNo,string url)
        {
            try
            {
                cmbPoNo.DataSource = null;
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync(url);
                List<PoCombo> poList = JsonConvert.DeserializeObject<List<PoCombo>>(response);
                cmbPoNo.DataSource = poList;
                cmbPoNo.DisplayMember = "no";
                cmbPoNo.ValueMember = "id";
                cmbPoNo.ResetText();
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }
        internal static void findAllPoCombo(ComboBox cmbPoNo)
        {
            findAllPoCombo(cmbPoNo, baseUrl);
        }
        internal static void findAllPoComboItems(ComboBox cmbPoNo)
        {
            findAllPoCombo(cmbPoNo, baseUrl + "/items");
        }

        internal static void findAllPoComboKits(ComboBox cmbPoNo)
        {
            findAllPoCombo(cmbPoNo, baseUrl + "/kits");
        }

        internal static void findAllPoDgv(DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/dgv");
        }

        internal static void searchPurchaseOrder(DataGridView dgv,string no)
        {
            Client.findAllDgv(dgv, baseUrl + "/search/" + no);
        }

        internal static async void findAllCountries(ComboBox cmbCountry)
        {
            try
            {
                cmbCountry.DataSource = null;
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync(baseUrl + "/countries");
                List<ComboDto> poList = JsonConvert.DeserializeObject<List<ComboDto>>(response);
                cmbCountry.DataSource = poList;
                cmbCountry.DisplayMember = "name";
                cmbCountry.ValueMember = "id";
                cmbCountry.ResetText();
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }

        internal static bool savePurchaseOrder(int no)
        {
            return Client.saveRequest(baseUrl, no);
        }

        internal static bool editPurchaseOrder(int id,int no)
        {
            return Client.editRequest(baseUrl + "/" + id, no);
        }

        internal static bool deletePurchaseOrder(string id)
        {
            return Client.deleteRequest(baseUrl + "/" + id);
        }
    }
}
