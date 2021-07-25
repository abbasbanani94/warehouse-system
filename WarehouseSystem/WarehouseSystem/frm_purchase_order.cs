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
                if(PurchaseOrder.savePurchaseOrder(Convert.ToInt32(txtNo.Text)))
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
                    if(PurchaseOrder.editPurchaseOrder(Convert.ToInt32(txtId.Text), Convert.ToInt32(txtNo.Text)))
                    {
                        Msg.doneMsg("Purchase Order edited successfully !", "Successfully");
                        done();
                    }
                }
            }
        }

        private string poId = "", items = "", kits = "";

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (txtId.Text == "" || txtNo.Text == "")
                Msg.emptyFields();
            else
            {
                if (!items.Equals("0") || !kits.Equals("0"))
                    Msg.errorMsg("This PO cannot be deleted because it's included in another tables", "Error");
                else
                {
                    if(Msg.questionMsg("Do you want to delete this PO ?", "Delete Confirmation") == DialogResult.Yes)
                    {
                        if(PurchaseOrder.deletePurchaseOrder(txtId.Text))
                        {
                            Msg.doneMsg("Purchase Order deleted successfully !", "Successfully");
                            done();
                        }
                    }
                }
            }
        }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            if (txtNo.Text == "")
                Msg.emptyFields();
            else
            {
                PurchaseOrder.searchPurchaseOrder(dgv, txtNo.Text);

                if (dgv.Rows.Count == 0)
                    PurchaseOrder.findAllPoDgv(dgv);
            }
        }

        private void dgv_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            resetVariables();
            if(e.RowIndex != -1)
            {
                txtId.Text = dgv.Rows[e.RowIndex].Cells["PO ID"].Value.ToString();
                txtNo.Text = dgv.Rows[e.RowIndex].Cells["PO NO"].Value.ToString();
                poId = txtId.Text;
                items = dgv.Rows[e.RowIndex].Cells["Items Count"].Value.ToString();
                kits = dgv.Rows[e.RowIndex].Cells["kits Count"].Value.ToString();
            }
        }

        private void resetVariables()
        {
            poId = "";
            items = "";
            kits = "";
        }
    }
}
