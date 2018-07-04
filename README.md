# openMipsCompiler MIPS汇编编译器使用说明

## 指令格式说明

MIPS共有32个通用寄存器，使用每个寄存器需要在前面 加上英文的 $ 符号

例如0号寄存器 $0, 1号寄存器 $1



**R型指令**

```s

add $1,$2,$3

$1 = $2 + $3
第一个寄存器参数代表存放结果的寄存器
第二个和第三个寄存器参数代表第一个和第二个运算参数

```


**I型指令**

```s

addi $1,$2,0xffff

$1 = $2 + 0xffff

将寄存器2的值和立即数0xffff加和存放到寄存器1当中

lui $2,0xffff

将2号寄存器的高位置为0xffff，低位清零


lw $1,$2,0x0000

lw指令
将寄存器1中的值，写入到 以寄存器2中的地址为基址，以偏移量为0x0000的地址中去

sw $3,$4,0x0000

sw指令
将 寄存器4中的地址为基址，0x0000 为偏移量的地址中的值，读取到寄存器3中

```


**J型指令**

```s

main:
    addi $1,$2,0xffff
    lui $2,0xffff
    add $3,$1,$2
    j main


J型指令支持 段标记，格式为 段名称 + 冒号，例如 main:
段后的代码 需要重新开一行写

```



---

**nop指令**

nop指令为空指令，二进制格式为 32'b00000000000000000000000000000000

**注意：多级流水线CPU，如果发生跳转指令异常，可以尝试在跳转指令后面紧接着补充一条nop指令**


```s

main:
    addi $1,$2,0xffff
    lui $2,0xffff
    add $3,$1,$2
    j main
    nop
    addi $1,$2,0xffff

```


## 编译器使用方法

1. 解压openMipsCompiler压缩文件
2. 将代码放在code.txt中

![](https://github.com/lzl471954654/drawIO/blob/master/openMipsCompiler/openMipsCompiler_guid-1.jpg?raw=true)

3. 运行run.bat
4. 运行成功后界面会显示 Compiler Success!

![](https://github.com/lzl471954654/drawIO/blob/master/openMipsCompiler/openMipsCompiler_guid-2.jpg?raw=true)

5. 根目录下会生成一个新的.txt文件，里面存放的就是可以直接粘贴到vivado和modelsim中的代码（data 文件中存放的是纯二进制格式代码，一条指令4字节，文件大小 = 指令数 * 4）

![](https://github.com/lzl471954654/drawIO/blob/master/openMipsCompiler/openMipsCompiler_guid-3.jpg?raw=true)


## 问题反馈

如果有任何问题，欢迎大家提issues
