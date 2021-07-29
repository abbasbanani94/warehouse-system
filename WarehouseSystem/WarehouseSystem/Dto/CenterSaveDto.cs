using Newtonsoft.Json;

namespace WarehouseSystem
{
    class CenterSaveDto
    {
        private object selectedValue;
        private string text1;
        private string text2;

        public string id { get; set; }
        public string districtId { get; set; }
        public string enName { get; set; }
        public string arName { get; set; }
        public CenterSaveDto (string id,string districtId,string enName,string arName)
        {
            this.id = id;
            this.districtId = districtId;
            this.enName = enName;
            this.arName = arName;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
