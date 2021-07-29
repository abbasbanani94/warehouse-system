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
    public partial class frm_kit_po_reports : Form
    {
        public frm_kit_po_reports(string kitPoId)
        {
            InitializeComponent();
            txtKitPoId.Text = kitPoId;
        }

        private void cmbReport_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.lettersOnly(e);
        }

        private void frm_kit_po_reports_Load(object sender, EventArgs e)
        {
            string[] reportNames = { "Kit General Info (Card)", "Kit Inventory Log" };
            addComboItems(reportNames);
        }

        private void addComboItems(string[] reportNames)
        {
            cmbReport.Items.AddRange(reportNames);
        }

        private void btnPrint_Click(object sender, EventArgs e)
        {
            if (!FormsFunctions.checkItemInCombo(cmbReport.Text, cmbReport))
                Msg.reportDoesntExist();
            else
            {
                frm_report f = new frm_report(cmbReport.Text, txtKitPoId.Text);
                f.Show();
            }
        }
    }
}
