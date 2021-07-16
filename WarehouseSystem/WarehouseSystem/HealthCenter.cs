﻿using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Data;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class HealthCenter
    {
        internal static async void findCitiesCombo(ComboBox cmbCity)
        {
            try
            {
                cmbCity.DataSource = null;
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/cities/combo");
                List<CityComboDto> itemList = JsonConvert.DeserializeObject<List<CityComboDto>>(response);
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

        internal static async void findAllHealthCenters(DataGridView dgv)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/healthCenters");
                DataTable dt = (DataTable)JsonConvert.DeserializeObject(response, (typeof(DataTable)));
                dgv.DataSource = dt;
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
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
                    List<DistrictComboDto> itemList = JsonConvert.DeserializeObject<List<DistrictComboDto>>(response);
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

        internal static bool saveHealthCenter(CenterSaveDto dto)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PostAsJsonAsync("/healthCenters", dto);
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

        internal static bool editHealthCenter(CenterEditDto dto,string id)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.PutAsJsonAsync("/healthCenters/" + id, dto);
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

        internal static bool deleteHealthCenter(string id)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.DeleteAsync("/healthCenters/" + id);
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

        internal static async void searchHealthCenter(string city,string district, string enName,
            string arName, DataGridView dgv)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = await client.GetStringAsync("/healthCenters/search?cityName=" + city + 
                    "&districtName=" + district + "&enName=" + enName + "&arName=" + arName);
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
