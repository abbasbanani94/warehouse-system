using Newtonsoft.Json;

namespace WarehouseSystem
{
    class WorkerSaveDto
    {
        public string enName { get; set; }
        public string arName { get; set; }
        public string mobile { get; set; }

        public WorkerSaveDto (string enName, string arName, string mobile)
        {
            this.enName = enName;
            this.arName = arName;
            this.mobile = mobile;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
