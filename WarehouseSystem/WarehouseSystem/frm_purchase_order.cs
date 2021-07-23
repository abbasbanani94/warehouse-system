using System;
using System.Windows.Forms;

namespace WarehouseSystem
{
    public partial class frm_purchase_order : Form
    {
        public frm_purchase_order()
        {
            InitializeComponent();
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void txtNo_KeyPress(object sender, KeyPressEventArgs e)
        {
            Msg.numbersOnly(e);
        }

        private void frm_purchase_order_Load(object sender, EventArgs e)
        {
            load();
        }

        private void load()
        {
            txtNo.Focus();
            PurchaseOrder.findAllPoDgv(dgv);
        }

        private void btnNew_Click(object sender, EventArgs e)
        {
            done();
        }

        private void done()
        {
            FormsFunctions.clearAll(groupBox1);
            load();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (txtNo.Text == "")
                Msg.emptyFields();
            else
            {
                if(PurchaseOrder.savePurchaseOrder(txtNo.Text))
                {
                    Msg.doneMsg("Purchase Order saved successfully !", "Successfully");
                    done();
                }
            }
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (txtId.Text == "" || txtNo.Text == "")
                Msg.emptyFields();
            else
            {
                if(Msg.questionMsg("Do you want to edit Purchase Order's No ?","Edit Confirmation") == DialogResult.Yes)
                {
                    if(PurchaseOrder.editPurchaseOrder(txtId.Text, txtNo.Text))
                    {
                        Msg.doneMsg("Purchase Order edited successfully !", "Successfully");
                        done();
                    }
                }
            }
        }

        private void dgv_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if(e.RowIndex != -1)
            {
                txtId.Text = dgv.Rows[e.RowIndex].Cells["PO ID"].Value.ToString();
                txtNo.Text = dgv.Rows[e.RowIndex].Cells["PO NO"].Value.ToString();
            }
        }
    }
}
