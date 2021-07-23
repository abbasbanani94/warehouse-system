using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Data;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class KitPo
    {
        internal static async void findKitPoDgv(DataGridView dgv)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/kitPo");
                DataTable dt = (DataTable)JsonConvert.DeserializeObject(response, (typeof(DataTable)));
                dgv.DataSource = dt;
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }

        internal static bool saveKitPo(KitPoSaveDto dto)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PostAsJsonAsync("/kitPo", dto);
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

        internal static bool editKitPo(KitPoSaveDto dto)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PutAsJsonAsync("/kitPo/" + dto.kitPoId, dto);
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

        internal static bool deleteKitPo(KitPoSaveDto dto)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.DeleteAsync("/kitPo/" + dto.kitPoId);
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

        internal static async void searchKitPoDgv(KitPoSearchDto dto, DataGridView dgv)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/kitPo/search?poId=" + dto.poId +
                    "&dateReceived=" + dto.dateReceived + "&kitId=" + dto.kitId + "&minTemp=" +
                    dto.minTemp + "&maxTemp=" + dto.maxTemp + "&description=" + dto.description +
                    "&manDate=" + dto.manDate + "&expDate=" + dto.expDate + "&country=" + dto.country +
                    "&batchNo=" + dto.batchNo + "&location=" + dto.location + "&palletsQty=" + dto.palletsQty +
                    "&boxesPallets=" + dto.boxesPallets + "&kitsPallet=" + dto.kitsPallet + 
                    "&totalQty=" + dto.totalQty + "&kitType=" + dto.kitType + "&rec=" + dto.rec + 
                    "&man=" + dto.man + "&exp=" + dto.exp);
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

        internal static async void findKitsComboByPo(ComboBox cmbKit, string poId)
        {
            if (poId.IndexOf('{') == -1)
            {
                try
                {
                    cmbKit.DataSource = null;
                    HttpClient client = Client.getHttpClient();
                    var response = await client.GetStringAsync("/kitPo/combo/" + poId);
                    List<ComboDto> itemList = JsonConvert.DeserializeObject<List<ComboDto>>(response);
                    cmbKit.DataSource = itemList;
                    cmbKit.DisplayMember = "name";
                    cmbKit.ValueMember = "id";
                    cmbKit.ResetText();
                }
                catch (Exception ex)
                {
                    Msg.errorMsg(ex.Message.ToString(), "Error");
                }
            }
        }

        internal static async void findKitPoDpDetailsById(string kitPoId, TextBox recDate, TextBox batch,
            TextBox description, TextBox manDate, TextBox expDate, TextBox kitType, TextBox totalQty,
            TextBox inventory)
        {
            if (kitPoId.IndexOf('{') == -1)
            {
                try
                {
                    HttpClient client = Client.getHttpClient();
                    var response = await client.GetStringAsync("/kitPo/kitPoDp/" + kitPoId);
                    KitPoDpDto dto = JsonConvert.DeserializeObject<KitPoDpDto>(response);
                    recDate.Text = dto.dateReceived;
                    batch.Text = dto.batchNo;
                    description.Text = dto.description;
                    manDate.Text = dto.manDate;
                    expDate.Text = dto.expDate;
                    kitType.Text = dto.kitType;
                    totalQty.Text = dto.totalQty.ToString();
                    inventory.Text = dto.inventory.ToString();
                }
                catch (Exception ex)
                {
                    Msg.errorMsg(ex.Message.ToString(), "Error");
                }
            }
        }

        internal static async void findKitNameByKitPoId(string id,TextBox name)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/kitPo/name/" + id);
                NameDto dto = JsonConvert.DeserializeObject<NameDto>(response);
                name.Text = dto.name;
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }
    }
}
