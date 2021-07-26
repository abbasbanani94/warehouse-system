using System;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class ItemDp
    {
        static string baseUrl = "/itemDp";
        internal static void findItemDpDgv(DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl);
        }

        internal static bool saveItemDp(ItemDpSaveDto dto)
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

        internal static bool editItemDp(string id, ItemDpSaveDto dto)
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

        internal static bool deleteItemDp(string id)
        {
            return Client.deleteRequest(baseUrl + "/" + id);
        }

        internal static void searchItemDpDgv(DataGridView dgv,string planId,string date,string poId,
            string itemPoId,string cityId,string districtId,string centerId,string qty,bool d)
        {
            Client.findAllDgv(dgv, baseUrl + "/search?planId=" + planId +
                    "&date=" + date + "&poId=" + poId + "&itemPoId=" + itemPoId + "&cityId=" + cityId +
                    "&districtId=" + districtId + "&centerId=" + centerId + "&qty=" + qty +
                    "&d=" + d);
        }
    }
}