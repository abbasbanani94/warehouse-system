using Newtonsoft.Json;

namespace WarehouseSystem
{
    class PasswordDto
    {
        public string username { get; set; }
        public string old { get; set; }
        public string password { get; set; }
        public string confirm { get; set; }
        public PasswordDto (string username,string old,string password,string confirm)
        {
            this.username = username;
            this.old = old;
            this.password = password;
            this.confirm = confirm;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
