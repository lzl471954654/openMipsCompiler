    and $1,$1,$0
    and $2,$2,$0
    and $3,$3,$0
    lui $1,0xffff
    lui $2,0xffff
    lui $3,0xffff
    ori $1,$1,0xf004
    ori $2,$2,0xf016
    ori $3,$3,0xf012
    lui $4,0x0000
    lui $10,0x0000
    lui $11,0x0000
    andi $11,$11,1
    lui $12,0x0000
    andi $12,$12,2
readKey:
    lw $5,$3,0x0000
    and $6,$11,$5
    beq $6,$11,addNumber
    nop
    and $6,$12,$5
    beq $6,$12,subNumber
    nop
    j readKey
    nop
addNumber:
    addi $4,$4,1
    sw $4,$1,0
    sw $4,$2,0
    j readKey
    nop
subNumber:
    beq $4,$10,readKey
    nop
    addi $4,$4,-1
    sw $4,$1,0
    sw $4,$2,0
    j readKey
    nop