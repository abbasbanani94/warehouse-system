using System;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class Worker
    {
        static string baseUrl = "/workers";
        internal static void findAllWorkersDgv(DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl);
        }

        internal static bool saveWorker(WorkerSaveDto dto)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PostAsJsonAsync(baseUrl, dto);
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

        internal static bool editWorker(WorkerSaveDto dto, string id)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PutAsJsonAsync(baseUrl + "/" + id, dto);
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

        internal static bool deleteWorker(string id)
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

        internal static void searchWorkers(string enName,string arName, string mobile,DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/search?enName=" + enName + "&arName=" +
                    arName + "&mobile=" + mobile);
        }
    }
}
