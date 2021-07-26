using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class ItemPo
    {
        static string baseUrl = "/itemPo";
        internal static void findItemPoDgv(DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl);
        }

        internal static bool saveItemPo(ItemPoSaveDto dto)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PostAsJsonAsync(baseUrl, dto);
                if (response.Result.StatusCode == System.Net.HttpStatusCode.OK)
                    return true;
                else
                {
                    Msg.errorMsg(response.Result.Content.ReadAsStringAsync().Result, "Error");
                }
            }
            catch(Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
            return false;
        }

        internal static bool editItemPo(ItemPoSaveDto dto)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PutAsJsonAsync(baseUrl + "/" + dto.itemPoId, dto);
                if (response.Result.StatusCode == System.Net.HttpStatusCode.OK)
                    return true;
                else
                {
                    Msg.errorMsg(response.Result.Content.ReadAsStringAsync().Result, "Error");
                }
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
            return false;
        }

        internal static async void findItemsComboByPo(ComboBox cmbItem, string poId)
        {
            if (poId.IndexOf('{') == -1)
            {
                try
                {
                    cmbItem.DataSource = null;
                    HttpClient client = Client.getHttpClient();
                    var response = await client.GetStringAsync(baseUrl + "/combo/" + poId);
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
        }

        internal static async void findItemPoDpDetailsById(string itemPoId, TextBox recDate, TextBox batch,
            TextBox description, TextBox manDate, TextBox expDate, TextBox packaging, TextBox totalQty,
            TextBox inventory)
        {
            if (itemPoId.IndexOf('{') == -1)
            {
                try
                {
                    HttpClient client = Client.getHttpClient();
                    var response = await client.GetStringAsync(baseUrl + "/itemPoDp/" + itemPoId);
                    ItemPoDpDto dto = JsonConvert.DeserializeObject<ItemPoDpDto>(response);
                    recDate.Text = dto.dateReceived;
                    batch.Text = dto.batchNo;
                    description.Text = dto.description;
                    manDate.Text = dto.manDate;
                    expDate.Text = dto.expDate;
                    packaging.Text = dto.packaging;
                    totalQty.Text = dto.totalQty.ToString();
                    inventory.Text = dto.inventory.ToString();
                }
                catch (Exception ex)
                {
                    Msg.errorMsg(ex.Message.ToString(), "Error");
                }
            }
        }

        internal static bool deleteItemPo(string itemPoId)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.DeleteAsync(baseUrl + "/" + itemPoId);
                if (response.Result.StatusCode == System.Net.HttpStatusCode.OK)
                    return true;
                else
                {
                    Msg.errorMsg(response.Result.Content.ReadAsStringAsync().Result, "Error");
                }
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
            return false;
        }

        internal static void searchItemPoDgv(ItemPoSearchDto dto,DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/search?poId=" + dto.poId +
                    "&dateReceived=" + dto.dateReceived + "&itemId=" + dto.itemId + "&minTemp=" +
                    dto.minTemp + "&maxTemp=" + dto.maxTemp + "&description=" + dto.description +
                    "&manDate=" + dto.manDate + "&expDate=" + dto.expDate + "&country=" + dto.country +
                    "&batch=" + dto.batch + "&packaging=" + dto.packaging + "&pallets=" + dto.pallets +
                    "&boxes=" + dto.boxes + "&packs=" + dto.packs + "&totalQty=" + dto.totalQty +
                    "&location=" + dto.location + "&rec=" + dto.rec + "&man=" + dto.man + "&exp=" + dto.exp);
        }
    }
}
