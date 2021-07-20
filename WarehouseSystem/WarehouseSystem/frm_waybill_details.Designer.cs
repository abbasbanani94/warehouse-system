namespace WarehouseSystem
{
    partial class frm_waybill_details
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
            this.label10 = new System.Windows.Forms.Label();
            this.label13 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.btnExit = new System.Windows.Forms.Button();
            this.txtPallets = new System.Windows.Forms.TextBox();
            this.txtBoxes = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.btnSave = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.label24 = new System.Windows.Forms.Label();
            this.txtWbId = new System.Windows.Forms.TextBox();
            this.label25 = new System.Windows.Forms.Label();
            this.label26 = new System.Windows.Forms.Label();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.btnLoad = new System.Windows.Forms.Button();
            this.btnRemoveAll = new System.Windows.Forms.Button();
            this.btnRemove = new System.Windows.Forms.Button();
            this.btnAddAll = new System.Windows.Forms.Button();
            this.btnAdd = new System.Windows.Forms.Button();
            this.label6 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.listWb = new System.Windows.Forms.ListBox();
            this.listDp = new System.Windows.Forms.ListBox();
            this.txtWbNo = new System.Windows.Forms.TextBox();
            this.txtCity = new System.Windows.Forms.TextBox();
            this.txtWbDate = new System.Windows.Forms.TextBox();
            this.txtDistrict = new System.Windows.Forms.TextBox();
            this.txtCenter = new System.Windows.Forms.TextBox();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.SuspendLayout();
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
            // btnExit
            // 
            this.btnExit.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnExit.Location = new System.Drawing.Point(892, 559);
            this.btnExit.Name = "btnExit";
            this.btnExit.Size = new System.Drawing.Size(98, 51);
            this.btnExit.TabIndex = 17;
            this.btnExit.Text = "Exit";
            this.btnExit.UseVisualStyleBackColor = true;
            this.btnExit.Click += new System.EventHandler(this.btnExit_Click);
            // 
            // txtPallets
            // 
            this.txtPallets.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtPallets.Location = new System.Drawing.Point(1367, 44);
            this.txtPallets.Name = "txtPallets";
            this.txtPallets.ReadOnly = true;
            this.txtPallets.Size = new System.Drawing.Size(144, 32);
            this.txtPallets.TabIndex = 5;
            this.txtPallets.TabStop = false;
            // 
            // txtBoxes
            // 
            this.txtBoxes.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtBoxes.Location = new System.Drawing.Point(1056, 44);
            this.txtBoxes.Name = "txtBoxes";
            this.txtBoxes.ReadOnly = true;
            this.txtBoxes.Size = new System.Drawing.Size(144, 32);
            this.txtBoxes.TabIndex = 4;
            this.txtBoxes.TabStop = false;
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
            // btnSave
            // 
            this.btnSave.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSave.Location = new System.Drawing.Point(892, 485);
            this.btnSave.Name = "btnSave";
            this.btnSave.Size = new System.Drawing.Size(98, 51);
            this.btnSave.TabIndex = 14;
            this.btnSave.Text = "Save";
            this.btnSave.UseVisualStyleBackColor = true;
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
            this.txtWbId.Location = new System.Drawing.Point(141, 44);
            this.txtWbId.Name = "txtWbId";
            this.txtWbId.ReadOnly = true;
            this.txtWbId.Size = new System.Drawing.Size(123, 32);
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
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.txtCenter);
            this.groupBox1.Controls.Add(this.txtDistrict);
            this.groupBox1.Controls.Add(this.txtWbDate);
            this.groupBox1.Controls.Add(this.txtCity);
            this.groupBox1.Controls.Add(this.txtWbNo);
            this.groupBox1.Controls.Add(this.label10);
            this.groupBox1.Controls.Add(this.label13);
            this.groupBox1.Controls.Add(this.label4);
            this.groupBox1.Controls.Add(this.txtPallets);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.txtBoxes);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Controls.Add(this.label24);
            this.groupBox1.Controls.Add(this.txtWbId);
            this.groupBox1.Controls.Add(this.label25);
            this.groupBox1.Controls.Add(this.label26);
            this.groupBox1.Font = new System.Drawing.Font("Tahoma", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.groupBox1.Location = new System.Drawing.Point(12, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(1900, 132);
            this.groupBox1.TabIndex = 1;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Waybill Details";
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.btnLoad);
            this.groupBox2.Controls.Add(this.btnRemoveAll);
            this.groupBox2.Controls.Add(this.btnRemove);
            this.groupBox2.Controls.Add(this.btnAddAll);
            this.groupBox2.Controls.Add(this.btnAdd);
            this.groupBox2.Controls.Add(this.label6);
            this.groupBox2.Controls.Add(this.label5);
            this.groupBox2.Controls.Add(this.listWb);
            this.groupBox2.Controls.Add(this.listDp);
            this.groupBox2.Controls.Add(this.btnExit);
            this.groupBox2.Controls.Add(this.btnSave);
            this.groupBox2.Font = new System.Drawing.Font("Tahoma", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.groupBox2.Location = new System.Drawing.Point(12, 150);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(1900, 791);
            this.groupBox2.TabIndex = 9;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "DP Details";
            // 
            // btnLoad
            // 
            this.btnLoad.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Bold);
            this.btnLoad.Location = new System.Drawing.Point(892, 115);
            this.btnLoad.Name = "btnLoad";
            this.btnLoad.Size = new System.Drawing.Size(98, 51);
            this.btnLoad.TabIndex = 48;
            this.btnLoad.TabStop = false;
            this.btnLoad.Text = "Load";
            this.btnLoad.UseVisualStyleBackColor = true;
            this.btnLoad.Click += new System.EventHandler(this.btnLoad_Click);
            // 
            // btnRemoveAll
            // 
            this.btnRemoveAll.Font = new System.Drawing.Font("Tahoma", 16F, System.Drawing.FontStyle.Bold);
            this.btnRemoveAll.Location = new System.Drawing.Point(892, 411);
            this.btnRemoveAll.Name = "btnRemoveAll";
            this.btnRemoveAll.Size = new System.Drawing.Size(98, 51);
            this.btnRemoveAll.TabIndex = 47;
            this.btnRemoveAll.TabStop = false;
            this.btnRemoveAll.Text = "<<";
            this.btnRemoveAll.UseVisualStyleBackColor = true;
            this.btnRemoveAll.Click += new System.EventHandler(this.btnRemoveAll_Click);
            // 
            // btnRemove
            // 
            this.btnRemove.Font = new System.Drawing.Font("Tahoma", 16F, System.Drawing.FontStyle.Bold);
            this.btnRemove.Location = new System.Drawing.Point(892, 337);
            this.btnRemove.Name = "btnRemove";
            this.btnRemove.Size = new System.Drawing.Size(98, 51);
            this.btnRemove.TabIndex = 46;
            this.btnRemove.TabStop = false;
            this.btnRemove.Text = "<";
            this.btnRemove.UseVisualStyleBackColor = true;
            this.btnRemove.Click += new System.EventHandler(this.btnRemove_Click);
            // 
            // btnAddAll
            // 
            this.btnAddAll.Font = new System.Drawing.Font("Tahoma", 16F, System.Drawing.FontStyle.Bold);
            this.btnAddAll.Location = new System.Drawing.Point(892, 263);
            this.btnAddAll.Name = "btnAddAll";
            this.btnAddAll.Size = new System.Drawing.Size(98, 51);
            this.btnAddAll.TabIndex = 45;
            this.btnAddAll.TabStop = false;
            this.btnAddAll.Text = ">>";
            this.btnAddAll.UseVisualStyleBackColor = true;
            this.btnAddAll.Click += new System.EventHandler(this.btnAddAll_Click);
            // 
            // btnAdd
            // 
            this.btnAdd.Font = new System.Drawing.Font("Tahoma", 16F, System.Drawing.FontStyle.Bold);
            this.btnAdd.Location = new System.Drawing.Point(892, 189);
            this.btnAdd.Name = "btnAdd";
            this.btnAdd.Size = new System.Drawing.Size(98, 51);
            this.btnAdd.TabIndex = 44;
            this.btnAdd.TabStop = false;
            this.btnAdd.Text = ">";
            this.btnAdd.UseVisualStyleBackColor = true;
            this.btnAdd.Click += new System.EventHandler(this.btnAdd_Click);
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Tahoma", 14F);
            this.label6.Location = new System.Drawing.Point(1006, 41);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(146, 34);
            this.label6.TabIndex = 43;
            this.label6.Text = "WB ITEMS";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Tahoma", 14F);
            this.label5.Location = new System.Drawing.Point(15, 41);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(138, 34);
            this.label5.TabIndex = 42;
            this.label5.Text = "DP ITEMS";
            // 
            // listWb
            // 
            this.listWb.Font = new System.Drawing.Font("Tahoma", 12F);
            this.listWb.FormattingEnabled = true;
            this.listWb.ItemHeight = 29;
            this.listWb.Location = new System.Drawing.Point(1012, 78);
            this.listWb.Name = "listWb";
            this.listWb.SelectionMode = System.Windows.Forms.SelectionMode.MultiSimple;
            this.listWb.Size = new System.Drawing.Size(867, 700);
            this.listWb.TabIndex = 41;
            this.listWb.TabStop = false;
            // 
            // listDp
            // 
            this.listDp.Font = new System.Drawing.Font("Tahoma", 12F);
            this.listDp.FormattingEnabled = true;
            this.listDp.ItemHeight = 29;
            this.listDp.Location = new System.Drawing.Point(21, 78);
            this.listDp.Name = "listDp";
            this.listDp.SelectionMode = System.Windows.Forms.SelectionMode.MultiSimple;
            this.listDp.Size = new System.Drawing.Size(847, 700);
            this.listDp.TabIndex = 40;
            this.listDp.TabStop = false;
            // 
            // txtWbNo
            // 
            this.txtWbNo.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtWbNo.Location = new System.Drawing.Point(411, 44);
            this.txtWbNo.Name = "txtWbNo";
            this.txtWbNo.ReadOnly = true;
            this.txtWbNo.Size = new System.Drawing.Size(184, 32);
            this.txtWbNo.TabIndex = 50;
            this.txtWbNo.TabStop = false;
            // 
            // txtCity
            // 
            this.txtCity.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtCity.Location = new System.Drawing.Point(140, 82);
            this.txtCity.Name = "txtCity";
            this.txtCity.ReadOnly = true;
            this.txtCity.Size = new System.Drawing.Size(455, 32);
            this.txtCity.TabIndex = 51;
            this.txtCity.TabStop = false;
            // 
            // txtWbDate
            // 
            this.txtWbDate.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtWbDate.Location = new System.Drawing.Point(735, 44);
            this.txtWbDate.Name = "txtWbDate";
            this.txtWbDate.ReadOnly = true;
            this.txtWbDate.Size = new System.Drawing.Size(174, 32);
            this.txtWbDate.TabIndex = 52;
            this.txtWbDate.TabStop = false;
            // 
            // txtDistrict
            // 
            this.txtDistrict.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtDistrict.Location = new System.Drawing.Point(735, 82);
            this.txtDistrict.Name = "txtDistrict";
            this.txtDistrict.ReadOnly = true;
            this.txtDistrict.Size = new System.Drawing.Size(465, 32);
            this.txtDistrict.TabIndex = 53;
            this.txtDistrict.TabStop = false;
            // 
            // txtCenter
            // 
            this.txtCenter.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtCenter.Location = new System.Drawing.Point(1367, 82);
            this.txtCenter.Name = "txtCenter";
            this.txtCenter.ReadOnly = true;
            this.txtCenter.Size = new System.Drawing.Size(495, 32);
            this.txtCenter.TabIndex = 54;
            this.txtCenter.TabStop = false;
            // 
            // frm_waybill_details
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 19F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1924, 953);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.groupBox2);
            this.Name = "frm_waybill_details";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Waybill Details";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.Load += new System.EventHandler(this.frm_waybill_details_Load);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button btnExit;
        private System.Windows.Forms.TextBox txtPallets;
        private System.Windows.Forms.TextBox txtBoxes;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button btnSave;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label24;
        private System.Windows.Forms.TextBox txtWbId;
        private System.Windows.Forms.Label label25;
        private System.Windows.Forms.Label label26;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.ListBox listWb;
        private System.Windows.Forms.ListBox listDp;
        private System.Windows.Forms.Button btnRemoveAll;
        private System.Windows.Forms.Button btnRemove;
        private System.Windows.Forms.Button btnAddAll;
        private System.Windows.Forms.Button btnAdd;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Button btnLoad;
        private System.Windows.Forms.TextBox txtCenter;
        private System.Windows.Forms.TextBox txtDistrict;
        private System.Windows.Forms.TextBox txtWbDate;
        private System.Windows.Forms.TextBox txtCity;
        private System.Windows.Forms.TextBox txtWbNo;
    }
}