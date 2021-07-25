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
    }
}
