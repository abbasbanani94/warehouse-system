using Newtonsoft.Json;

namespace WarehouseSystem
{
    class CenterEditDto
    {
        public string districtEn { get; set; }
        public string districtAr { get; set; }
        public string enName { get; set; }
        public string arName { get; set; }

        public CenterEditDto (string district,string enName,string arName)
        {
            string en = "", ar = "";
            en = district.Substring(0, district.IndexOf('-') - 1);
            ar = district.Substring(district.IndexOf('-') + 2);
            districtEn = en;
            districtAr = ar;
            this.enName = enName;
            this.arName = arName;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
