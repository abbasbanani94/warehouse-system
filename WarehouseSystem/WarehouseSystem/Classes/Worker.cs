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
            return Client.saveRequest(baseUrl, dto);
        }

        internal static bool editWorker(WorkerSaveDto dto, string id)
        {
            return Client.editRequest(baseUrl + "/" + id, dto);
        }

        internal static bool deleteWorker(string id)
        {
            return Client.deleteRequest(baseUrl + "/" + id);
        }

        internal static void searchWorkers(string enName,string arName, string mobile,DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/search?enName=" + enName + "&arName=" +
                    arName + "&mobile=" + mobile);
        }
    }
}
