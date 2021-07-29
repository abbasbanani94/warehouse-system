using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WarehouseSystem
{
    class ItemPoSearchDto
    {
        public string poId { get; set; }
        public string dateReceived { get; set; }
        public string itemId { get; set; }
        public string minTemp { get; set; }
        public string maxTemp { get; set; }
        public string description { get; set; }
        public string manDate { get; set; }
        public string expDate { get; set; }
        public string country { get; set; }
        public string batch { get; set; }
        public string packaging { get; set; }
        public string pallets { get; set; }
        public string boxes { get; set; }
        public string packs { get; set; }
        public string totalQty { get; set; }
        public string location { get; set; }
        public bool rec { get; set; }
        public bool man { get; set; }
        public bool exp { get; set; }

        public ItemPoSearchDto (string poId,string dateReceived,string itemId,string minTemp,string maxTemp,
            string description,string manDate,string expDate,string country,string batch,string packaging,
            string pallets,string boxes,string packs,string totalQty,string location,bool rec,bool man,
            bool exp)
        {
            this.poId = poId;
            this.dateReceived = dateReceived;
            this.itemId = itemId;
            this.minTemp = minTemp;
            this.maxTemp = maxTemp;
            this.description = description;
            this.manDate = manDate;
            this.expDate = expDate;
            this.country = country;
            this.batch = batch;
            this.packaging = packaging;
            this.pallets = pallets;
            this.boxes = boxes;
            this.packs = packs;
            this.totalQty = totalQty;
            this.location = location;
            this.rec = rec;
            this.man = man;
            this.exp = exp;
        }
    }
}
