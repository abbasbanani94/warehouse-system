using Newtonsoft.Json;

namespace WarehouseSystem
{
    class PlanSaveDto
    {
        public string enName { get; set; }
        public string arName { get; set; }
        public string date { get; set; }

        public PlanSaveDto (string enName,string arName,string date)
        {
            this.enName = enName;
            this.arName = arName;
            this.date = date;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
