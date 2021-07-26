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
        internal static async void findAllPoCombo(ComboBox cmbPoNo)
        {
            try
            {
                cmbPoNo.DataSource = null;
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync(baseUrl);
                List<PoCombo> poList = JsonConvert.DeserializeObject<List<PoCombo>>(response);
                cmbPoNo.DataSource = poList;
                cmbPoNo.DisplayMember = "no";
                cmbPoNo.ValueMember = "id";
                cmbPoNo.ResetText();
            }
            catch(Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
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
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PostAsJsonAsync(baseUrl + "/", no);
                if (response.Result.StatusCode == System.Net.HttpStatusCode.OK)
                    return true;
                else
                {
                    Msg.errorMsg(response.Result.Content.ReadAsStringAsync().Result, "Error");
                }
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
            return false;
        }

        internal static bool editPurchaseOrder(int id,int no)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PutAsJsonAsync(baseUrl + "/" + id, no);
                if (response.Result.StatusCode == System.Net.HttpStatusCode.OK)
                    return true;
                else
                {
                    Msg.errorMsg(response.Result.Content.ReadAsStringAsync().Result, "Error");
                }
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
            return false;
        }

        internal static bool deletePurchaseOrder(string id)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.DeleteAsync(baseUrl + "/" + id);
                if (response.Result.StatusCode == System.Net.HttpStatusCode.OK)
                    return true;
                else
                {
                    Msg.errorMsg(response.Result.Content.ReadAsStringAsync().Result, "Error");
                }
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
            return false;
        }
    }
}
