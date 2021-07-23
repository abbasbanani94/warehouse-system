using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WarehouseSystem
{
    class KitPoSearchDto
    {
        public string poId { get; set; }
        public string dateReceived { get; set; }
        public string kitId { get; set; }
        public string minTemp { get; set; }
        public string maxTemp { get; set; }
        public string description { get; set; }
        public string manDate { get; set; }
        public string expDate { get; set; }
        public string country { get; set; }
        public string batchNo { get; set; }
        public string location { get; set; }
        public string palletsQty { get; set; }
        public string boxesPallets { get; set; }
        public string kitsPallet { get; set; }
        public string totalQty { get; set; }
        public string kitType { get; set; }

        public bool rec { get; set; }
        public bool man { get; set; }
        public bool exp { get; set; }

        public KitPoSearchDto (string poId,string dateReceived,string kitId,string minTemp,string maxTemp,
            string description,string manDate,string expDate,string country,string batchNo,string location,
            string palletsQty,string boxesPallets,string kitsPallet,string totalQty,string kitType,
            bool rec,bool man,bool exp)
        {
            this.poId = poId;
            this.dateReceived = dateReceived;
            this.kitId = kitId;
            this.minTemp = minTemp;
            this.maxTemp = maxTemp;
            this.description = description;
            this.manDate = manDate;
            this.expDate = expDate;
            this.country = country;
            this.batchNo = batchNo;
            this.location = location;
            this.palletsQty = palletsQty;
            this.boxesPallets = boxesPallets;
            this.kitsPallet = kitsPallet;
            this.totalQty = totalQty;
            this.kitType = kitType;
            this.rec = rec;
            this.man = man;
            this.exp = exp;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
