using Newtonsoft.Json;

namespace WarehouseSystem
{
    class UserSaveDto
    {
        public string roleId { get; set; }
        public string name { get; set; }
        public string username { get; set; }
        public string password { get; set; }
        public string confirm { get; set; }

        public UserSaveDto ()
        {
            this.roleId = "";
            this.name = "";
            this.username = "";
            this.password = "";
            this.confirm = "";
        }

        public UserSaveDto (string roleId,string name,string username,string password,string confirm)
        {
            this.roleId = roleId;
            this.name = name;
            this.username = username;
            this.password = password;
            this.confirm = confirm;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
