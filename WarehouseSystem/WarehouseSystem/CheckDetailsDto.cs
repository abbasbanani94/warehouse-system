using Newtonsoft.Json;

namespace WarehouseSystem
{
    class CheckDetailsDto
    {
        public string id { get; set; }
        public string type { get; set; }
        public string date { get; set; }
        public string notes { get; set; }

        public CheckDetailsDto (string id,string type,string date,string notes)
        {
            this.id = id;
            this.type = type;
            this.date = date;
            this.notes = notes;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
