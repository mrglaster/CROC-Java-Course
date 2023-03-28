## HomeWork 4

### Russian

В текстовом файле слова могут быть разделены одним или несколькими пробелами, или символами перевода строки. Необходимо реализовать программу, считающую количество слов в файле и выводящую результат на экран. Путь к файлу задается первым аргументом командной строки (args[0]).
В случае, если аргумент не задан – кидать IllegalArgumentException. При ошибке открытия файла сообщать об этом в консоль без вывода стектрейса.
 
Пример:
``` 
[in]
Забыл   Панкрат  Кондратьевич домкрат,
А без домкрату ну  не  поднять на тракте трактор.
```
 
``` 
[out]
13
```


### English

In a text file, words can be separated by one or more spaces or newlines. It is necessary to implement a program that counts the number of words in a file and displays the result on the screen. The path to the file is given as the first command line argument (args[0]).
If the argument is not set, throw an IllegalArgumentException. If there is an error opening a file, report it to the console without outputting the stack trace.

Example:
``` 
[in]
Забыл   Панкрат  Кондратьевич домкрат,
А без домкрату ну  не  поднять на тракте трактор.
```
 
```
[out]
13
```
