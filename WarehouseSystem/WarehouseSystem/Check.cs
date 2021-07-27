using System;
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

        internal static bool deleteCheck(string id)
        {
            return Client.deleteRequest(baseUrl + "/" + id);
        }

        internal static void searchChecks(string notes, string type, string date, bool d, DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/search?type=" + type + "&notes=" + notes +
                "&date=" + date + "&d=" + d);
        }
    }
}
