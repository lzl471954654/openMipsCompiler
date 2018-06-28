main:
    lui $1,0
    ori $4,$1,80
call:
    jal sum
dslot1:
    addi $5,$0,4
return:
    sw $2,$4,0
    lw $9,$4,0
    sub $8,$9,$4
    addi $5,$0,3
loop2:
    addi $5,$5,-1
    ori $8,$5,0xffff
    xori $8,$8,0x5555
    addi $9,$0,-1
    andi $10,$9,0xffff
    or $6,$10,$9
    xor $8,$10,$9
    and $7,$10,$6
    beq $5,$0,shift
dslot2:
    # nop
    j loop2
dslot3:
    # nop
shift:
    addi $5,$0,-1
    sll $8,$5,15
    sll $8,$8,16
    sra $8,$8,16
    srl $8,$8,15
finish:
    j finish
dslot4:
    # nop
sum:
    add $8,$0,$0
loop:
    lw $9,$4,0
    add $8,$8,$9
    addi $5,$5,-1
    bne $5,$0,loop
dslot5:
    addi $4,$4,4
    jr $31
dslot6:
    sll $2,$8,0