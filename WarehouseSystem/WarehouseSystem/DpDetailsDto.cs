using Newtonsoft.Json;

namespace WarehouseSystem
{
    class DpDetailsDto
    {
        public int id { get; set; }
        public string enName { get; set; }
        public string arName { get; set; }
        public string dDate { get; set; }

        public DpDetailsDto (int id,string enName,string arName,string dDate)
        {
            this.id = id;
            this.enName = enName;
            this.arName = arName;
            this.dDate = dDate;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
