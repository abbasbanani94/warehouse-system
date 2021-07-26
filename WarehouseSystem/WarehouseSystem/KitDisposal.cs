using System.Windows.Forms;

namespace WarehouseSystem
{
    class KitDisposal
    {
        static string baseUrl = "/kitDisposals";
        internal static void findAllKitDisposalsDgv(string disposalId, DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/" + disposalId);
        }

        internal static bool saveKitDisposal(string disposalId, KitDisposalSaveDto dto)
        {
            return Client.saveRequest(baseUrl + "/" + disposalId, dto);
        }

        internal static bool editKitDisposal(string disposalId, string kitDisposalId, KitDisposalSaveDto dto)
        {
            return Client.editRequest(baseUrl + "/" + disposalId + "/" + kitDisposalId, dto);
        }

        internal static bool deleteItemDisposal(string id)
        {
            return Client.deleteRequest(baseUrl + "/" + id);
        }

        internal static void searchKitDisposal(string disposalId, string poId, string kitPoId,
            string qty, DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/search?disposalId=" + disposalId +
                    "&poId=" + poId + "&kitPoId=" + kitPoId + "&qty=" + qty);
        }
    }
}
