package com.example.foodapp.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.foodapp.R;
import androidx.annotation.Nullable;

public class ConnectDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PRM392_APP_FOOD_PROJECT";
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
                "INSERT INTO [Order](UserID,OrderDate,ShipDate,Status,Address) VALUES(3,'2023-02-17','2023-02-18','Shipping','0341 Everett Court')\n",
        };
        for(String sql : sqlInsert){
            sqLiteDatabase.execSQL(sql);
        }
    }
    private void InsertProduct(SQLiteDatabase sqLiteDatabase){
        String[] sqlInsert ={
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Nam Can crab','https://toplist.vn/images/800px/cua-nam-can-ca-mau-133357.jpg',10.00,1,NULL)\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Lobster','https://toplist.vn/images/800px/tom-hum-binh-ba-khanh-hoa-133355.jpg',16.00,1,'Lobster in Binh Ba, in addition to the greasy and sweet taste, because the shrimp is still fresh when eaten with green chili salt here, it blends even more.')\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Tuna','https://toplist.vn/images/800px/ca-ngu-dai-duong-phu-yen-133360.jpg',12.25,1,'Tuna is a particularly delicious seafood, very nutritious, processed into a variety of delicious dishes and created a valuable source of export goods.')\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Abalone','https://toplist.vn/images/800px/bao-ngu-bach-long-vy-hai-phong-834994.jpg',13.00,1,'This is a typical product, a special species that brings high economic efficiency of this island district, is a source of nutritious food, a precious medicine, is considered the best quality and is one of the seafood specialties. of Vietnam is comparable to the famous abalone in many countries around the world')\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Chicken noodle soup','https://cdn.tgdd.vn/2021/06/content/phoga-800x450.jpg',17.75,2,'Chicken pho broth has a light yellow color but still retains the level of Vietnamese pho standards. The noodle soup is soft, submerged in a sweet broth, fragrant with ginger, cinnamon, and anise.')\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Fried noodle','https://cdn.tgdd.vn/2021/06/content/phoxao-800x450.jpg',16.75,2,'Stir-fried Pho with soft, sweet pieces of beef, mixed with bok choy, carrots, onions, fresh, crispy and sweet, add a little cilantro, fragrant scallions.')\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Crab fried rice','https://toplist.vn/images/800px/com-rang-cua-26877.jpg',18.75,4,NULL)\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Fried rice with sausage and shrimp','https://toplist.vn/images/800px/com-rang-lap-xuong-va-tom-836726.jpg',12.75,4,NULL)\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Kimchi fried rice','https://toplist.vn/images/800px/com-rang-kim-chi-26882.jpg',13.5,4,NULL)\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Orange juice','https://www.acouplecooks.com/wp-content/uploads/2021/02/Painkiller-Cocktail-008.jpg',6.25,5,NULL)\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Coca cola','https://sieuthibianhap.vn/wp-content/uploads/2021/07/Coca-Cola-Nhat-Chai-300ml-Thung-24-Lon-2.jpg',5.75,5,NULL)\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Milk tea','https://mccoffeetanphu.com/uploads/source/mccoffee/b45-3-min.jpg',5.55,5,NULL)\n",
                "INSERT INTO Product(ProductName,Image,price,CategoryID,description) VALUES('Lemon juice','https://static.toiimg.com/thumb/msid-74712583,imgsize-219657,width-400,resizemode-4/74712583.jpg',5.25,5,NULL)\n",
        };
        for(String sql : sqlInsert){
            sqLiteDatabase.execSQL(sql);
        }
    }

    private void InsertCategory(SQLiteDatabase sqLiteDatabase){
        String[] sqlInsert = {
               "INSERT INTO Category ([Name], Image) VALUES ('Seafood', "+R.drawable.seafood+")\n",
               "INSERT INTO Category ([Name], Image) VALUES ('Phở',"+R.drawable.noodle+"\n)",
               "INSERT INTO Category ([Name], Image) VALUES ('Burger',"+R.drawable.logo+")\n",
               "INSERT INTO Category ([Name], Image) VALUES ('Pizza',"+R.drawable.pizza+")\n",
               "INSERT INTO Category ([Name], Image) VALUES ('Drink',"+R.drawable.cat_4+")\n"
        };
        for(String sql : sqlInsert){
            sqLiteDatabase.execSQL(sql);
        }
    }

    private void InsertUser(SQLiteDatabase sqLiteDatabase){
        String[] sqlInsert = {
                "INSERT INTO User(FullName,phone,email,gender,username,password,RoleName,Money,Address) VALUES('Kirk Nelson','4533389559','oparagreen0@usnews.com','Female','Kirk','999@','Seller',123.5,'130 Nguyen Van Cu')\n",
                "INSERT INTO User(FullName,phone,email,gender,username,password,RoleName,Money,Address) VALUES('Nguyen Thi Thu','4533389559','oparagreen0@usnews.com','Female','ThuThu','123@','Customer',130,'300 Bach Mai')\n",
                "INSERT INTO User(FullName,phone,email,gender,username,password,RoleName,Money,Address) VALUES('Nguyen Anh Tuan','6298446654','kfleet1@artisteer.com','Male','AnhTuan','123@','Customer',150.5,'74 Phuong Mai')\n",
                "INSERT INTO User(FullName,phone,email,gender,username,password,RoleName,Money,Address) VALUES('Chu Quang Quan','8851738015','fellcock2@earthlink.net','Male','QuangQuan','123@','Customer',140,'200 Giai Phong')\n",
                "INSERT INTO User(FullName,phone,email,gender,username,password,RoleName,Money,Address) VALUES('Nguyen Minh Duc','5541282702','bkervin4@fotki.com','Male','MinhDuc','123@','Customer',200,'100 Dai Co Viet')\n",
                "INSERT INTO User(FullName,phone,email,gender,username,password,RoleName,Money,Address) VALUES('Nicky Gaitone','7583151589','ngaitone6@cyberchimps.com','Female','Nicky','123@','Customer',500,'189 Nguyen Van Cu')\n"
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
                "\tGender TEXT NOT NULL, \n" +
                "\tUsername TEXT NOT NULL,\n" +
                "\tPassword TEXT NOT NULL,\n" +
                "\tRoleName TEXT NOT NULL,\n" +
                "\tMoney REAL, \n" +
                "\tAddress TEXT \n" +
                ");";
        String sqlCategory = "CREATE TABLE Category(\n" +
                "\tID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\tName TEXT NOT NULL,\n" +
                "\tImage INTEGER NOT NULL);";
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
                "\tImage TEXT  NOT NULL,\n" +
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
