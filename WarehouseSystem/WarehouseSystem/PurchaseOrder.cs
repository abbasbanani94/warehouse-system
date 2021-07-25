using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Data;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class PurchaseOrder
    {
        internal static async void findAllPoCombo(ComboBox cmbPoNo)
        {
            try
            {
                cmbPoNo.DataSource = null;
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/po");
                List<PoCombo> poList = (List<PoCombo>)JsonConvert.DeserializeObject<List<PoCombo>>(response);
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

        internal static async void findAllPoDgv(DataGridView dgv)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/po/dgv");
                DataTable dt = (DataTable)JsonConvert.DeserializeObject(response, (typeof(DataTable)));
                dgv.DataSource = dt;
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }

        internal static async void searchPurchaseOrder(DataGridView dgv,string no)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/po/search/" + no);
                DataTable dt = (DataTable)JsonConvert.DeserializeObject(response, (typeof(DataTable)));
                dgv.DataSource = dt;
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }

        internal static async void findAllCountries(ComboBox cmbCountry)
        {
            try
            {
                cmbCountry.DataSource = null;
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/po/countries");
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
                var response = client.PostAsJsonAsync("/po/", no);
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
                var response = client.PutAsJsonAsync("/po/" + id, no);
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
                var response = client.DeleteAsync("/po/" + id);
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
