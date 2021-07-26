using Newtonsoft.Json;
using System;
using System.Data;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class ItemDisposal
    {
        internal static async void findAllItemDisposalsDgv(string disposalId,DataGridView dgv)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/itemDisposals/" + disposalId);
                DataTable dt = (DataTable)JsonConvert.DeserializeObject(response, (typeof(DataTable)));
                dgv.DataSource = dt;
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }

        internal static bool saveItemDisposal(string disposalId,ItemDisposalSaveDto dto)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PostAsJsonAsync("/itemDisposals/" + disposalId, dto);
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

        internal static bool editItemDisposal(string disposalId,string itemDisposalId,ItemDisposalSaveDto dto)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PutAsJsonAsync("/itemDisposals/" + disposalId + "/" + itemDisposalId, dto);
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

        internal static bool deleteItemDisposal(string id)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.DeleteAsync("/itemDisposals/" + id);
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

        internal static async void searchItemDisposal(string disposalId,string poId,string itemPoId,
            string qty,DataGridView dgv)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/itemDisposals/search?disposalId=" + disposalId + 
                    "&poId=" + poId + "&itemPoId=" + itemPoId + "&qty=" + qty);
                DataTable dt = (DataTable)JsonConvert.DeserializeObject(response, (typeof(DataTable)));
                dgv.DataSource = dt;
                if (dgv.Rows.Count == 0)
                    Msg.errorMsg("No data found", "NO DATA");
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }
    }
}
