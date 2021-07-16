using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class Item
    {
        internal static async void findAllItemsCombo(ComboBox cmbItem)
        {
            try
            {
                cmbItem.DataSource = null;
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/items/combo");
                List<ComboDto> itemList = JsonConvert.DeserializeObject<List<ComboDto>>(response);
                cmbItem.DataSource = itemList;
                cmbItem.DisplayMember = "name";
                cmbItem.ValueMember = "id";
                cmbItem.ResetText();
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }

        internal static async void findItemBoxesById(string id, TextBox txtMin, TextBox txtMax, TextBox txtDesc)
        {
            if(id.IndexOf('{') == -1)
            {
                try
                {
                    HttpClient client = Client.getHttpClient();
                    var response = await client.GetStringAsync("/items/" + id);
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
