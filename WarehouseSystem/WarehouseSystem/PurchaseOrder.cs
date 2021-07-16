using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class PurchaseOrder
    {
        internal static async void findAllPoCombo(ComboBox cmbPoNo)
        {
            try
            {
                cmbPoNo.DataSource = null;
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/po");
                List<PoCombo> poList = (List<PoCombo>)JsonConvert.DeserializeObject<List<PoCombo>>(response);
                cmbPoNo.DataSource = poList;
                cmbPoNo.DisplayMember = "no";
                cmbPoNo.ValueMember = "id";
                cmbPoNo.ResetText();
            }
            catch(Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }

        internal static async void findAllCountries(ComboBox cmbCountry)
        {
            try
            {
                cmbCountry.DataSource = null;
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/po/countries");
                List<ComboDto> poList = JsonConvert.DeserializeObject<List<ComboDto>>(response);
                cmbCountry.DataSource = poList;
                cmbCountry.DisplayMember = "name";
                cmbCountry.ValueMember = "id";
                cmbCountry.ResetText();
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }
    }
}
