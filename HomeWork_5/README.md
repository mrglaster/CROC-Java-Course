## Homework №5

### RUSSIAN
 
   В виде строки задан относительный путь в файловой системе, в котором:
       — "." означает текущую директорию;
       — ".." означает родительскую директорию по отношению к текущей;
       — "/" используется в качестве разделителя директорий.
 
   Реализовать функцию, выполняющую "нормализацию" заданного пути,
   то есть, удаляющую из него лишние директории с учетом переходов "." и "..".
 
   Пример:
   
   
 ```
   [in]
   
   "КРОК/task_5_2/src/./../../task_5_1/../../../мемы/котики"
   
   [out]
   
   ../мемы/котики"
  
  ```
  
### English
 
   A relative path in the file system is specified as a string, in which:
       — "." means the current directory;
       — ".." means the parent directory in relation to the current one;
       — "/" is used as a directory separator.
 
   Implement a function that "normalizes" the given path,
   that is, removing unnecessary directories from it, taking into account the transitions "." And "..".
 
   Example:
 
 
 ```
   [in]
   
   "CROC/task_5_2/src/./../../task_5_1/../../../memes/cats"
  
  [out]
  
  ../memes/cats"
       
 ```     
