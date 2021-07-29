using Newtonsoft.Json;
using System.Collections.Generic;

namespace WarehouseSystem
{
    class CheckWorkerDto
    {
        public List<string> worker { get; set; }
        public CheckWorkerDto (List<string> worker)
        {
            this.worker = worker;
        }
        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
