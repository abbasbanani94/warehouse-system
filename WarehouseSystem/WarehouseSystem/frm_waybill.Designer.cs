namespace WarehouseSystem
{
    partial class frm_waybill
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle3 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle4 = new System.Windows.Forms.DataGridViewCellStyle();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.cmbCenter = new System.Windows.Forms.ComboBox();
            this.label10 = new System.Windows.Forms.Label();
            this.cmbDistrict = new System.Windows.Forms.ComboBox();
            this.label13 = new System.Windows.Forms.Label();
            this.cmbCity = new System.Windows.Forms.ComboBox();
            this.label4 = new System.Windows.Forms.Label();
            this.txtPallets = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.txtBoxes = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label21 = new System.Windows.Forms.Label();
            this.chkDate = new System.Windows.Forms.CheckBox();
            this.label24 = new System.Windows.Forms.Label();
            this.txtWbId = new System.Windows.Forms.TextBox();
            this.label25 = new System.Windows.Forms.Label();
            this.label26 = new System.Windows.Forms.Label();
            this.dtpWb = new System.Windows.Forms.DateTimePicker();
            this.txtWbNo = new System.Windows.Forms.TextBox();
            this.btnDetails = new System.Windows.Forms.Button();
            this.btnDelete = new System.Windows.Forms.Button();
            this.btnEdit = new System.Windows.Forms.Button();
            this.btnSave = new System.Windows.Forms.Button();
            this.btnSearch = new System.Windows.Forms.Button();
            this.btnNew = new System.Windows.Forms.Button();
            this.dgv = new System.Windows.Forms.DataGridView();
            this.btnExit = new System.Windows.Forms.Button();
            this.groupBox1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgv)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.btnExit);
            this.groupBox1.Controls.Add(this.btnDetails);
            this.groupBox1.Controls.Add(this.btnDelete);
            this.groupBox1.Controls.Add(this.btnEdit);
            this.groupBox1.Controls.Add(this.btnSave);
            this.groupBox1.Controls.Add(this.btnSearch);
            this.groupBox1.Controls.Add(this.btnNew);
            this.groupBox1.Controls.Add(this.txtWbNo);
            this.groupBox1.Controls.Add(this.cmbCenter);
            this.groupBox1.Controls.Add(this.label10);
            this.groupBox1.Controls.Add(this.cmbDistrict);
            this.groupBox1.Controls.Add(this.label13);
            this.groupBox1.Controls.Add(this.cmbCity);
            this.groupBox1.Controls.Add(this.label4);
            this.groupBox1.Controls.Add(this.txtPallets);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.txtBoxes);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Controls.Add(this.label21);
            this.groupBox1.Controls.Add(this.chkDate);
            this.groupBox1.Controls.Add(this.label24);
            this.groupBox1.Controls.Add(this.txtWbId);
            this.groupBox1.Controls.Add(this.label25);
            this.groupBox1.Controls.Add(this.label26);
            this.groupBox1.Controls.Add(this.dtpWb);
            this.groupBox1.Font = new System.Drawing.Font("Tahoma", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.groupBox1.Location = new System.Drawing.Point(12, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(1900, 208);
            this.groupBox1.TabIndex = 1;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Waybill Details";
            // 
            // cmbCenter
            // 
            this.cmbCenter.AutoCompleteMode = System.Windows.Forms.AutoCompleteMode.SuggestAppend;
            this.cmbCenter.AutoCompleteSource = System.Windows.Forms.AutoCompleteSource.ListItems;
            this.cmbCenter.Font = new System.Drawing.Font("Tahoma", 10F);
            this.cmbCenter.FormattingEnabled = true;
            this.cmbCenter.Location = new System.Drawing.Point(1367, 82);
            this.cmbCenter.Name = "cmbCenter";
            this.cmbCenter.Size = new System.Drawing.Size(452, 32);
            this.cmbCenter.TabIndex = 8;
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label10.Location = new System.Drawing.Point(1269, 85);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(92, 24);
            this.label10.TabIndex = 49;
            this.label10.Text = "CENTER*";
            // 
            // cmbDistrict
            // 
            this.cmbDistrict.AutoCompleteMode = System.Windows.Forms.AutoCompleteMode.SuggestAppend;
            this.cmbDistrict.AutoCompleteSource = System.Windows.Forms.AutoCompleteSource.ListItems;
            this.cmbDistrict.Font = new System.Drawing.Font("Tahoma", 10F);
            this.cmbDistrict.FormattingEnabled = true;
            this.cmbDistrict.Location = new System.Drawing.Point(745, 82);
            this.cmbDistrict.Name = "cmbDistrict";
            this.cmbDistrict.Size = new System.Drawing.Size(455, 32);
            this.cmbDistrict.TabIndex = 7;
            this.cmbDistrict.TextChanged += new System.EventHandler(this.cmbDistrict_TextChanged);
            // 
            // label13
            // 
            this.label13.AutoSize = true;
            this.label13.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label13.Location = new System.Drawing.Point(621, 85);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(108, 24);
            this.label13.TabIndex = 47;
            this.label13.Text = "DISTRICT*";
            // 
            // cmbCity
            // 
            this.cmbCity.AutoCompleteMode = System.Windows.Forms.AutoCompleteMode.SuggestAppend;
            this.cmbCity.AutoCompleteSource = System.Windows.Forms.AutoCompleteSource.ListItems;
            this.cmbCity.Font = new System.Drawing.Font("Tahoma", 10F);
            this.cmbCity.FormattingEnabled = true;
            this.cmbCity.Location = new System.Drawing.Point(140, 82);
            this.cmbCity.Name = "cmbCity";
            this.cmbCity.Size = new System.Drawing.Size(477, 32);
            this.cmbCity.TabIndex = 6;
            this.cmbCity.TextChanged += new System.EventHandler(this.cmbCity_TextChanged);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(70, 85);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(64, 24);
            this.label4.TabIndex = 45;
            this.label4.Text = "CITY*";
            // 
            // txtPallets
            // 
            this.txtPallets.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtPallets.Location = new System.Drawing.Point(1367, 44);
            this.txtPallets.Name = "txtPallets";
            this.txtPallets.Size = new System.Drawing.Size(144, 32);
            this.txtPallets.TabIndex = 5;
            this.txtPallets.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.txtWbNo_KeyPress);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(1208, 47);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(153, 24);
            this.label2.TabIndex = 42;
            this.label2.Text = "TOTAL PALLETS";
            // 
            // txtBoxes
            // 
            this.txtBoxes.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtBoxes.Location = new System.Drawing.Point(1056, 44);
            this.txtBoxes.Name = "txtBoxes";
            this.txtBoxes.Size = new System.Drawing.Size(144, 32);
            this.txtBoxes.TabIndex = 4;
            this.txtBoxes.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.txtWbNo_KeyPress);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(915, 47);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(136, 24);
            this.label1.TabIndex = 40;
            this.label1.Text = "TOTAL BOXES";
            // 
            // label21
            // 
            this.label21.AutoSize = true;
            this.label21.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label21.Location = new System.Drawing.Point(1531, 47);
            this.label21.Name = "label21";
            this.label21.Size = new System.Drawing.Size(157, 24);
            this.label21.TabIndex = 38;
            this.label21.Text = "Search Options :";
            // 
            // chkDate
            // 
            this.chkDate.AutoSize = true;
            this.chkDate.Font = new System.Drawing.Font("Tahoma", 10F);
            this.chkDate.Location = new System.Drawing.Point(1704, 46);
            this.chkDate.Name = "chkDate";
            this.chkDate.Size = new System.Drawing.Size(115, 28);
            this.chkDate.TabIndex = 37;
            this.chkDate.TabStop = false;
            this.chkDate.Text = "WB Date";
            this.chkDate.UseVisualStyleBackColor = true;
            // 
            // label24
            // 
            this.label24.AutoSize = true;
            this.label24.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label24.Location = new System.Drawing.Point(623, 47);
            this.label24.Name = "label24";
            this.label24.Size = new System.Drawing.Size(106, 24);
            this.label24.TabIndex = 2;
            this.label24.Text = "WB DATE*";
            // 
            // txtWbId
            // 
            this.txtWbId.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtWbId.Location = new System.Drawing.Point(140, 44);
            this.txtWbId.Name = "txtWbId";
            this.txtWbId.ReadOnly = true;
            this.txtWbId.Size = new System.Drawing.Size(124, 32);
            this.txtWbId.TabIndex = 1;
            this.txtWbId.TabStop = false;
            // 
            // label25
            // 
            this.label25.AutoSize = true;
            this.label25.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label25.Location = new System.Drawing.Point(17, 47);
            this.label25.Name = "label25";
            this.label25.Size = new System.Drawing.Size(118, 24);
            this.label25.TabIndex = 0;
            this.label25.Text = "WAYBILL ID";
            // 
            // label26
            // 
            this.label26.AutoSize = true;
            this.label26.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label26.Location = new System.Drawing.Point(270, 47);
            this.label26.Name = "label26";
            this.label26.Size = new System.Drawing.Size(135, 24);
            this.label26.TabIndex = 11;
            this.label26.Text = "WAYBILL NO*";
            // 
            // dtpWb
            // 
            this.dtpWb.Font = new System.Drawing.Font("Tahoma", 10F);
            this.dtpWb.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.dtpWb.Location = new System.Drawing.Point(745, 44);
            this.dtpWb.Name = "dtpWb";
            this.dtpWb.Size = new System.Drawing.Size(148, 32);
            this.dtpWb.TabIndex = 3;
            // 
            // txtWbNo
            // 
            this.txtWbNo.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtWbNo.Location = new System.Drawing.Point(411, 44);
            this.txtWbNo.Name = "txtWbNo";
            this.txtWbNo.Size = new System.Drawing.Size(206, 32);
            this.txtWbNo.TabIndex = 2;
            this.txtWbNo.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.txtWbNo_KeyPress);
            // 
            // btnDetails
            // 
            this.btnDetails.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnDetails.Location = new System.Drawing.Point(1226, 136);
            this.btnDetails.Name = "btnDetails";
            this.btnDetails.Size = new System.Drawing.Size(98, 51);
            this.btnDetails.TabIndex = 14;
            this.btnDetails.Text = "Details";
            this.btnDetails.UseVisualStyleBackColor = true;
            this.btnDetails.Click += new System.EventHandler(this.btnDetails_Click);
            // 
            // btnDelete
            // 
            this.btnDelete.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnDelete.Location = new System.Drawing.Point(1095, 136);
            this.btnDelete.Name = "btnDelete";
            this.btnDelete.Size = new System.Drawing.Size(98, 51);
            this.btnDelete.TabIndex = 13;
            this.btnDelete.Text = "Delete";
            this.btnDelete.UseVisualStyleBackColor = true;
            this.btnDelete.Click += new System.EventHandler(this.btnDelete_Click);
            // 
            // btnEdit
            // 
            this.btnEdit.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnEdit.Location = new System.Drawing.Point(964, 136);
            this.btnEdit.Name = "btnEdit";
            this.btnEdit.Size = new System.Drawing.Size(98, 51);
            this.btnEdit.TabIndex = 12;
            this.btnEdit.Text = "Edit";
            this.btnEdit.UseVisualStyleBackColor = true;
            this.btnEdit.Click += new System.EventHandler(this.btnEdit_Click);
            // 
            // btnSave
            // 
            this.btnSave.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSave.Location = new System.Drawing.Point(833, 136);
            this.btnSave.Name = "btnSave";
            this.btnSave.Size = new System.Drawing.Size(98, 51);
            this.btnSave.TabIndex = 11;
            this.btnSave.Text = "Save";
            this.btnSave.UseVisualStyleBackColor = true;
            this.btnSave.Click += new System.EventHandler(this.btnSave_Click);
            // 
            // btnSearch
            // 
            this.btnSearch.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSearch.Location = new System.Drawing.Point(702, 136);
            this.btnSearch.Name = "btnSearch";
            this.btnSearch.Size = new System.Drawing.Size(98, 51);
            this.btnSearch.TabIndex = 10;
            this.btnSearch.Text = "Search";
            this.btnSearch.UseVisualStyleBackColor = true;
            this.btnSearch.Click += new System.EventHandler(this.btnSearch_Click);
            // 
            // btnNew
            // 
            this.btnNew.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnNew.Location = new System.Drawing.Point(571, 136);
            this.btnNew.Name = "btnNew";
            this.btnNew.Size = new System.Drawing.Size(98, 51);
            this.btnNew.TabIndex = 9;
            this.btnNew.Text = "New";
            this.btnNew.UseVisualStyleBackColor = true;
            this.btnNew.Click += new System.EventHandler(this.btnNew_Click);
            // 
            // dgv
            // 
            this.dgv.AllowUserToAddRows = false;
            this.dgv.AllowUserToDeleteRows = false;
            this.dgv.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.AllCells;
            dataGridViewCellStyle3.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle3.BackColor = System.Drawing.SystemColors.Control;
            dataGridViewCellStyle3.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            dataGridViewCellStyle3.ForeColor = System.Drawing.SystemColors.WindowText;
            dataGridViewCellStyle3.SelectionBackColor = System.Drawing.SystemColors.Highlight;
            dataGridViewCellStyle3.SelectionForeColor = System.Drawing.SystemColors.HighlightText;
            dataGridViewCellStyle3.WrapMode = System.Windows.Forms.DataGridViewTriState.True;
            this.dgv.ColumnHeadersDefaultCellStyle = dataGridViewCellStyle3;
            this.dgv.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewCellStyle4.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle4.BackColor = System.Drawing.SystemColors.Window;
            dataGridViewCellStyle4.Font = new System.Drawing.Font("Tahoma", 10F);
            dataGridViewCellStyle4.ForeColor = System.Drawing.SystemColors.ControlText;
            dataGridViewCellStyle4.SelectionBackColor = System.Drawing.SystemColors.Highlight;
            dataGridViewCellStyle4.SelectionForeColor = System.Drawing.SystemColors.HighlightText;
            dataGridViewCellStyle4.WrapMode = System.Windows.Forms.DataGridViewTriState.False;
            this.dgv.DefaultCellStyle = dataGridViewCellStyle4;
            this.dgv.Location = new System.Drawing.Point(12, 226);
            this.dgv.Name = "dgv";
            this.dgv.ReadOnly = true;
            this.dgv.RowTemplate.Height = 29;
            this.dgv.Size = new System.Drawing.Size(1900, 715);
            this.dgv.TabIndex = 1004;
            this.dgv.TabStop = false;
            this.dgv.CellDoubleClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgv_CellDoubleClick);
            // 
            // btnExit
            // 
            this.btnExit.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnExit.Location = new System.Drawing.Point(1356, 136);
            this.btnExit.Name = "btnExit";
            this.btnExit.Size = new System.Drawing.Size(98, 51);
            this.btnExit.TabIndex = 15;
            this.btnExit.Text = "Exit";
            this.btnExit.UseVisualStyleBackColor = true;
            this.btnExit.Click += new System.EventHandler(this.btnExit_Click);
            // 
            // frm_waybill
            // 
            this.AcceptButton = this.btnSave;
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 19F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1924, 953);
            this.Controls.Add(this.dgv);
            this.Controls.Add(this.groupBox1);
            this.Name = "frm_waybill";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Waybills";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.Load += new System.EventHandler(this.frm_waybill_Load);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgv)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.TextBox txtWbNo;
        private System.Windows.Forms.ComboBox cmbCenter;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.ComboBox cmbDistrict;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.ComboBox cmbCity;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox txtPallets;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox txtBoxes;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label21;
        private System.Windows.Forms.CheckBox chkDate;
        private System.Windows.Forms.Label label24;
        private System.Windows.Forms.TextBox txtWbId;
        private System.Windows.Forms.Label label25;
        private System.Windows.Forms.Label label26;
        private System.Windows.Forms.DateTimePicker dtpWb;
        private System.Windows.Forms.Button btnDetails;
        private System.Windows.Forms.Button btnDelete;
        private System.Windows.Forms.Button btnEdit;
        private System.Windows.Forms.Button btnSave;
        private System.Windows.Forms.Button btnSearch;
        private System.Windows.Forms.Button btnNew;
        private System.Windows.Forms.DataGridView dgv;
        private System.Windows.Forms.Button btnExit;
    }
}