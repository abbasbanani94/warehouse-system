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
            if (User.role != "Admin")
                usersToolStripMenuItem.Enabled = false;
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

        private frm_kit_dp _kit_dp = null;

        private void kitsToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            if (_kit_dp == null)
            {
                _kit_dp = new frm_kit_dp();
                _kit_dp.Show();
                _kit_dp.FormClosed += _kit_dp_FormClosed;
            }
            else
                Msg.formAlreadyOpen("Kit Distribution Plans");
        }

        private void _kit_dp_FormClosed(object sender, FormClosedEventArgs e)
        {
            _kit_dp = null;
        }

        private frm_waybill _waybills = null;

        private void _waybills_FormClosed(object sender, FormClosedEventArgs e)
        {
            _waybills = null;
        }

        private void waybillsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (_waybills == null)
            {
                _waybills = new frm_waybill();
                _waybills.Show();
                _waybills.FormClosed += _waybills_FormClosed;
            }
            else
                Msg.formAlreadyOpen("Waybills");
        }

        private frm_purchase_order _purchase_order = null;

        private void detailsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (_purchase_order == null)
            {
                _purchase_order = new frm_purchase_order();
                _purchase_order.Show();
                _purchase_order.FormClosed += _purchase_order_FormClosed;
            }
            else
                Msg.formAlreadyOpen("Purchase Orders Details");
        }

        private void _purchase_order_FormClosed(object sender, FormClosedEventArgs e)
        {
            _purchase_order = null;
        }

        private frm_distribution_plan _dp = null;

        private void detailsToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            if (_dp == null)
            {
                _dp = new frm_distribution_plan();
                _dp.Show();
                _dp.FormClosed += _dp_FormClosed;
            }
            else
                Msg.formAlreadyOpen("Distribution Plan");
        }

        private void _dp_FormClosed(object sender, FormClosedEventArgs e)
        {
            _dp = null;
        }

        private frm_worker _worker = null;

        private void dailyWorkersToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (_worker == null)
            {
                _worker = new frm_worker();
                _worker.Show();
                _worker.FormClosed += _worker_FormClosed;
            }
            else
                Msg.formAlreadyOpen("Daily Workers");
        }

        private void _worker_FormClosed(object sender, FormClosedEventArgs e)
        {
            _worker = null;
        }

        private frm_disposal _disposal = null;

        private void disposalsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (_disposal == null)
            {
                _disposal = new frm_disposal();
                _disposal.Show();
                _disposal.FormClosed += _disposal_FormClosed;
            }
            else
                Msg.formAlreadyOpen("Disposals");
        }

        private void _disposal_FormClosed(object sender, FormClosedEventArgs e)
        {
            _disposal = null;
        }

        private frm_check _check = null;

        private void checksToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (_check == null)
            {
                _check = new frm_check();
                _check.Show();
                _check.FormClosed += _check_FormClosed;
            }
            else
                Msg.formAlreadyOpen("Checks");
        }

        private void _check_FormClosed(object sender, FormClosedEventArgs e)
        {
            _check = null;
        }

        private frm_user _user = null;

        private void usersToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (_user == null)
            {
                _user = new frm_user();
                _user.Show();
                _user.FormClosed += _user_FormClosed;
            }
            else
                Msg.formAlreadyOpen("Users");
        }

        private void _user_FormClosed(object sender, FormClosedEventArgs e)
        {
            _user = null;
        }

        private frm_password _password = null;

        private void changePasswordToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (_password == null)
            {
                _password = new frm_password();
                _password.Show();
                _password.FormClosed += _password_FormClosed;
            }
            else
                Msg.formAlreadyOpen("Change Password");
        }

        private void _password_FormClosed(object sender, FormClosedEventArgs e)
        {
            _password = null;
        }
    }
}
