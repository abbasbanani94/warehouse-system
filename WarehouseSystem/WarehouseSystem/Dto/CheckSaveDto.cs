using Newtonsoft.Json;

namespace WarehouseSystem
{
    class CheckSaveDto
    {
        public string typeId { get; set; }
        public string notes { get; set; }
        public string date { get; set; }
        public CheckSaveDto (string typeId,string notes,string date)
        {
            this.typeId = typeId;
            this.notes = notes;
            this.date = date;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
