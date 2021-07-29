using Newtonsoft.Json;
using System;

namespace WarehouseSystem
{
    class ItemPoSaveDto
    {
        public string poId { get; set; }
        public string poNo { get; set; }
        public string dateReceived { get; set; }
        public string itemName { get; set; }
        public decimal minTemp { get; set; }
        public decimal maxTemp { get; set; }
        public string itemDescription { get; set; }
        public string manDate { get; set; }
        public string expDate { get; set; }
        public string country { get; set; }
        public string batchNo { get; set; }
        public string packaging { get; set; }
        public int palletsQty { get; set; }
        public int boxesPerPallet { get; set; }
        public int packsPerBox { get; set; }
        public int piecesPerPack { get; set; }
        public int totalQty { get; set; }
        public string location { get; set; }
        public string itemPoId { get; set; }

        public ItemPoSaveDto (string poId, string poNo, string dateReceived,string itemName,string minTemp,
            string maxTemp,string itemDescription,string manDate,string expDate,string country,string batchNo,
            string packaging, string palletsQty, string boxesPerPallet, string packsPerBox, string piecesPerPack,
            string totalQty, string itemPoId,string location)
        {
            this.poId = poId;
            this.poNo = poNo;
            this.dateReceived = dateReceived;
            this.itemName = itemName;
            this.minTemp = Convert.ToDecimal(minTemp);
            this.maxTemp = Convert.ToDecimal(maxTemp);
            this.itemDescription = itemDescription;
            this.manDate = manDate;
            this.expDate = expDate;
            this.country = country;
            this.batchNo = batchNo;
            this.packaging = packaging;
            this.palletsQty = Convert.ToInt32(palletsQty);
            this.boxesPerPallet = Convert.ToInt32(boxesPerPallet);
            this.packsPerBox = Convert.ToInt32(packsPerBox);
            this.piecesPerPack = Convert.ToInt32(piecesPerPack);
            this.totalQty = Convert.ToInt32(totalQty);
            this.itemPoId = itemPoId;
            this.location = location;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
