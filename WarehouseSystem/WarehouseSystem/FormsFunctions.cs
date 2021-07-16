using System.Windows.Forms;

namespace WarehouseSystem
{
    class FormsFunctions
    {
        static public void clearAll(GroupBox gb)
        {
            foreach (Control c in gb.Controls)
            {
                if (c is TextBox || c is ComboBox)
                    c.ResetText();
            }
        }
    }
}
