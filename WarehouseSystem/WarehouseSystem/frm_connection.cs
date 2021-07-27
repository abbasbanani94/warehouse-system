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
    public partial class frm_connection : Form
    {
        public frm_connection()
        {
            InitializeComponent();
        }

        private void frm_connection_Load(object sender, EventArgs e)
        {
            txtUrl.Text = Properties.Settings.Default.serverUrl;
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (txtUrl.Text == "")
                Msg.emptyFields();
            else
            {
                Properties.Settings.Default.serverUrl = txtUrl.Text.Trim();
                Properties.Settings.Default.Save();
                Msg.doneMsg("Server configuration saved successfully !", "Success");
                Close();
            }
        }
    }
}
