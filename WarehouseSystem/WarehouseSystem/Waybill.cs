using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class Waybill
    {
        internal static async void findAllWaybillCombo(ComboBox cmbWbNo)
        {
            try
            {
                cmbWbNo.DataSource = null;
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/waybills/combo");
                List<PoCombo> poList = (List<PoCombo>)JsonConvert.DeserializeObject<List<PoCombo>>(response);
                cmbWbNo.DataSource = poList;
                cmbWbNo.DisplayMember = "no";
                cmbWbNo.ValueMember = "id";
                cmbWbNo.ResetText();
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }
    }
}
