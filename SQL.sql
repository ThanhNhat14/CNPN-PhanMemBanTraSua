DROP DATABASE IF EXISTS TRASUA_TASU;
CREATE DATABASE  TRASUA_TASU;

USE TRASUA_TASU;

DROP TABLE IF EXISTS Account;
CREATE TABLE Account(
	idAccount int IDENTITY(1, 1) PRIMARY KEY,
	fullName nvarchar(100) NOT NULL,
	cccd nvarchar(12) NOT NULL,
	userName nvarchar(30) UNIQUE NOT NULL,
	password nvarchar(400) NOT NULL,
	birthday nvarchar(12) NOT NULL,
	address nvarchar(100) NOT NULL,
	permission nvarchar(15) NOT NULL,
	gender int NOT NULL,
	salary bigint NOT NULL,
	numberPhone nvarchar(10) NOT NULL,
	avatar nvarchar(300),
);

DROP TABLE IF EXISTS Category;
CREATE TABLE Category(
	idCategory int IDENTITY(1, 1) PRIMARY KEY,
	nameCategory nvarchar(100) NOT NULL,
	statusCategory bit,
) 

DROP TABLE IF EXISTS Voucher;
CREATE TABLE Voucher(
	idVoucher int IDENTITY(1, 1) PRIMARY KEY,
	codeVoucher nvarchar(10) NOT NULL,
	percentDiscount int NOT NULL,
	toCost int NOT NULL,
	statusVoucher bit,
) 

DROP TABLE IF EXISTS Product;
CREATE TABLE Product(
	idProduct int IDENTITY(1, 1) PRIMARY KEY,
	nameProduct nvarchar(100) NOT NULL,
	imageProduct nvarchar(300),
	idCategory int NOT NULL,
	description nvarchar(1000) NOT NULL,
	statusProduct bit,
    FOREIGN KEY (idCategory) REFERENCES Category(idCategory)
)



DROP TABLE IF EXISTS Customer;
CREATE TABLE Customer(
	idCustomer int IDENTITY(1, 1) PRIMARY KEY,
	nameCustomer nvarchar(50) NOT NULL,
	numberPhoneCustomer nvarchar(10),
	userName nvarchar(30) NOT NULL,
	password nvarchar(400) NOT NULL,
	addressCustomer nvarchar(100) NOT NULL,
	gender int NOT NULL,
	birthday nvarchar(12)
)

DROP TABLE IF EXISTS Size;
CREATE TABLE Size(
	idSize int IDENTITY(1, 1) PRIMARY KEY,
	nameOfSize nvarchar(20) NOT NULL,
)

DROP TABLE IF EXISTS ProductSize;
CREATE TABLE ProductSize(
	idProductSize int IDENTITY(1, 1) PRIMARY KEY,
	idProduct int NOT NULL,
	idSize int not null,
	price int not null,
	statusProductSize bit,
	FOREIGN KEY (idProduct) REFERENCES Product(idProduct),
	FOREIGN KEY (idSize) REFERENCES Size(idSize)
)


DROP TABLE IF EXISTS Topping;
CREATE TABLE Topping(
	idTopping int IDENTITY(1, 1) PRIMARY KEY,
	nameTopping nvarchar(30) NOT NULL,
	priceTopping int NOT NULL,
	imageTopping nvarchar(300),
	statusTopping bit,
)


DROP TABLE IF EXISTS Item;
CREATE TABLE Item(
	idItem int IDENTITY(1, 1) PRIMARY KEY,
	idItemDetail int not null,
	idProductSize int not null,
	idTopping int,
	quantityTopping int,
	FOREIGN KEY (idTopping) REFERENCES Topping(idTopping),
	FOREIGN KEY (idProductSize) REFERENCES ProductSize(idProductSize),
)

DROP TABLE IF EXISTS Bill;
CREATE TABLE Bill(
	idBill int IDENTITY(1, 1) PRIMARY KEY,
	idAccount int NOT NULL,
	dateOrder nvarchar(25) NOT NULL,
	deliveryAddress nvarchar(100) NOT NULL,
	note nvarchar(100),
	totalAmount int NOT NULL,
	totalPrice int NOT NULL,
	idVoucher int,
	statusPurchase nvarchar(50) NOT NULL,
	paymentMethod nvarchar(50) NOT NULL,
	idCustomer int,
	cancelReason nvarchar(100),
	FOREIGN KEY (idAccount) REFERENCES Account(idAccount),
	FOREIGN KEY (idVoucher) REFERENCES Voucher(idVoucher),
	FOREIGN KEY (idCustomer) REFERENCES Customer(idCustomer)
)

DROP TABLE IF EXISTS BillDetail;
CREATE TABLE BillDetail(
	idBillDetail int IDENTITY(1, 1) PRIMARY KEY,
	idBill int not null,
	idItem int NOT NULL,
	quantityProduct int NOT NULL,
	FOREIGN KEY (idBill) REFERENCES Bill(idBill),
	FOREIGN KEY (idItem) REFERENCES Item(idItem),
)

DROP TABLE IF EXISTS Ingredient;
CREATE TABLE Ingredient(
	idIngredient int IDENTITY(1, 1) PRIMARY KEY,
	nameIngredient nvarchar(150) NOT NULL,
	unitIngredient nvarchar(20) NOT NULL,
	quantityIngredient float NOT NULL,
	statusIngredient bit,
)



DROP TABLE IF EXISTS Supplier;
CREATE TABLE Supplier(
	idSupplier int IDENTITY(1, 1) PRIMARY KEY,
	nameSupplier nvarchar(150) NOT NULL,
	representPerson nvarchar(100),
	numberPhoneSupplier nvarchar(10) not null,
	description nvarchar(300),
	address nvarchar(100) not null,
	statusSupplier bit,
)



DROP TABLE IF EXISTS BillImportIngredient;
CREATE TABLE BillImportIngredient(
	idBillImportIngredient int IDENTITY(1, 1) PRIMARY KEY,
	idAccount int NOT NULL,
	dateCreate nvarchar(25) NOT NULL,
	idSupplier int not null,
	totalPrice int,
	FOREIGN KEY (idAccount) REFERENCES Account(idAccount),
	FOREIGN KEY (idSupplier) REFERENCES Supplier(idSupplier),

)

DROP TABLE IF EXISTS BillImportIngredientItem;
CREATE TABLE BillImportIngredientItem(
	idBillImportIngredientItem int IDENTITY(1, 1) PRIMARY KEY,
	idBillImportIngredient int not null,
	idIngredient int NOT NULL,
	quantity int not null,
	priceImport int not null,
	FOREIGN KEY (idBillImportIngredient) REFERENCES BillImportIngredient(idBillImportIngredient),
	FOREIGN KEY (idIngredient) REFERENCES Ingredient(idIngredient),

)

INSERT INTO Account (fullName, cccd, userName, password, birthday, address, permission, gender, salary, numberPhone)
VALUES (N'Võ Hồng Nguyên', '001103108701', 'hongnguyen', '6f6c9c051e1146b2d54e5d728fdc95758165dc0bd2f1115e13361b9efff56a89', '05/02/2003', N'Bình Định', 'MANAGER', 1, 100000000, '0564689470'),
		(N'Võ Văn Ngọc', '079103108778', 'vanngoc', '6f6c9c051e1146b2d54e5d728fdc95758165dc0bd2f1115e13361b9efff56a89', '01/01/2003', N'Bình Định', 'EMPLOYEE', 1, 100000000, '0564689471'),
		(N'Hồ Thanh Nhật', '079103102378', 'thanhnhat', '6f6c9c051e1146b2d54e5d728fdc95758165dc0bd2f1115e13361b9efff56a89', '02/01/2003', N'Long An', 'EMPLOYEE', 1, 100000000, '0564689472'),
		(N'Ngô Hoàng Phan', '001103108778', 'hoangphan', '6f6c9c051e1146b2d54e5d728fdc95758165dc0bd2f1115e13361b9efff56a89', '01/01/2001', N'Hà Nội', 'EMPLOYEE', 1, 100000000, '0564689473')

		INSERT INTO Customer(nameCustomer, numberPhoneCustomer, userName, password, addressCustomer, gender, birthday)
VALUES (N'', '1234567890', 'ngoc', '6f6c9c051e1146b2d54e5d728fdc95758165dc0bd2f1115e13361b9efff56a89', N'Số nhà, Phường/Xã, Quận/Huyện, Thành phố Hồ Chí Minh', 1, '')

		INSERT INTO Category (nameCategory, statusCategory)
VALUES (N'Trà Sữa', 1),
		(N'Black Tea', 1),
		(N'Nước Ép', 1),
		(N'Trà Trái Cây', 1),
		(N'Milo Dầm', 1)

	   INSERT INTO Size (nameOfSize)
VALUES (N'M'),
	   (N'L')

		INSERT INTO Product (nameProduct, imageProduct, idCategory, description, statusProduct)
VALUES (N'Trà sữa Truyền Thống', '/img/products/tra-sua-chocolate-cao-cap-43961616125427.JPG', 1, N'Với vị Trà hảo hạng quyện cùng Sữa tạo nên sự kết hợp hài hòa, lôi cuốn. Truyền Thống lúc nào cũng là lựa chọn hàng đầu cho mọi cuộc gặp gỡ.', 0),
	   (N'Trà sữa Matcha', '/img/products/tra-sua-cacao-23891616125721.JPG', 1, N'Vị Trà Xanh nhẫn, đậm len lỏi vào mọi giác quan ngay từ những ngụm đầu tiên, tạo cảm giác tươi mới sảng khoái chỉ muốn thưởng thức mãi không nguôi.', 1),
	   (N'Trà sữa Khoai Môn', '/img/products/tra-sua-chocolate-52461616126267.JPG', 3, N'Mãn nhãn với sắc tím ấn tượng, sự kết hợp tinh tế của Khoai Môn và Trà Sữa cho ra đời hương vị mang sức hút mãnh liệt nhưng pha chút dịu dàng, nhẹ nhàng.', 1),
	   (N'Trà sữa Matcha Machiato', '/img/products/matcha-machiato-23061616126590.JPG', 3, N'Trà Sữa Matcha thơm lừng phối hợp hoàn hảo với lớp Milkfoarm mằn mặn, thơm béo tạo cảm giác ngất ngây, mê say ngay từ ngụm đầu tiên.', 1),
	   (N'Trà sữa Trân Châu Macchiato', '/img/products/milktea-macchiato-78421616126852.jpg', 1, N'Ngào ngạt hương thơm của trà và vị thanh béo của sữa, quyện cùng lớp foarm sánh mịn, ngọt ngào. Là thức uống luôn được các bạn trẻ ưu ái lựa chọn mỗi khi đến Chin.', 1),
	   (N'Trà Sữa Chocolate Macchiato', '/img/products/macchiato-chocolate-88991616126984.JPG', 1, N'Lớp Macchiato mịn béo càng được cảm nhận rõ nét hơn khi kết hợp cùng vị Chocolate trứ danh. Mang đến cảm giác ngọt ngào, beo béo tự nhiên.', 1),
	   (N'Trà sữa Chocolate', '/img/products/tra-sua-thach-dao-3831616125859.JPG', 1, N'Chocolate đăng đắng kết hợp cùng Sữa ngot ngào, không chỉ giúp làm xua đi cảm giác ''khó chịu vô lý'' mà còn tăng hứng khởi cho ngày dài thêm năng động.', 1),
	   (N'Trà sữa Bạc Hà', '/img/products/tra-sua-dao-6411616126096.JPG', 1, N'The mát - Bắt mắt là hai tính từ miêu tả chân thật nhất cho Trà Sữa Bạc Hà. The mát nơi đầu lưỡi, lan tỏa hương thơm khắp vị giác và cuối cùng là dự vị ngọt ngào đọng lại trong tim.', 1),
	   (N'Trà sữa Caramel', '/img/products/tra-sua-dau-tay-58891616125982.JPG', 2, N'Sốt Caramel quả là một người bạn hoàn hảo cho Trà Sữa khi mang đến một thức uống lan tỏa, nức vị trong mỗi lần hòa quyện.', 1),
(N'Sữa tươi Trân Châu Đường Đen', '/img/products/hong-tra-special-66001616128002.JPG', 5, N'Trân châu được nấu với Đường Đen, tạo nên một hỗn hợp huyền bí, óng ánh, ngọt ngào. Mặt khác, Sữa Tươi thượng hạng thì thuần khiết, trong trắng, thanh vị. Sự kết hợp đối lập nhưng là một phép tính hài hòa, thơm lừng, thu hút.', 1),
	   (N'Chocolate Trân Châu Đường Đen', '/img/products/sua-tuoi-tran-chau-duong-den-special-13771616128155.JPG', 5, N'Phiên bản đặc biệt của Đường Đen, giới hạn của sự phá cách. Kết hợp thêm Chocolate hảo hạng, một công đôi việc cho những thực khách vừa yêu sự ngọt ngào của Đường Đen, vừa mê tít cảm giác đăng đắng quyến rũ của Chocolate.', 1),
	   (N'Trà Thạch Đào', '/img/products/cacao-sua-special-43851616127873.JPG', 4, N'Một thức uống thuần Đào khi bao gồm: Trà Đào thơm ngát, Miếng Đào giòn tan và Thạch Đào núng nính. Không ngoa khi nói đây là một trong những thức uống giải nhiệt hàng đầu tại Chin.', 1),
	   (N'Trà Vải Thiều', '/img/products/tra-vai-thieu-5981616127620.JPG', 4, N'Trái Vải Thiều căng tràn, mọng nước được Chin tiếp thêm sức mạnh bằng vị trà ngào ngạt, mang đến cảm giác lan tỏa, thanh mát khi thưởng thức mà vẫn giữ nguyên được hương vị trứ danh trong từng quả vải.', 1),
	   (N'Trà Dâu Tây Macchiato', '/img/products/tra-khoai-mon-macchiato-28351616127216.jpg', 4, N'Đắm chìm trong hương vị trà Dâu Tây chua chua, thơm lừng quyện cùng lớp Foarm mịn màng, sóng sánh. Mang đến một trải nghiệm khác biệt cho dòng Macchiato.', 1),
	   (N'Hồng Trà Macchiato', '/img/products/chocolate-macchiato-48741616127116.JPG', 2, N'Sự bù trừ hoàn hảo giữa vị thanh thanh, đắng nhẹ của Hồng Trà và thơm béo, ngọt ngào của lớp Macchiato đã cho ra đời một thức uống hài hòa, tròn vị.', 1),
	   (N'Trà Đào Macchiato', '/img/products/tra-dao-macchiato-10791616127309.JPG', 4, N'Hương vị đặc trưng của Trà Đào nguyên bản được Chin kết hợp cùng lớp Macchiato bồng bềnh, thêm một ít Thịt Đào tươi nhuyễn và Trân Châu hương Đào sẵn có, bùng nổ mọi vị giác ngay từ những giây đầu tiên. Thử ngay nào!', 1)

	   INSERT INTO Topping (nameTopping, priceTopping, imageTopping, statusTopping)
VALUES (N'Ngọc Trai', 7000, '/img/images/topping-hat-sen-41511615605843.jpg', 1),
	   (N'Trân Châu Hoàng Kim', 4000, '/img/images/thach-mon-66361653662231.JPG', 1),
	   (N'Rau câu Sơn Thủy', 3000, '/img/images/topping-hat-sen-11001615605976.jpg', 1),
	   (N'Khúc bạch', 3000, '/img/images/topping-hat-sen-82891615605929.jpg', 1),
	   (N'Phô Mai', 3000, '/img/images/topping-hat-sen-82891615605929.jpg', 1),
	   (N'Flan Trứng', 4000, '/img/images/topping-hat-sen-24021615605948.jpg', 1),
	   (N'Jelly Đào', 3000, '/img/images/topping-hat-sen-24021615605948.jpg', 1),
	   (N'Topping Sương sáo', 3000, '/img/images/topping-hat-sen-82891615605929.jpg', 1),
	   (N'Củ Năng', 5000, '/img/images/topping-hat-sen-82891615605929.jpg', 0),
	   (N'Thạch môn', 3000, '/img/images/topping-hat-sen-82891615605929.jpg', 1)

		INSERT INTO ProductSize(idProduct, idSize, price, statusProductSize)
VALUES (1, 1, 23000, 1),
	   (1, 2, 26000, 1),
	   (2, 1, 26000, 1),
	   (2, 2, 30000, 1),
	   (3, 1, 28000, 1),
	   (3, 2, 32000, 1),
	   (4, 1, 26000, 1),
	   (4, 2, 30000, 1),
	   (5, 1, 25000, 1),
	   (5, 2, 28000, 1),
	   (6, 1, 26000, 1),
	   (6, 2, 30000, 1),
	   (7, 1, 26000, 1),
	   (7, 2, 28000, 1),
	   (8, 1, 25000, 1),
	   (8, 2, 28000, 1),
	   (9, 1, 25000, 1),
	   (9, 2, 29000, 1),
	   (10, 1, 27000, 1),
	   (10, 2, 30000, 1),
	   (11, 1, 27000, 1),
	   (12, 1, 30000, 1),
	   (13, 1, 27000, 1),
	   (14, 1, 30000, 1),
	   (15, 1, 27000, 1),
	   (16, 1, 30000, 1)

INSERT INTO Supplier(nameSupplier, representPerson, numberPhoneSupplier, description, address, statusSupplier)
			VALUES(N'Công ty TNHH Sơn La', N'Trương Văn Thành', '0356781902', N'Công ty chuyên cung cấp trân châu', N'97 Man Thiện, Phường Hiệp Phú, TP HCM', 1),
				(N'Công ty thực phẩm Tula', N'Hồ Hải Nguyên', '0235617892', N'Cung cấp sữa các loại', N'101 Nguyễn Thị Minh Khai, Quận 1, TP HCM', 1),
				(N'Công ty TNHH Bình Đinh', N'Nguyễn Thanh Thủy', '0786152671', N'Cung cấp các sản phẩm về bột trà sữa', N'101 Hồ Hữ Khanh, TP HCM', 1),
				(N'Công ty sữa Đà Nẵng', N'Phạm Thành Danh', '0245617892', N'Chuyên cung cấp sữa đặc các loại', N'25 Phan Châu TRinh, TP Đà Nẵng', 1)

				INSERT INTO Ingredient(nameIngredient, unitIngredient, quantityIngredient, statusIngredient)
	VALUES(N'Đường đen', N'kg', 0, 1),
			(N'Siro vải', N'Lít', 0, 1),
			(N'Sữa tươi', N'Hộp', 0, 1),
			(N'Chất tạo hương', N'Lít', 0, 1)

	INSERT INTO Voucher(codeVoucher, percentDiscount, toCost, statusVoucher)
VALUES (N'TASU5', 5, 200000, 1),
		(N'TASU10', 10, 400000, 1),
		(N'TASU15', 15, 600000, 1)