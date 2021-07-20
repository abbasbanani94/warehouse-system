using Newtonsoft.Json;
using System.Collections.Generic;

namespace WarehouseSystem
{
    class WbDetailsSaveDto
    {
        public List<string> dpList { get; set; }
        public List<string> wbList { get; set; }

        public WbDetailsSaveDto (List<string> dpList,List<string> wbList)
        {
            this.dpList = dpList;
            this.wbList = wbList;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
