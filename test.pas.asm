        .data
    _MSG0 .asciiz "Type a number:"
    _MSG1 .asciiz "Factorial of "
    _MSG2 .asciiz " is "
    _number  .word 0
    _result  .word 0
        .text
        .globl factorial
factorial:
    move $fp,$sp
    b _factorial_b
_fact:
    sw $fp,-4($sp)
    sw $ra,-8($sp)
    sw $s0,-12($sp)
    move $fp,$sp
    sub $fp,$fp,20
    move $s0,$a0
    b _fact_b
_fact_b:
    li $t0,1
    sw $t0,_fact
    li $t0,1
    sw $t0,-20($sp)
    li $t0,1
    bgt $s0,$t0,ETIQ1
    b _ETIQ0
ETIQ1:
    li $t0,2
    sw $t0,-16($sp)
ETIQ2:
    blt -16($sp),$s0,ETIQ3
    b _ETIQ0
ETIQ4:
    li $t0,1
    add -16($sp),-16($sp),$t0
    b _ETIQ2
ETIQ3:
    move $t0,_t0
    mul $t0,-20($sp),-16($sp)
    move $t1,_t0
    sw $t1,-20($sp)
    b _ETIQ0
    b _ETIQ0
ETIQ0:
    sw -20($sp),_fact
    move $sp,$fp
    sw $ra,-8($sp)
    sw $fp,-4($sp)
    jr $ra
_factorial_b:
    li $v0,4
    la $a0,_MSG0
    syscall
    move $t1,_number
    li $v0,5
    syscall
    sw $v0,$t1
    lw $a0,_number
    jal _fact
    sw $v0,_result
    li $v0,4
    la $a0,_MSG1
    syscall
    move $t1,_number
    li $v0,1
    la $a0,$t1
    syscall
    li $v0,4
    la $a0,_MSG2
    syscall
    move $t1,_result
    li $v0,1
    la $a0,$t1
    syscall
    li $v0,10
    syscall
