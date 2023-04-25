# Russian

## Итоговое домашнее задание 

На входе имеется несколько входных файлов, в которых указаны данные по товарам, продавцам, ценам и продажам:

```Файл с товарами:```

- ID товара
- Наименование товара

```Файл с продавцами:```

- ID продавца
- Фамилия продавца
- Имя продавца

```Файл с данными по наличию товаров у продавцов:```

- ID продавца
- ID товара
- Цена товара
- Количество товара в наличии у продавца

```Файл с продажами:```

- ID продажи
- ID продавца
- ID товара
- Количество проданных товаров
- Дата продажи

### Задание 1. 

|Вариант|Описание|
| :- | :- |
|0|Для каждого товара вывести в файл продавца, у которого в наличии наибольшее количество этого товара, и само количество этого товара у него в наличии|
|1|Для каждого товара вывести в файл продавца, у которого наименьшая цена на этот товар, и саму цену на этот товар у этого продавца|
|2|Для каждого товара вывести в файл общее количество товара этого типа в наличии|
|3|Для каждого товара вывести в файл общее количество проданных товаров этого типа|
|4|Вывести в файл топ 5 продавцов, продавших наибольшее количество товаров|
|5|Вывести в файл топ 5 товаров с наибольшим количеством продаж|

Задание 2:

|Вариант|Описание|
| :- | :- |
|0|Вывести в файл распределение общего количества продаж по датам|
|1|Вывести в файл топ 5 дат, в которые было продано наибольшее количество товаров|
|2|Вывести в файл среднее количество проданных товаров в день|

Форматы файлов:

|**Вариант**|**Входной файл**|**Выходной файл**|
| :- | :- | :- |
|0|XML|XML|
|1|XML|JSON|
|2|JSON|XML|
|3|JSON|JSON|
Структуру входных/выходных файлов нужно придумать самостоятельно.

### Вариант для данного случая
- Формат входного и выходного файла: ```JSON``` (Вариант III)
- Выполнены задания: ```ВСЕ```

### Используемые Библиотеки
- ![GSON](https://github.com/google/gson)
- ![SQLite JDBC Driver](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc)
- ![JetBrains Annotations](https://mvnrepository.com/artifact/org.jetbrains/annotations)




# English

## Final homework

At the input, there are several input files that contain data on goods, sellers, prices and sales:

```Products file:```

- Item ID
- Name of product

```File with vendors:```

- Vendor ID
- Seller's last name
- Seller's name

```File with data on the availability of goods from sellers:```

- Vendor ID
- Item ID
- The price of the product
- Quantity of goods in stock at the seller

```Sales file:```

- Sale ID
- Vendor ID
- Item ID
- Number of goods sold
- Date of sale

### Exercise 1.

|Option|Description|
| :- | :- |
|0|For each product, output to the file the seller who has the largest amount of this product in stock, and the amount of this product he has in stock|
|1|For each product, output to the file the seller who has the lowest price for this product, and the price for this product from this seller|
|2|For each product, output to the file the total quantity of this type of product in stock|
|3|For each item, output to file the total number of sold items of this type|
|4|Output to file the top 5 sellers who sold the largest number of goods|
|5|Output the top 5 products with the largest number of sales into a file|

Task 2:

|Option|Description|
| :- | :- |
|0|Output to file the distribution of the total number of sales by date|
|1|Output the top 5 dates in which the largest number of goods were sold|
|2|Output to file the average number of goods sold per day|

File formats:

|**Option**|**Input file**|**Output file**|
| :- | :- | :- |
|0|XML|XML|
|1|XML|JSON|
|2|JSON|XML|
|3|JSON|JSON|
The structure of the input / output files must be invented independently.

### Option for this case
- Input and output file format: ```JSON``` (Variant III)
- Completed tasks: ```ALL```

### Used Libraries
- ![GSON](https://github.com/google/gson)
- ![SQLite JDBC Driver](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc)
- ![JetBrains Annotations](https://mvnrepository.com/artifact/org.jetbrains/annotations)
