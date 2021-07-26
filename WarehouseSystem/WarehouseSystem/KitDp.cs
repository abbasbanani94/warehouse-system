using System;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class KitDp
    {
        static string baseUrl = "/kitDp";
        internal static void findKitDpDgv(DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl);
        }

        internal static bool saveKitDp(KitDpSaveDto dto)
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

        internal static bool editKitDp(string id, KitDpSaveDto dto)
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

        internal static bool deleteKitDp(string id)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.DeleteAsync(baseUrl+ "/" + id);
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

        internal static void searchKitDpDgv(DataGridView dgv, string planId, string date, string poId,
            string kitPoId, string cityId, string districtId, string centerId, string qty,bool d)
        {
            Client.findAllDgv(dgv, baseUrl + "/search?planId=" + planId +
                    "&date=" + date + "&poId=" + poId + "&kitPoId=" + kitPoId + "&cityId=" + cityId +
                    "&districtId=" + districtId + "&centerId=" + centerId + "&qty=" + qty + "&d=" + d);
        }
    }
}
