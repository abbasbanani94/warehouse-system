using Newtonsoft.Json;

namespace WarehouseSystem
{
    class ItemComboDto
    {
        public int id { get; set; }
        public string name { get; set; }

        public ItemComboDto (int id,string name)
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
