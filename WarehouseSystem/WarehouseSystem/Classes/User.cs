using Newtonsoft.Json;
using System;
using System.Net.Http;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class User
    {
        static string baseUrl = "/users";
        public static string userId = "";
        public static string name = "";
        public static string role = "";

        internal static void findAllUsers(DataGridView dgv)
        {
            Client.findAllDgv(dgv, baseUrl);
        }

        internal static bool saveUser(UserSaveDto dto)
        {
            return Client.saveRequest(baseUrl, dto);
        }

        internal static bool editUser(string id, UserSaveDto dto)
        {
            return Client.editRequest(baseUrl + "/" + id, dto);
        }

        internal static bool activateUser(string id)
        {
            return Client.editRequest(baseUrl + "/activate/" + id, new UserSaveDto());
        }

        internal static bool deactivateUser(string id)
        {
            return Client.editRequest(baseUrl + "/deactivate/" + id, new UserSaveDto());
        }

        internal static bool changePassword(PasswordDto dto)
        {
            return Client.editRequest(baseUrl + "/password", dto);
        }

        internal static bool login(LoginDto dto)
        {
            try
            {
                HttpClient client = Client.getHttpClient();
                var response = client.GetStringAsync(baseUrl + "/login?username=" + dto.username + 
                    "&password=" + dto.password);
                UserLoginDto d = JsonConvert.DeserializeObject<UserLoginDto>(response.Result);
                userId = d.id;
                role = d.roleName;
                name = d.name;
                return true;
            }
            catch (Exception ex)
            {
                Msg.errorMsg(ex.Message.ToString(), "Error");
            }
            return false;
        }
    }
}
