using Newtonsoft.Json;
using System;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class Disposal
    {
        private static string baseUrl = "/disposals";
        internal static void findAllDisposalsDgv(DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl);
        }

        internal static bool saveDisposal(DisposalSaveDto dto)
        {
            return Client.saveRequest(baseUrl, dto);
        }

        internal static bool editDisposal(string id, DisposalSaveDto dto)
        {
            return Client.editRequest(baseUrl + "/" + id, dto);
        }

        internal static bool deleteDisposal(string id)
        {
            return Client.deleteRequest(baseUrl + "/" + id);
        }

        internal static void searchDisposals (string reason, string date,bool d, DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/search?reason=" + reason + "&date=" +
                    date + "&d=" + d);
        }

        internal static async void findDisposalDetails(string id, TextBox reason, TextBox date)
        {
            if (id.IndexOf('{') == -1)
            {
                try
                {
                    HttpClient client = Client.getHttpClient();
                    var response = await client.GetStringAsync(baseUrl + "/details/" + id);
                    DisposalSaveDto dto = JsonConvert.DeserializeObject<DisposalSaveDto>(response);
                    reason.Text = dto.reason;
                    date.Text = dto.date;
                }
                catch (Exception ex)
                {
                    Msg.errorMsg(ex.Message.ToString(), "Error");
                }
            }
        }
    }
}
