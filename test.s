    and $1,$1,$0
    lui $1,0xffff
main:
    ori $1,$1,0xffff
    lui $2,0xffff
    ori $2,$2,0xf004
    nop
    nop
    nop
    nop
    nop
    sw  $1,$2,0x0000
    and $5,$5,$0
    nop
    nop
    nop
    nop
    nop
    jr  $5
    j main
    jal main
    lui $1,0xffff
    nop