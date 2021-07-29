using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class HealthCenter
    {
        static string baseUrl = "/healthCenters";
        internal static async void findCitiesCombo(ComboBox cmbCity)
        {
            try
            {
                cmbCity.DataSource = null;
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/cities/combo");
                List<ComboDto> itemList = JsonConvert.DeserializeObject<List<ComboDto>>(response);
                cmbCity.DataSource = itemList;
                cmbCity.DisplayMember = "name";
                cmbCity.ValueMember = "id";
                cmbCity.ResetText();
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
        }

        internal static void findAllHealthCenters(DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl);
        }

        internal static async void findDistrictsCombo(ComboBox cmbDistrict,string cityId)
        {
            if (cityId.IndexOf('{') == -1)
            {
                try
                {
                    cmbDistrict.DataSource = null;
                    HttpClient client = Client.getHttpClient();
                    var response = await client.GetStringAsync("/districts/combo/" + cityId);
                    List<ComboDto> itemList = JsonConvert.DeserializeObject<List<ComboDto>>(response);
                    cmbDistrict.DataSource = itemList;
                    cmbDistrict.DisplayMember = "name";
                    cmbDistrict.ValueMember = "id";
                    cmbDistrict.ResetText();
                }
                catch (Exception ex)
                {
                    Msg.errorMsg(ex.Message.ToString(), "Error");
                }
            }
            
        }

        internal static async void findCentersCombo(ComboBox cmbCenter, string districtId)
        {
            if (districtId.IndexOf('{') == -1)
            {
                try
                {
                    cmbCenter.DataSource = null;
                    HttpClient client = Client.getHttpClient();
                    var response = await client.GetStringAsync(baseUrl + "/combo/" + districtId);
                    List<ComboDto> itemList = JsonConvert.DeserializeObject<List<ComboDto>>(response);
                    cmbCenter.DataSource = itemList;
                    cmbCenter.DisplayMember = "name";
                    cmbCenter.ValueMember = "id";
                    cmbCenter.ResetText();
                }
                catch (Exception ex)
                {
                    Msg.errorMsg(ex.Message.ToString(), "Error");
                }
            }
        }

        internal static bool saveHealthCenter(CenterSaveDto dto)
        {
            return Client.saveRequest(baseUrl, dto);
        }

        internal static bool editHealthCenter(CenterEditDto dto,string id)
        {
            return Client.editRequest(baseUrl + "/" + id, dto);
        }

        internal static bool deleteHealthCenter(string id)
        {
            return Client.deleteRequest(baseUrl + "/" + id);
        }

        internal static void searchHealthCenter(string city,string district, string enName,string arName, 
            DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl + "/search?cityName=" + city +
                    "&districtName=" + district + "&enName=" + enName + "&arName=" + arName);
        }
    }
}
