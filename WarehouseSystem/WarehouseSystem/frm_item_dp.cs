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
    public partial class frm_item_dp : Form
    {
        public frm_item_dp()
        {
            InitializeComponent();
        }

        private void cmbPoNo_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.numbersOnly(e);
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void frm_item_dp_Load(object sender, EventArgs e)
        {
            load();
        }

        private void load()
        {
            PurchaseOrder.findAllPoCombo(cmbPoNo);
            DistributionPlan.findAllDpCombo(cmbDp);
        }
    }
}
