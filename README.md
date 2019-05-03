# Tiny-Parser
This a tiny parser using JAVA
The parser uses a scanner from https://github.com/mohamedsameh1998/tiny-compiler
The parser uses java swing and some classes from java.util
The documentation for the project is yet to come.

# Usage
download the executable folder https://github.com/mohamedsameh1998/Tiny-Parser/tree/master/executable
Follow the **readme.md** for extra instructions

# Demo
### 1. GUI
![image](https://user-images.githubusercontent.com/34913464/57158394-95d0be80-6de3-11e9-99e5-261b90363069.png)

### 2. text output
```
entered program
entered stmt_seq
entered statement
entered read_stmt
read
x
;
entered statement
entered if_stmt
if
entered exp
entered simple_exp
entered term
entered factor
0
entered comparison_op
<
entered simple_exp
entered term
entered factor
x
then
entered stmt_seq
entered statement
entered assign_stmt
fact
:=
entered exp
entered simple_exp
entered term
entered factor
1
;
entered statement
entered repeat_stmt
repeat
entered stmt_seq
entered statement
entered assign_stmt
fact
:=
entered exp
entered simple_exp
entered term
entered factor
fact
entered mulop
*
entered factor
x
;
entered statement
entered assign_stmt
x
:=
entered exp
entered simple_exp
entered term
entered factor
x
entered addop
-
entered term
entered factor
1
until
entered exp
entered simple_exp
entered term
entered factor
x
entered comparison_op
=
entered simple_exp
entered term
entered factor
0
;
entered statement
entered write_stmt
write
entered exp
entered simple_exp
entered term
entered factor
fact
end

```
