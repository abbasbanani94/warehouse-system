using System;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class ItemDisposal
    {
        static string baseUrl = "/itemDisposals";
        internal static void findAllItemDisposalsDgv(string disposalId,DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/" + disposalId);
        }

        internal static bool saveItemDisposal(string disposalId,ItemDisposalSaveDto dto)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PostAsJsonAsync(baseUrl + "/" + disposalId, dto);
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
                var response = client.PutAsJsonAsync(baseUrl + "/" + disposalId + "/" + itemDisposalId, dto);
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
            return Client.deleteRequest(baseUrl + "/" + id);
        }

        internal static void searchItemDisposal(string disposalId,string poId,string itemPoId,
            string qty,DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/search?disposalId=" + disposalId +
                    "&poId=" + poId + "&itemPoId=" + itemPoId + "&qty=" + qty);
        }
    }
}
