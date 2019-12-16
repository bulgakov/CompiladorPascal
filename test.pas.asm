        .data
    _MSG0: .asciiz "Type a number:"
    _MSG1: .asciiz "Factorial of "
    _MSG2: .asciiz " is "
    _number:  .word 0
    _result:  .word 0
        .text
        .globl main
main:
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
    bgt $s0,$t0,_ETIQ1
    b _ETIQ0
_ETIQ1:
    li $t0,2
    sw $t0,-16($sp)
_ETIQ2:
    lw $t0,-16($sp)
    blt $t0,$s0,_ETIQ3
    b _ETIQ0
_ETIQ4:
    li $t0,1
    lw $t1,-16($sp)
    lw $t2,-16($sp)
    add $t1,$t2,$t0
    sw $t1,-16($sp)
    b _ETIQ2
_ETIQ3:
    lw $t1,-20($sp)
    lw $t2,-16($sp)
    mul $t0,$t1,$t2
    sw $t0,-20($sp)
    b _ETIQ0
    b _ETIQ0
_ETIQ0:
    lw $t0,-20($sp)
    sw $t0,_fact
    move $sp,$fp
    sw $ra,-8($sp)
    sw $fp,-4($sp)
    jr $ra
_factorial_b:
    li $v0,4
    la $a0,_MSG0
    syscall
    lw $t0,_number
    li $v0,5
    syscall
    move $v0,$t0
    lw $a0,_number
    jal _fact
    sw $v0,_result
    li $v0,4
    la $a0,_MSG1
    syscall
    lw $t0,_number
    li $v0,1
    move $a0,$t0
    syscall
    li $v0,4
    la $a0,_MSG2
    syscall
    lw $t0,_result
    li $v0,1
    move $a0,$t0
    syscall
    li $v0,10
    syscall
