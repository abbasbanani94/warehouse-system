using Newtonsoft.Json;

namespace WarehouseSystem
{
    class ItemTbDto
    {
        public int id { get; set; }
        public string name { get; set; }
        public decimal minTemp { get; set; }
        public decimal maxTemp { get; set; }
        public string description { get; set; }

        public ItemTbDto (int id,string name,decimal min,decimal max,string description)
        {
            this.id = id;
            this.name = name;
            this.minTemp = min;
            this.maxTemp = max;
            this.description = description;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
