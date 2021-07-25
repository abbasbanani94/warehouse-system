using Newtonsoft.Json;

namespace WarehouseSystem
{
    class DisposalSaveDto
    {
        public string reason { get; set; }
        public string date { get; set; }
        public DisposalSaveDto (string reason,string date)
        {
            this.reason = reason;
            this.date = date;
        }
        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
