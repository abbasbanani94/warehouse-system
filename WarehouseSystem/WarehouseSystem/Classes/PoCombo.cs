using Newtonsoft.Json;

namespace WarehouseSystem
{
    class PoCombo
    {
        public int id { get; set; }
        public string no { get; set; }

        public PoCombo (int id,string no)
        {
            this.id = id;
            this.no = no;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
