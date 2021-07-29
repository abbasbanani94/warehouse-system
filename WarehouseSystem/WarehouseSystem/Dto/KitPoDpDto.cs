using Newtonsoft.Json;

namespace WarehouseSystem
{
    class KitPoDpDto
    {
        public string dateReceived { get; set; }
        public string batchNo { get; set; }
        public string description { get; set; }
        public string manDate { get; set; }
        public string expDate { get; set; }
        public string kitType { get; set; }
        public int totalQty { get; set; }
        public int inventory { get; set; }

        public KitPoDpDto(string dateReceived, string batchNo, string description, string manDate, string expDate,
            string kitType, int totalQty, int inventory)
        {
            this.dateReceived = dateReceived;
            this.batchNo = batchNo;
            this.description = description;
            this.manDate = manDate;
            this.expDate = expDate;
            this.kitType = kitType;
            this.totalQty = totalQty;
            this.inventory = inventory;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
