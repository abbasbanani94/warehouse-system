using Newtonsoft.Json;
using System;

namespace WarehouseSystem
{
    class KitDpSaveDto
    {
        public int planId { get; set; }
        public string enName { get; set; }
        public string arName { get; set; }
        public string planDate { get; set; }
        public int kitPoId { get; set; }
        public int centerId { get; set; }
        public int qty { get; set; }

        public KitDpSaveDto(string planId, string enName, string arName, string planDate, string kitPoId,
            string centerId, string qty)
        {
            if (planId != "")
                this.planId = Convert.ToInt32(planId);
            else
                this.planId = 0;
            this.enName = enName;
            this.arName = arName;
            this.planDate = planDate;
            this.kitPoId = Convert.ToInt32(kitPoId);
            this.centerId = Convert.ToInt32(centerId);
            this.qty = Convert.ToInt32(qty);
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
