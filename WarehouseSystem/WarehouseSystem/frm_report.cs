using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_report : Form
    {
        string report = "", id = "";
        public frm_report(string report,string id)
        {
            InitializeComponent();
            this.report = report;
            this.id = id;
        }

        private void frm_report_Load(object sender, EventArgs e)
        {
            if(report == "Item General Info (Card)")
            {
                Reports.rpt_one_item_general_info r = new Reports.rpt_one_item_general_info();
                r.SetParameterValue("itemPoId", id);
                crv.ReportSource = r;
            }
            else if(report == "Item Inventory Log")
            {
                Reports.rpt_one_item_inventory r = new Reports.rpt_one_item_inventory();
                r.SetParameterValue("itemPoId", id);
                crv.ReportSource = r;
            }
            else if(report == "Kit General Info (Card)")
            {
                Reports.rpt_one_kit_general_info r = new Reports.rpt_one_kit_general_info();
                r.SetParameterValue("kitItemPo", id);
                crv.ReportSource = r;
            }
            else if(report == "Kit Inventory Log")
            {
                Reports.rpt_one_kit_inventory r = new Reports.rpt_one_kit_inventory();
                r.SetParameterValue("kitPoId", id);
                crv.ReportSource = r;
            }
            Text = report;
        }
    }
}
