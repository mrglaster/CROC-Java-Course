-- Таблица "Товары"
CREATE TABLE IF NOT EXISTS Products (
    ProductID  INT  PRIMARY KEY,
    ProductName VARCHAR(255) NOT NULL
);

-- Таблица "Продавцы"
CREATE TABLE IF NOT EXISTS Sellers (
    SellerID INT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL
);

-- Таблица "Наличие товаров"
CREATE TABLE IF NOT EXISTS Stock (
    SellerID INT NOT NULL,
    ProductID INT NOT NULL,
    Price DECIMAL(10, 2),
    Quantity INT NOT NULL,
    PRIMARY KEY (SellerID, ProductID),
    FOREIGN KEY (SellerID) REFERENCES Sellers(SellerID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Таблицы "Продажи"
CREATE TABLE IF NOT EXISTS Sales (
    SaleID INT PRIMARY KEY,
    SellerID INT,
    ProductID INT,
    QuantitySold INT,
    SaleDate DATE,
    FOREIGN KEY (SellerID) REFERENCES Sellers(SellerID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);


