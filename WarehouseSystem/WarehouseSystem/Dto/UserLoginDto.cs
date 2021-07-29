using Newtonsoft.Json;

namespace WarehouseSystem
{
    class UserLoginDto
    {
        public string id { get; set; }
        public string roleName { get; set; }
        public string name { get; set; }
        public UserLoginDto (string id,string roleName,string name)
        {
            this.id = id;
            this.roleName = roleName;
            this.name = name;
        }
        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
