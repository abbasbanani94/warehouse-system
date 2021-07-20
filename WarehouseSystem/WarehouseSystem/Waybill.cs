using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Data;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class Waybill
    {
        internal static async void findAllWaybillsDgv(DataGridView dgv)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/waybills");
                DataTable dt = (DataTable)JsonConvert.DeserializeObject(response, (typeof(DataTable)));
                dgv.DataSource = dt;
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }

        internal static bool saveWaybill(WbSaveDto dto)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PostAsJsonAsync("/waybills", dto);
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

        internal static bool editWaybill(string id, WbSaveDto dto)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PutAsJsonAsync("/waybills/" + id, dto);
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

        internal static bool deleteWaybill(string id)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.DeleteAsync("/waybills/" + id);
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

        internal static async void searchWaybill(string wbNo,string wbDate,string boxes,string pallets,
            string cityId,string districtId,string centerId, DataGridView dgv)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/waybills/search?wbNo=" + wbNo +
                    "&wbDate=" + wbDate + "&boxes=" + boxes + "&pallets=" + pallets + "&cityId=" +
                    cityId + "&districtId=" + districtId + "&centerId=" + centerId);
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

        internal static async void findWaybillById(string wbId, TextBox wbNo, TextBox wbDate,
            TextBox boxes, TextBox pallets, TextBox city, TextBox district, TextBox center)
        {
            if (wbId.IndexOf('{') == -1)
            {
                try
                {
                    HttpClient client = Client.getHttpClient();
                    var response = await client.GetStringAsync("/waybills/" + wbId);
                    WbDetailsDto dto = JsonConvert.DeserializeObject<WbDetailsDto>(response);
                    wbNo.Text = dto.wbNo;
                    wbDate.Text = dto.wbDate;
                    boxes.Text = dto.boxes;
                    pallets.Text = dto.pallets;
                    city.Text = dto.city;
                    district.Text = dto.district;
                    center.Text = dto.center;
                }
                catch (Exception ex)
                {
                    Msg.errorMsg(ex.Message.ToString(), "Error");
                }
            }
        }

        internal static async void findItems(ListBox list, string wbId, string url)
        {
            if (wbId.IndexOf('{') == -1)
            {
                try
                {
                    list.Items.Clear();
                    HttpClient client = Client.getHttpClient();
                    var response = await client.GetStringAsync(url + wbId);
                    List<string> itemList = JsonConvert.DeserializeObject<List<string>>(response);
                    list.Items.AddRange(itemList.ToArray());
                }
                catch (Exception ex)
                {
                    Msg.errorMsg(ex.Message.ToString(), "Error");
                }
            }
        }
    }
}
