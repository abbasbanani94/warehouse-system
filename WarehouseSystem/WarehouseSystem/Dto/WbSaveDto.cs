using Newtonsoft.Json;
using System;

namespace WarehouseSystem
{
    class WbSaveDto
    {
        public string wbNo { get; set; }
        public string wbDate { get; set; }
        public int boxes { get; set; }
        public int pallets { get; set; }
        public string centerId { get; set; }

        public WbSaveDto (string wbNo,string wbDate,string boxes,string pallets,string centerId)
        {
            this.wbNo = wbNo;
            this.wbDate = wbDate;
            if (boxes == "")
                this.boxes = 0;
            else
                this.boxes = Convert.ToInt32(boxes);
            if (pallets == "")
                this.pallets = 0;
            else
                this.pallets = Convert.ToInt32(pallets);
            this.centerId = centerId;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
