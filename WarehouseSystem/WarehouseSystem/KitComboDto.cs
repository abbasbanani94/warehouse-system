using Newtonsoft.Json;

namespace WarehouseSystem
{
    class KitComboDto
    {
        public int id { get; set; }
        public string name { get; set; }

        public KitComboDto(int id, string name)
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
