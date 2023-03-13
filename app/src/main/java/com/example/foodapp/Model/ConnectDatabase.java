package com.example.foodapp.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.foodapp.R;
import androidx.annotation.Nullable;

public class ConnectDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PRM392_FOOD_APP";
    private static final int DATABASE_VERSION = 1;
    public ConnectDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //----------------------------------------------- Create table-------------------------------------------------------
        CreateTable(sqLiteDatabase);
        //----------------------------------------------- Insert data-------------------------------------------------------
        InsertUser(sqLiteDatabase);
        InsertCategory(sqLiteDatabase);
        InsertProduct(sqLiteDatabase);
        InsertOrder(sqLiteDatabase);
        InsertOrderDetail(sqLiteDatabase);
    }

    private void InsertOrderDetail(SQLiteDatabase sqLiteDatabase){
        String[] sqlInsert = {
                "INSERT INTO OrderDetail(OrderID,ProductID,quantity) VALUES (1,3,1)\n",
                "INSERT INTO OrderDetail(OrderID,ProductID,quantity) VALUES (2,7,1)\n",
                "INSERT INTO OrderDetail(OrderID,ProductID,quantity) VALUES (2,10,1)\n"
        };
        for(String sql : sqlInsert){
            sqLiteDatabase.execSQL(sql);
        }
    }

    private void InsertOrder(SQLiteDatabase sqLiteDatabase){
        String[] sqlInsert ={
                "INSERT INTO [Order](UserID,OrderDate,ShipDate,Status,Address) VALUES(2,'2023-02-16','2023-02-16','Completed','7683 Ruskin Avenue')\n",
                "INSERT INTO [Order](UserID,OrderDate,ShipDate,Status,Address) VALUES(3,'2023-02-17','2023-02-18','Completed','0341 Everett Court')\n"
        };
        for(String sql : sqlInsert){
            sqLiteDatabase.execSQL(sql);
        }
    }
    private void InsertProduct(SQLiteDatabase sqLiteDatabase){
        String[] sqlInsert ={
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Ốc hương',"+ R.drawable.oc_huong+",15.25,1,'Ốc hương là loài thân mềm một mảnh vỏ, thịt thơm ngon, giàu chất dinh dưỡng. Từ lâu ốc hương đã được coi là món ngon hạng nhất trong những đặc sản biển. Loài ốc này ngay cả khi tươi sống đã tỏa ra hương thơm tự nhiên, hấp dẫn, đúng như tên của nó.')\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Cua Năm Căn',"+R.drawable.cua_nam_can+",10.00,1,NULL)\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Tôm hùm',"+R.drawable.tom_hum+",16.00,1,'Tôm hùm ở Bình Ba ngoài vị béo ngậy, ngọt lịm do tôm còn tươi khi ăn kèm muối ớt xanh ở đây càng quyện vào nhau hơn.')\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Cá ngừ đại dương',"+R.drawable.ca_ngu_dai_duong+",12.25,1,'Cá ngừ đại dương là loại hải sản đặc biệt thơm ngon, mắt rất bổ, được chế biến thành nhiều loại món ăn ngon và tạo nguồn hàng xuất khẩu có giá trị.')\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Bào ngư',"+R.drawable.bao_ngu+",13.00,1,'Đây là sản vật đặc trưng, loài đặc sản mang lại hiệu quả kinh tế cao của huyện đảo này, là nguồn thực phẩm bổ dưỡng, vị thuốc quý, được đánh giá là có chất lượng nhất và là một trong những đặc sản hải sản của Việt Nam sánh ngang với bào ngư nổi tiếng ở nhiều nước trên thế giới')\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Phở cuốn',"+R.drawable.pho_cuon+",15.75,2,'Nhân cuốn bên trong phở rất đa dạng, có thể thay bằng tôm, chả chiên, thịt bò tùy theo sở thích của gia đình. Nhưng dù dùng phần nhân gì đây chăng nữa thì phở cuốn vẫn là món ngon cực phẩm, chinh phục được mọi thực khách.')\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Phở gà',"+R.drawable.pho_ga+",17.75,2,'Nước dùng phở gà có màu vàng nhạt nhưng vẫn giữ được độ trong chuẩn phở Việt. Bánh phở mềm, ngập trong nước dùng ngọt thanh, thơm mùi gừng, quế, hồi.')\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Phở xào',"+R.drawable.pho_xao+",16.75,2,'Phở xào với những miếng thịt bò mềm, ngọt, quyện cùng với cải thìa, cà rốt, hành tây, vừa tươi, vừa giòn, vừa ngọt, thêm một chút ngò rí, hành lá thơm lừng.')\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Cơm rang cua',"+R.drawable.com_rang_cua+",18.75,4,NULL)\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Cơm rang lạp xưởng và tôm',"+R.drawable.com_rang_lap_xuong_va_tom+",12.75,4,NULL)\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Cơm rang kim chi',"+R.drawable.com_rang_kim_chi+",13.5,4,NULL)\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Bún riêu cua',"+R.drawable.bun_rieu_cua+",16.00,3,'Nhắc đến bún riêu không ai là không nghĩ đến tô bún nóng hổi với nước dùng thanh ngọt, ăn cùng với chả riêu hấp béo mềm, dậy mùi thơm hấp dẫn của gạch cua.')\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Bún bò giò heo',"+R.drawable.bun_bo_gio_heo+",16.25,3,'Điểm đặc biệt của món ăn này là phần giò heo và bắp bò chín mềm, thấm đều gia vị. Khi ăn ta sẽ cảm nhận được vị giòn ngon của da heo được giữ trọn vẹn')\n"
        };
        for(String sql : sqlInsert){
            sqLiteDatabase.execSQL(sql);
        }
    }

    private void InsertCategory(SQLiteDatabase sqLiteDatabase){
        String[] sqlInsert = {
                "INSERT INTO Category ([Name]) VALUES ('Hải sản')\n",
                "INSERT INTO Category ([Name]) VALUES ('Phở')\n",
                "INSERT INTO Category ([Name]) VALUES ('Bún')\n",
                "INSERT INTO Category ([Name]) VALUES ('Cơm rang')"
        };
        for(String sql : sqlInsert){
            sqLiteDatabase.execSQL(sql);
        }
    }

    private void InsertUser(SQLiteDatabase sqLiteDatabase){
        String[] sqlInsert = {
         "INSERT INTO [dbo].[User]([FullName],[phone],[email],[gender],[username],[password],[RoleName])VALUES('Kirk Nelson','4533389559','oparagreen0@usnews.com','Female','Seller','999@','Seller')\n",
         "INSERT INTO [dbo].[User]([FullName],[phone],[email],[gender],[username],[password],[RoleName])VALUES('Nguyen Thi Thu','4533389559','oparagreen0@usnews.com','Female','ThuThu','123@','Customer')\n",
         "INSERT INTO [dbo].[User]([FullName],[phone],[email],[gender],[username],[password],[RoleName])VALUES('Nguyen Anh Tuan','6298446654','kfleet1@artisteer.com','Male','AnhTuan','123@','Customer')\n",
         "INSERT INTO [dbo].[User]([FullName],[phone],[email],[gender],[username],[password],[RoleName])VALUES('Chu Quang Quan','8851738015','fellcock2@earthlink.net','Male','QuangQuan','123@','Customer')\n",
         "INSERT INTO [dbo].[User]([FullName],[phone],[email],[gender],[username],[password],[RoleName])VALUES('Nguyen Minh Duc','5541282702','bkervin4@fotki.com','Male','MinhDuc','123@','Customer')\n",
         "INSERT INTO [dbo].[User]([FullName],[phone],[email],[gender],[username],[password],[RoleName])VALUES('Nicky Gaitone','7583151589','ngaitone6@cyberchimps.com','Female','Nicky','123@','Customer')\n"
        };
        for(String sql : sqlInsert){
            sqLiteDatabase.execSQL(sql);
        }
    }

    private void CreateTable(SQLiteDatabase sqLiteDatabase){
        String sqlUser = "CREATE TABLE User(\n" +
                "\tID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\tFullName TEXT NOT NULL,\n" +
                "\tPhone TEXT NOT NULL\n," +
                "\tEmail TEXT, \n" +
                "\tGender TEXT, \n" +
                "\tUsername TEXT NOT NULL,\n" +
                "\tPassword TEXT NOT NULL,\n" +
                "\tRoleName TEXT NOT NULL\n" +
                ");";

        String sqlCategory = "CREATE TABLE Category(\n" +
                "\tID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\tName TEXT NOT NULL\n" +
                ");";
        String sqlOrder = "CREATE TABLE [Order](\n" +
                "\tOrderID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\tUserID INTEGER  NOT NULL,\n" +
                "\tOrderDate TEXT  NOT NULL,\n" +
                "\tShipDate TEXT ,\n" +
                "\tStatus TEXT NOT NULL, \n" +
                "\tAddress TEXT NOT NULL,\n" +
                "\tFOREIGN KEY (UserID) REFERENCES User(ID)\n"  +
                ");";
        String sqlProduct = "CREATE TABLE Product(\n" +
                "\tProductID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\tProductName TEXT NOT NULL ,\n" +
                "\tImage INTEGER  NOT NULL,\n" +
                "\tprice REAL NOT NULL,\n" +
                "\tCategoryID INTEGER  NOT NULL,\n" +
                "\tDescription TEXT ,\n" +
                "\tFOREIGN KEY (CategoryID) REFERENCES Category(ID)\n" +
                ");";
        String sqlOrderDetail = "CREATE TABLE OrderDetail(\n" +
                "\tDetailID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\tOrderID INTEGER  NOT NULL,\n" +
                "\tProductID INTEGER  NOT NULL,\n" +
                "\tquantity INTEGER  NOT NULL,\n" +
                "\tFOREIGN KEY (ProductID) REFERENCES Product(ProductID),\n" +
                "\tFOREIGN KEY (OrderID) REFERENCES [Order](OrderID)\n" +
                ");";
        String sqlCart = "CREATE TABLE Cart(\n" +
                "\tCartID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\tUserID INTEGER  NOT NULL,\n" +
                "\tProductID INTEGER  NOT NULL,\n" +
                "\tquantity INTEGER  NOT NULL,\n" +
                "\tFOREIGN KEY (ProductID) REFERENCES Product(ProductID),\n" +
                "\tFOREIGN KEY (UserID) REFERENCES User(ID)\n" +
                ");";
        // execute sql
        sqLiteDatabase.execSQL(sqlUser);
        sqLiteDatabase.execSQL(sqlCategory);
        sqLiteDatabase.execSQL(sqlProduct);
        sqLiteDatabase.execSQL(sqlOrder);
        sqLiteDatabase.execSQL(sqlOrderDetail);
        sqLiteDatabase.execSQL(sqlCart);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
