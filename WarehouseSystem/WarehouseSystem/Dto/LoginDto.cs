using Newtonsoft.Json;

namespace WarehouseSystem
{
    class LoginDto
    {
        public string username { get; set; }
        public string password { get; set; }
        public LoginDto (string username,string password)
        {
            this.username = username;
            this.password = password;
        }
        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
