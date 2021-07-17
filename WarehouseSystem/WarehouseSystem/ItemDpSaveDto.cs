using Newtonsoft.Json;
using System;

namespace WarehouseSystem
{
    class ItemDpSaveDto
    {
        public int planId { get; set; }
        public string enName { get; set; }
        public string arName { get; set; }
        public string planDate { get; set; }
        public int itemPoId { get; set; }
        public int centerId { get; set; }
        public int qty { get; set; }

        public ItemDpSaveDto (string planId,string enName,string arName,string planDate,string itemPoId,
            string centerId,string qty)
        {
            if (planId != "")
                this.planId = Convert.ToInt32(planId);
            else
                this.planId = 0;
            this.enName = enName;
            this.arName = arName;
            this.planDate = planDate;
            this.itemPoId = Convert.ToInt32(itemPoId);
            this.centerId = Convert.ToInt32(centerId);
            this.qty = Convert.ToInt32(qty);
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
