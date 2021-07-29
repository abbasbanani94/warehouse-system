using Newtonsoft.Json;

namespace WarehouseSystem
{
    class KitDisposalSaveDto
    {
        public string kitPoId { get; set; }
        public string qty { get; set; }

        public KitDisposalSaveDto(string kitPoId, string qty)
        {
            this.kitPoId = kitPoId;
            this.qty = qty;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
