using System;
using System.Collections.Generic;
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

        static public List<string> addFromListBox(ListBox listBox)
        {
            List<string> list = new List<string>();
            for (int i = 0; i < listBox.Items.Count; i++)
            {
                list.Add(listBox.Items[i].ToString());
            }
            return list;
        }

        static public void moveItems(ListBox source, ListBox destination)
        {
            List<string> items = new List<string>();
            for (int i = 0; i < source.SelectedItems.Count; i++)
            {
                for (int j = 0; j < source.Items.Count; j++)
                {
                    if ((string)source.SelectedItems[i] == source.Items[j].ToString())
                    {
                        destination.Items.Add(source.Items[j]);
                        items.Add(source.Items[j].ToString());
                        break;
                    }
                }
            }
            for (int i = 0; i < items.Count; i++)
            {
                for (int j = 0; j < source.Items.Count; j++)
                {
                    if (items[i] == source.Items[j].ToString())
                    {
                        source.Items.RemoveAt(j);
                        break;
                    }
                }
            }
        }

        internal static bool checkItemInCombo(string item, ComboBox cmb)
        {
            for(int i=0; i<cmb.Items.Count; i++)
            {
                if (cmb.Items[i].ToString() == item)
                    return true;
            }
            return false;
        }
    }
}
