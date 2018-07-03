    and $1,$1,$0
    lui $1,0xffff
    ori $1,$1,0xffff
    lui $2,0xffff
    ori $2,$2,0xf004
    sw  $1,$2,0x0000
    and $5,$5,$0
    jr  $5
    nop