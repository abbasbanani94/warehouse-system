namespace WarehouseSystem
{
    partial class frm_parent
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
            this.components = new System.ComponentModel.Container();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.purchaseOrdersToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.detailsToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.itemsToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.kitsToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.distributionPlansToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.detailsToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.itemsToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.kitsToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.waybillsToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.healthCentersToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.dailyWorkersToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.disposalsToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.checksToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.usersToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStrip1 = new System.Windows.Forms.ToolStrip();
            this.tslDate = new System.Windows.Forms.ToolStripLabel();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.tslTime = new System.Windows.Forms.ToolStripLabel();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.tslRole = new System.Windows.Forms.ToolStripLabel();
            this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            this.tslUser = new System.Windows.Forms.ToolStripLabel();
            this.timer1 = new System.Windows.Forms.Timer(this.components);
            this.changePasswordToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.menuStrip1.SuspendLayout();
            this.toolStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.ImageScalingSize = new System.Drawing.Size(24, 24);
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.purchaseOrdersToolStripMenuItem,
            this.distributionPlansToolStripMenuItem,
            this.waybillsToolStripMenuItem,
            this.healthCentersToolStripMenuItem,
            this.dailyWorkersToolStripMenuItem,
            this.disposalsToolStripMenuItem,
            this.checksToolStripMenuItem,
            this.usersToolStripMenuItem,
            this.changePasswordToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(1454, 33);
            this.menuStrip1.TabIndex = 0;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // purchaseOrdersToolStripMenuItem
            // 
            this.purchaseOrdersToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.detailsToolStripMenuItem,
            this.itemsToolStripMenuItem,
            this.kitsToolStripMenuItem});
            this.purchaseOrdersToolStripMenuItem.Name = "purchaseOrdersToolStripMenuItem";
            this.purchaseOrdersToolStripMenuItem.Size = new System.Drawing.Size(153, 29);
            this.purchaseOrdersToolStripMenuItem.Text = "Purchase Orders";
            // 
            // detailsToolStripMenuItem
            // 
            this.detailsToolStripMenuItem.Name = "detailsToolStripMenuItem";
            this.detailsToolStripMenuItem.Size = new System.Drawing.Size(149, 30);
            this.detailsToolStripMenuItem.Text = "Details";
            this.detailsToolStripMenuItem.Click += new System.EventHandler(this.detailsToolStripMenuItem_Click);
            // 
            // itemsToolStripMenuItem
            // 
            this.itemsToolStripMenuItem.Name = "itemsToolStripMenuItem";
            this.itemsToolStripMenuItem.Size = new System.Drawing.Size(149, 30);
            this.itemsToolStripMenuItem.Text = "Items";
            this.itemsToolStripMenuItem.Click += new System.EventHandler(this.itemsToolStripMenuItem_Click);
            // 
            // kitsToolStripMenuItem
            // 
            this.kitsToolStripMenuItem.Name = "kitsToolStripMenuItem";
            this.kitsToolStripMenuItem.Size = new System.Drawing.Size(149, 30);
            this.kitsToolStripMenuItem.Text = "Kits";
            this.kitsToolStripMenuItem.Click += new System.EventHandler(this.kitsToolStripMenuItem_Click);
            // 
            // distributionPlansToolStripMenuItem
            // 
            this.distributionPlansToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.detailsToolStripMenuItem1,
            this.itemsToolStripMenuItem1,
            this.kitsToolStripMenuItem1});
            this.distributionPlansToolStripMenuItem.Name = "distributionPlansToolStripMenuItem";
            this.distributionPlansToolStripMenuItem.Size = new System.Drawing.Size(163, 29);
            this.distributionPlansToolStripMenuItem.Text = "Distribution Plans";
            // 
            // detailsToolStripMenuItem1
            // 
            this.detailsToolStripMenuItem1.Name = "detailsToolStripMenuItem1";
            this.detailsToolStripMenuItem1.Size = new System.Drawing.Size(149, 30);
            this.detailsToolStripMenuItem1.Text = "Details";
            this.detailsToolStripMenuItem1.Click += new System.EventHandler(this.detailsToolStripMenuItem1_Click);
            // 
            // itemsToolStripMenuItem1
            // 
            this.itemsToolStripMenuItem1.Name = "itemsToolStripMenuItem1";
            this.itemsToolStripMenuItem1.Size = new System.Drawing.Size(149, 30);
            this.itemsToolStripMenuItem1.Text = "Items";
            this.itemsToolStripMenuItem1.Click += new System.EventHandler(this.itemsToolStripMenuItem1_Click);
            // 
            // kitsToolStripMenuItem1
            // 
            this.kitsToolStripMenuItem1.Name = "kitsToolStripMenuItem1";
            this.kitsToolStripMenuItem1.Size = new System.Drawing.Size(149, 30);
            this.kitsToolStripMenuItem1.Text = "Kits";
            this.kitsToolStripMenuItem1.Click += new System.EventHandler(this.kitsToolStripMenuItem1_Click);
            // 
            // waybillsToolStripMenuItem
            // 
            this.waybillsToolStripMenuItem.Name = "waybillsToolStripMenuItem";
            this.waybillsToolStripMenuItem.Size = new System.Drawing.Size(89, 29);
            this.waybillsToolStripMenuItem.Text = "Waybills";
            this.waybillsToolStripMenuItem.Click += new System.EventHandler(this.waybillsToolStripMenuItem_Click);
            // 
            // healthCentersToolStripMenuItem
            // 
            this.healthCentersToolStripMenuItem.Name = "healthCentersToolStripMenuItem";
            this.healthCentersToolStripMenuItem.Size = new System.Drawing.Size(139, 29);
            this.healthCentersToolStripMenuItem.Text = "Health Centers";
            this.healthCentersToolStripMenuItem.Click += new System.EventHandler(this.healthCentersToolStripMenuItem_Click);
            // 
            // dailyWorkersToolStripMenuItem
            // 
            this.dailyWorkersToolStripMenuItem.Name = "dailyWorkersToolStripMenuItem";
            this.dailyWorkersToolStripMenuItem.Size = new System.Drawing.Size(133, 29);
            this.dailyWorkersToolStripMenuItem.Text = "Daily Workers";
            this.dailyWorkersToolStripMenuItem.Click += new System.EventHandler(this.dailyWorkersToolStripMenuItem_Click);
            // 
            // disposalsToolStripMenuItem
            // 
            this.disposalsToolStripMenuItem.Name = "disposalsToolStripMenuItem";
            this.disposalsToolStripMenuItem.Size = new System.Drawing.Size(100, 29);
            this.disposalsToolStripMenuItem.Text = "Disposals";
            this.disposalsToolStripMenuItem.Click += new System.EventHandler(this.disposalsToolStripMenuItem_Click);
            // 
            // checksToolStripMenuItem
            // 
            this.checksToolStripMenuItem.Name = "checksToolStripMenuItem";
            this.checksToolStripMenuItem.Size = new System.Drawing.Size(79, 29);
            this.checksToolStripMenuItem.Text = "Checks";
            this.checksToolStripMenuItem.Click += new System.EventHandler(this.checksToolStripMenuItem_Click);
            // 
            // usersToolStripMenuItem
            // 
            this.usersToolStripMenuItem.Name = "usersToolStripMenuItem";
            this.usersToolStripMenuItem.Size = new System.Drawing.Size(67, 29);
            this.usersToolStripMenuItem.Text = "Users";
            this.usersToolStripMenuItem.Click += new System.EventHandler(this.usersToolStripMenuItem_Click);
            // 
            // toolStrip1
            // 
            this.toolStrip1.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.toolStrip1.ImageScalingSize = new System.Drawing.Size(24, 24);
            this.toolStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tslDate,
            this.toolStripSeparator1,
            this.tslTime,
            this.toolStripSeparator2,
            this.tslRole,
            this.toolStripSeparator3,
            this.tslUser});
            this.toolStrip1.Location = new System.Drawing.Point(0, 574);
            this.toolStrip1.Name = "toolStrip1";
            this.toolStrip1.Size = new System.Drawing.Size(1454, 28);
            this.toolStrip1.TabIndex = 1;
            this.toolStrip1.Text = "toolStrip1";
            // 
            // tslDate
            // 
            this.tslDate.Name = "tslDate";
            this.tslDate.Size = new System.Drawing.Size(131, 25);
            this.tslDate.Text = "toolStripLabel1";
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(6, 28);
            // 
            // tslTime
            // 
            this.tslTime.Name = "tslTime";
            this.tslTime.Size = new System.Drawing.Size(131, 25);
            this.tslTime.Text = "toolStripLabel2";
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(6, 28);
            // 
            // tslRole
            // 
            this.tslRole.Name = "tslRole";
            this.tslRole.Size = new System.Drawing.Size(131, 25);
            this.tslRole.Text = "toolStripLabel3";
            // 
            // toolStripSeparator3
            // 
            this.toolStripSeparator3.Name = "toolStripSeparator3";
            this.toolStripSeparator3.Size = new System.Drawing.Size(6, 28);
            // 
            // tslUser
            // 
            this.tslUser.Name = "tslUser";
            this.tslUser.Size = new System.Drawing.Size(131, 25);
            this.tslUser.Text = "toolStripLabel4";
            // 
            // timer1
            // 
            this.timer1.Tick += new System.EventHandler(this.timer1_Tick);
            // 
            // changePasswordToolStripMenuItem
            // 
            this.changePasswordToolStripMenuItem.Name = "changePasswordToolStripMenuItem";
            this.changePasswordToolStripMenuItem.Size = new System.Drawing.Size(164, 29);
            this.changePasswordToolStripMenuItem.Text = "Change Password";
            this.changePasswordToolStripMenuItem.Click += new System.EventHandler(this.changePasswordToolStripMenuItem_Click);
            // 
            // frm_parent
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 19F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1454, 602);
            this.Controls.Add(this.toolStrip1);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "frm_parent";
            this.Text = "Warehouse System";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.Load += new System.EventHandler(this.frm_parent_Load);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.toolStrip1.ResumeLayout(false);
            this.toolStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem purchaseOrdersToolStripMenuItem;
        private System.Windows.Forms.ToolStrip toolStrip1;
        private System.Windows.Forms.ToolStripLabel tslDate;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripLabel tslTime;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripLabel tslRole;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
        private System.Windows.Forms.ToolStripLabel tslUser;
        private System.Windows.Forms.Timer timer1;
        private System.Windows.Forms.ToolStripMenuItem itemsToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem kitsToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem healthCentersToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem distributionPlansToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem itemsToolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem kitsToolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem waybillsToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem detailsToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem detailsToolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem dailyWorkersToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem disposalsToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem checksToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem usersToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem changePasswordToolStripMenuItem;
    }
}