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
            return Client.saveRequest(baseUrl, dto);
        }

        internal static bool editItemDp(string id, ItemDpSaveDto dto)
        {
            return Client.editRequest(baseUrl + "/" + id, dto);
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