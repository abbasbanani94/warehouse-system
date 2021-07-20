using Newtonsoft.Json;

namespace WarehouseSystem
{
    class WbDetailsDto
    {
        public string wbNo { get; set; }
        public string wbDate { get; set; }
        public string boxes { get; set; }
        public string pallets { get; set; }
        public string city { get; set; }
        public string district { get; set; }
        public string center { get; set; }

        public WbDetailsDto (string wbNo,string wbDate,string boxes,string pallets,string city,string district,
            string center)
        {
            this.wbNo = wbNo;
            this.wbDate = wbDate;
            this.boxes = boxes;
            this.pallets = pallets;
            this.city = city;
            this.district = district;
            this.center = center;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
