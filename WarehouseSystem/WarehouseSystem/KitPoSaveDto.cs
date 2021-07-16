using Newtonsoft.Json;
using System;

namespace WarehouseSystem
{
    class KitPoSaveDto
    {
        public string poId { get; set;}
        public string poNo { get; set; }
        public string dateReceived { get; set; }
        public string kitName { get; set; }
        public decimal minTemp { get; set; }
        public decimal maxTemp { get; set; }
        public string kitDescription { get; set; }
        public string manDate { get; set; }
        public string expDate { get; set; }
        public string country { get; set; }
        public string batchNo { get; set; }
        public int palletsQty { get; set; }
        public int boxesPerPallet { get; set; }
        public int kitsPerPallet { get; set; }
        public int totalQty { get; set; }
        public string location { get; set; }
        public string kitPoId { get; set; }
        public string kitType { get; set; }
        

        public KitPoSaveDto(string poId, string poNo, string dateReceived, string kitName, string minTemp,
            string maxTemp, string kitDescription, string manDate, string expDate, string country, string batchNo,
            string palletsQty, string boxesPerPallet, string totalQty, string kitPoId, string location,
            string kitsPerPallet,string kitType)
        {
            this.poId = poId;
            this.poNo = poNo;
            this.dateReceived = dateReceived;
            this.kitName = kitName;
            this.minTemp = Convert.ToDecimal(minTemp);
            this.maxTemp = Convert.ToDecimal(maxTemp);
            this.kitDescription = kitDescription;
            this.manDate = manDate;
            this.expDate = expDate;
            this.country = country;
            this.batchNo = batchNo;
            this.palletsQty = Convert.ToInt32(palletsQty);
            this.boxesPerPallet = Convert.ToInt32(boxesPerPallet);
            this.totalQty = Convert.ToInt32(totalQty);
            this.kitPoId = kitPoId;
            this.location = location;
            this.kitsPerPallet = Convert.ToInt32(kitsPerPallet);
            this.kitType = kitType;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
