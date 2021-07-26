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
            return Client.saveRequest(baseUrl, dto);
        }

        internal static bool editKitDp(string id, KitDpSaveDto dto)
        {
            return Client.editRequest(baseUrl + "/" + id, dto);
        }

        internal static bool deleteKitDp(string id)
        {
            return Client.deleteRequest(baseUrl + "/" + id);
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
