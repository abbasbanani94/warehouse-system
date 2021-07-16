using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_parent : Form
    {
        public frm_parent()
        {
            InitializeComponent();
        }

        private void frm_parent_Load(object sender, EventArgs e)
        {
            tslDate.Text = DateTime.Now.ToShortDateString();
            tslTime.Text = DateTime.Now.ToShortTimeString();
            tslRole.Text = User.role;
            tslUser.Text = User.name;
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            tslDate.Text = DateTime.Now.ToShortDateString();
            tslTime.Text = DateTime.Now.ToShortTimeString();
        }

        private frm_item_po _item_po = null;

        private void _item_po_FormClosed(object sender, FormClosedEventArgs e)
        {
            _item_po = null;
        }

        private void itemsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (_item_po == null)
            {
                _item_po = new frm_item_po();
                _item_po.Show();
                _item_po.FormClosed += _item_po_FormClosed;
            }
            else
                Msg.formAlreadyOpen("Items Purchase Orders");
        }

        private frm_kit_po _kit_po = null;

        private void kitsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (_kit_po == null)
            {
                _kit_po = new frm_kit_po();
                _kit_po.Show();
                _kit_po.FormClosed += _kit_po_FormClosed;
            }
            else
                Msg.formAlreadyOpen("Kits Purchase Orders");
        }

        private void _kit_po_FormClosed(object sender, FormClosedEventArgs e)
        {
            _kit_po = null;
        }

        private frm_health_center _health_center = null;

        private void healthCentersToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (_health_center == null)
            {
                _health_center = new frm_health_center();
                _health_center.Show();
                _health_center.FormClosed += _health_center_FormClosed;
            }
            else
                Msg.formAlreadyOpen("Health Centers");
        }

        private void _health_center_FormClosed(object sender, FormClosedEventArgs e)
        {
            _health_center = null;
        }

        private frm_item_dp _item_dp = null;

        private void itemsToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            if (_item_dp == null)
            {
                _item_dp = new frm_item_dp();
                _item_dp.Show();
                _item_dp.FormClosed += _item_dp_FormClosed;
            }
            else
                Msg.formAlreadyOpen("Item Distribution Plans");
        }

        private void _item_dp_FormClosed(object sender, FormClosedEventArgs e)
        {
            _item_dp = null;
        }
    }
}
