using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class Check
    {
        static string baseUrl = "/checks";
        internal static void findAllChecksDgv(DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl);
        }

        internal static bool saveCheck(CheckSaveDto dto)
        {
            return Client.saveRequest(baseUrl, dto);
        }

        internal static bool editCheck(string id, CheckSaveDto dto)
        {
            return Client.editRequest(baseUrl + "/" + id, dto);
        }

        internal static async void findCheckDetailsById(string id, TextBox notes, TextBox date, TextBox type)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync(baseUrl + "/details/" + id);
                CheckDetailsDto dto = JsonConvert.DeserializeObject<CheckDetailsDto>(response);
                notes.Text = dto.notes;
                date.Text = dto.date;
                type.Text = dto.type;
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }

        internal static bool deleteCheck(string id)
        {
            return Client.deleteRequest(baseUrl + "/" + id);
        }

        internal static void searchChecks(string notes, string type, string date, bool d, DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/search?type=" + type + "&notes=" + notes +
                "&date=" + date + "&d=" + d);
        }

        internal static async void findListBoxItems(ListBox list,string url)
        {
            try
            {
                list.Items.Clear();
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync(url);
                List<string> itemList = JsonConvert.DeserializeObject<List<string>>(response);
                list.Items.AddRange(itemList.ToArray());
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }

        internal static bool saveCheckWorkers(string checkId, CheckWorkerDto dto)
        {
            return Client.saveRequest(baseUrl + "/" + checkId + "/check-workers", dto);
        }

        internal static bool saveCheckItems(string checkId, CheckWorkerDto dto)
        {
            return Client.saveRequest(baseUrl + "/" + checkId + "/check-items", dto);
        }
    }
}
