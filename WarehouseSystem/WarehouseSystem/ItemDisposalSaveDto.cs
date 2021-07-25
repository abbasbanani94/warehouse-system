using Newtonsoft.Json;

namespace WarehouseSystem
{
    class ItemDisposalSaveDto
    {
        public string disposalId { get; set; }
        public string itemPoId { get; set; }
        public string qty { get; set; }

        public ItemDisposalSaveDto (string disposalId,string itemPoId,string qty)
        {
            this.disposalId = disposalId;
            this.itemPoId = itemPoId;
            this.qty = qty;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
