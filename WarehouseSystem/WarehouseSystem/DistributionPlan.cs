using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class DistributionPlan
    {
        static string baseUrl = "/dp";
        internal static async void findAllDpCombo(ComboBox cmb)
        {
            try
            {
                cmb.DataSource = null;
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync(baseUrl + "/combo");
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
                    var response = await client.GetStringAsync(baseUrl + "/details/" + id);
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
                    var response = await client.GetStringAsync(baseUrl + "/details/" + id);
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

        internal static void findAllDpDgv(DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/dgv");
        }

        internal static bool saveDistributionPlan(PlanSaveDto dto)
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
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
            return false;
        }

        internal static bool editDistributionPlan(string id,PlanSaveDto dto)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PutAsJsonAsync(baseUrl + "/" + id, dto);
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

        internal static bool deleteDistributionPlan(string id)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.DeleteAsync(baseUrl + "/" + id);
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

        internal static  void searchDistributionPlan(string enName,string arName,string date,bool d,
            DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/search?enName=" + enName + "&arName=" +
                    arName + "&date=" + date + "&d=" + d);
        }
    }
}
