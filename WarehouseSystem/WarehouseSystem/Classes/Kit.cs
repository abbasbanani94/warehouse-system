using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class Kit
    {
        static string baseUrl = "/kits";
        internal static async void findAllKitsCombo(ComboBox cmbKit)
        {
            try
            {
                cmbKit.DataSource = null;
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync(baseUrl + "/combo");
                List<ComboDto> kitList = JsonConvert.DeserializeObject<List<ComboDto>>(response);
                cmbKit.DataSource = kitList;
                cmbKit.DisplayMember = "name";
                cmbKit.ValueMember = "id";
                cmbKit.ResetText();
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }

        internal static async void findKitBoxesById(string id, TextBox txtMin, TextBox txtMax, TextBox txtDesc)
        {
            if (id.IndexOf('{') == -1)
            {
                try
                {
                    HttpClient client = Client.getHttpClient();
                    var response = await client.GetStringAsync(baseUrl + "/" + id);
                    ItemTbDto dto = JsonConvert.DeserializeObject<ItemTbDto>(response);
                    txtMin.Text = dto.minTemp.ToString();
                    txtMax.Text = dto.maxTemp.ToString();
                    txtDesc.Text = dto.description;
                }
                catch (Exception ex)
                {
                    Msg.errorMsg(ex.Message.ToString(), "Error");
                }
            }
        }
    }
}
