using Newtonsoft.Json;

namespace WarehouseSystem
{
    class ItemDisposalSaveDto
    {
        public string itemPoId { get; set; }
        public string qty { get; set; }

        public ItemDisposalSaveDto (string itemPoId,string qty)
        {
            this.itemPoId = itemPoId;
            this.qty = qty;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
