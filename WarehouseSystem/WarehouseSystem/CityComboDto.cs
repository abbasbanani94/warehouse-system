using Newtonsoft.Json;

namespace WarehouseSystem
{
    class CityComboDto
    {
        public int id { get; set; }
        public string name { get; set; }

        public CityComboDto (int id,string name)
        {
            this.id = id;
            this.name = name;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
