using Newtonsoft.Json;
using System;

namespace WarehouseSystem
{
    class KitDetailSaveDto
    {
        public string boxNo { get; set; }
        public string itemName { get; set; }
        public decimal minTemp { get; set; }
        public decimal maxTemp { get; set; }
        public string description { get; set; }
        public string packaging { get; set; }
        public string packsPerBox { get; set; }
        public string piecesPerPack { get; set; }
        public string expDate { get; set; }

        public KitDetailSaveDto (string boxNo,string itemName,string minTemp,string maxTemp,string description,
            string packaging,string packsPerBox,string piecesPerPack,string expDate)
        {
            this.boxNo = boxNo;
            this.itemName = itemName;
            if (minTemp == "")
                this.minTemp = 0;
            else
                this.minTemp = Convert.ToDecimal(minTemp);
            if (maxTemp == "")
                this.maxTemp = 0;
            else
                this.maxTemp = Convert.ToDecimal(maxTemp);
            this.description = description;
            this.packaging = packaging;
            this.packsPerBox = packsPerBox;
            this.piecesPerPack = piecesPerPack;
            this.expDate = expDate;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
