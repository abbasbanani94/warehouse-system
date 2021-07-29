using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_item_po_reports : Form
    {
        public frm_item_po_reports(string itemPoId)
        {
            InitializeComponent();
            txtItemPoId.Text = itemPoId;
        }

        private void cmbReport_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.lettersOnly(e);
        }

        private void frm_item_po_reports_Load(object sender, System.EventArgs e)
        {
            string[] reportNames = { "Item General Info (Card)", "Item Inventory Log" };
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
                frm_report f = new frm_report(cmbReport.Text, txtItemPoId.Text);
                f.Show();
            }
        }
    }
}
