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

        internal static async void findDpDetails(string id,TextBox arName,DateTimePicker date)
        {
            if (id.IndexOf('{') == -1)
            {
                try
                {
                    HttpClient client = Client.getHttpClient();
                    var response = await client.GetStringAsync("/dp/details/" + id);
                    DpDetailsDto dto = JsonConvert.DeserializeObject<DpDetailsDto>(response);
                    arName.Text = dto.arName;
                    date.Value = Convert.ToDateTime(dto.dDate).Date;
                }
                catch (Exception ex)
                {
                    Msg.errorMsg(ex.Message.ToString(), "Error");
                }
            }
        }

        internal static async void findDpDetails(string id, TextBox arName, TextBox date)
        {
            if (id.IndexOf('{') == -1)
            {
                try
                {
                    HttpClient client = Client.getHttpClient();
                    var response = await client.GetStringAsync("/dp/details/" + id);
                    DpDetailsDto dto = JsonConvert.DeserializeObject<DpDetailsDto>(response);
                    arName.Text = dto.arName;
                    date.Text = dto.dDate;
                }
                catch (Exception ex)
                {
                    Msg.errorMsg(ex.Message.ToString(), "Error");
                }
            }
        }
    }
}
