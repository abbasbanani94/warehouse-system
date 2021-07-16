using System;
using System.Net.Http;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WarehouseSystem
{
    class Msg
    {
        static public void doneMsg(string body, string title)
        {
            MessageBox.Show(body, title, MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1, MessageBoxOptions.RtlReading);
        }

        static public void errorMsg(string body, string title)
        {
            MessageBox.Show(body, title, MessageBoxButtons.OK, MessageBoxIcon.Error, MessageBoxDefaultButton.Button1, MessageBoxOptions.RtlReading);
        }

        static public DialogResult questionMsg(string body, string title)
        {
            return MessageBox.Show(body, title, MessageBoxButtons.YesNo, MessageBoxIcon.Question, MessageBoxDefaultButton.Button1, MessageBoxOptions.RtlReading);
        }

        internal static void formAlreadyOpen(string v)
        {
            errorMsg(v + " form is already opened, you can't open the same form twice", "Already Open");
        }

        static public void numbersOnly(KeyPressEventArgs e)
        {
            if (!char.IsControl(e.KeyChar) && !char.IsDigit(e.KeyChar) && (e.KeyChar != '.'))
            {
                e.Handled = true;
                errorMsg("This field accepts only numbers", "Numbers Only");
            }
        }

        static public void lettersOnly(KeyPressEventArgs e)
        {
            if (!char.IsLetter(e.KeyChar) && e.KeyChar != (char)Keys.Back && e.KeyChar != (char)Keys.Space)
            {
                e.Handled = true;
                errorMsg("This field accepts only letters and symbols", "Letters & Symbols Only");
            }
        }

        static public void emptyFields()
        {
            errorMsg("You must enter data in all mandatory fields", "Empty Fields");
        }

        static public void idAlreadyUsed()
        {
            errorMsg("This ID is already used, press on New button then try again", "Error");
        }

        internal static void idDoesntExistsDelete()
        {
            errorMsg("This ID doesn't exist to be deleted", "ID doesn't exists");
        }

        internal static void idDoesntExistsEdit()
        {
            errorMsg("This ID doesn't exist to be edited", "ID doesn't exists");
        }

        internal static void error500()
        {
            errorMsg("Internal Server Error", "Error 500");
        }
    }
}
