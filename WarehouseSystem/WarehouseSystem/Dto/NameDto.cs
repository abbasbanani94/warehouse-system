using Newtonsoft.Json;

namespace WarehouseSystem
{
    class NameDto
    {
        public string name { get; set; }
        public NameDto (string name)
        {
            this.name = name;
        }
        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
