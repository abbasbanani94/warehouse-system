using System;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class ItemDisposal
    {
        static string baseUrl = "/itemDisposals";
        internal static void findAllItemDisposalsDgv(string disposalId,DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/" + disposalId);
        }

        internal static bool saveItemDisposal(string disposalId,ItemDisposalSaveDto dto)
        {
            return Client.saveRequest(baseUrl + "/" + disposalId, dto);
        }

        internal static bool editItemDisposal(string disposalId,string itemDisposalId,ItemDisposalSaveDto dto)
        {
            return Client.editRequest(baseUrl + "/" + disposalId + "/" + itemDisposalId, dto);
        }

        internal static bool deleteItemDisposal(string id)
        {
            return Client.deleteRequest(baseUrl + "/" + id);
        }

        internal static void searchItemDisposal(string disposalId,string poId,string itemPoId,
            string qty,DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/search?disposalId=" + disposalId +
                    "&poId=" + poId + "&itemPoId=" + itemPoId + "&qty=" + qty);
        }
    }
}
