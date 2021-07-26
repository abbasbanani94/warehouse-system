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
            return Client.saveRequest(baseUrl + "/" + kitPoId, dto);
        }

        internal static bool editKitDetails(string kitPoId,string detailId,KitDetailSaveDto dto)
        {
            return Client.editRequest(baseUrl + "/" + kitPoId + "/" + detailId, dto);
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
