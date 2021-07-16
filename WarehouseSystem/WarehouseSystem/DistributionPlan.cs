using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class DistributionPlan
    {
        internal static async void findAllDpCombo(ComboBox cmb)
        {
            try
            {
                cmb.DataSource = null;
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/dp/combo");
                List<ComboDto> poList = JsonConvert.DeserializeObject<List<ComboDto>>(response);
                cmb.DataSource = poList;
                cmb.DisplayMember = "name";
                cmb.ValueMember = "id";
                cmb.ResetText();
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }
    }
}
