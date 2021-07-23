using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Data;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class ItemPo
    {
        internal static async void findItemPoDgv(DataGridView dgv)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/itemPo");
                DataTable dt = (DataTable)JsonConvert.DeserializeObject(response, (typeof(DataTable)));
                dgv.DataSource = dt;
            }
            catch(Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }

        internal static bool saveItemPo(ItemPoSaveDto dto)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PostAsJsonAsync("/itemPo", dto);
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
                var response = client.PutAsJsonAsync("/itemPo/" + dto.itemPoId, dto);
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
                    var response = await client.GetStringAsync("/itemPo/combo/" + poId);
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
                    var response = await client.GetStringAsync("/itemPo/itemPoDp/" + itemPoId);
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
                var response = client.DeleteAsync("/itemPo/" + itemPoId);
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

        internal static async void searchItemPoDgv(ItemPoSearchDto dto,DataGridView dgv)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/itemPo/search?poId=" + dto.poId + 
                    "&dateReceived=" + dto.dateReceived + "&itemId=" + dto.itemId + "&minTemp=" +
                    dto.minTemp + "&maxTemp=" + dto.maxTemp + "&description=" + dto.description + 
                    "&manDate=" + dto.manDate + "&expDate=" + dto.expDate + "&country=" + dto.country + 
                    "&batch=" + dto.batch + "&packaging=" + dto.packaging + "&pallets=" + dto.pallets + 
                    "&boxes=" + dto.boxes + "&packs=" + dto.packs + "&totalQty=" + dto.totalQty + 
                    "&location=" + dto.location + "&rec=" + dto.rec + "&man=" + dto.man + "&exp=" + dto.exp);
                DataTable dt = (DataTable)JsonConvert.DeserializeObject(response, (typeof(DataTable)));
                dgv.DataSource = dt;
                if (dgv.Rows.Count == 0)
                    Msg.errorMsg("No data found", "NO DATA");
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }
    }
}
