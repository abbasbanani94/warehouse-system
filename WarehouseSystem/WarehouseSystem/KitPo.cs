using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class KitPo
    {
        static string baseUrl = "/kitPo";
        internal static void findKitPoDgv(DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl);
        }

        internal static bool saveKitPo(KitPoSaveDto dto)
        {
            return Client.saveRequest(baseUrl, dto);
        }

        internal static bool editKitPo(KitPoSaveDto dto)
        {
            return Client.editRequest(baseUrl + "/" + dto.kitPoId, dto);
        }

        internal static bool deleteKitPo(KitPoSaveDto dto)
        {
            return Client.deleteRequest(baseUrl + "/" + dto.kitPoId);
        }

        internal static void searchKitPoDgv(KitPoSearchDto dto, DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/search?poId=" + dto.poId +
                    "&dateReceived=" + dto.dateReceived + "&kitId=" + dto.kitId + "&minTemp=" +
                    dto.minTemp + "&maxTemp=" + dto.maxTemp + "&description=" + dto.description +
                    "&manDate=" + dto.manDate + "&expDate=" + dto.expDate + "&country=" + dto.country +
                    "&batchNo=" + dto.batchNo + "&location=" + dto.location + "&palletsQty=" + dto.palletsQty +
                    "&boxesPallets=" + dto.boxesPallets + "&kitsPallet=" + dto.kitsPallet +
                    "&totalQty=" + dto.totalQty + "&kitType=" + dto.kitType + "&rec=" + dto.rec +
                    "&man=" + dto.man + "&exp=" + dto.exp);
        }

        internal static async void findKitsComboByPo(ComboBox cmbKit, string poId)
        {
            if (poId.IndexOf('{') == -1)
            {
                try
                {
                    cmbKit.DataSource = null;
                    HttpClient client = Client.getHttpClient();
                    var response = await client.GetStringAsync(baseUrl + "/combo/" + poId);
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
                    var response = await client.GetStringAsync(baseUrl + "/kitPoDp/" + kitPoId);
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
                var response = await client.GetStringAsync(baseUrl + "/name/" + id);
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
