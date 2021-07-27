using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class User
    {
        static string baseUrl = "/users";
        public static string userId = "1";
        public static string name = "Abbas";
        public static string role = "Admin";

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
    }
}
