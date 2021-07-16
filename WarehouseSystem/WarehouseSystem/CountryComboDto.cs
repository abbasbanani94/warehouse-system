using Newtonsoft.Json;

namespace WarehouseSystem
{
    class CountryComboDto
    {
        public int id { get; set; }
        public string name { get; set; }

        public CountryComboDto (int id,string name)
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
