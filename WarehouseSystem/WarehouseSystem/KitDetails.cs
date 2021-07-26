using System;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class KitDetails
    {
        static string baseUrl = "/kitDetails";
        internal static void findAllKitDetailsByKitPo(string kitPoId,DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/" + kitPoId);
        }

        internal static bool saveKitDetail(string kitPoId,KitDetailSaveDto dto)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PostAsJsonAsync(baseUrl + "/" + kitPoId, dto);
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

        internal static bool editKitDetails(string kitPoId,string detailId,KitDetailSaveDto dto)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PutAsJsonAsync(baseUrl + "/" + kitPoId + "/" + detailId, dto);
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

        internal static bool deleteKitDetail(string detailId)
        {
            return Client.deleteRequest(baseUrl + "/" + detailId);
        }

        internal static void searchKitDetails(string kitPoId,string boxNo,string minTemp,
            string maxTemp,string description,string packaging,string packs,string pieces,
            string expDate,string itemId,DataGridView dgv,bool exp)
        {
            Client.findAllDgv(dgv, baseUrl + "/search?kitPoId=" + kitPoId +
                    "&boxNo=" + boxNo + "&minTemp=" + minTemp + "&maxTemp=" + maxTemp +
                    "&description=" + description + "&packaging=" + packaging + "&packs=" + packs +
                    "&pieces=" + pieces + "&expDate=" + expDate + "&itemId=" + itemId + "&exp=" + exp);
        }
    }
}
