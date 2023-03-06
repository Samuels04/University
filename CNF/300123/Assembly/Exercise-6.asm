movl r0, 01h
movh r0, 05h
mov r1, [r0]; point r1 to a

inc r0
mov r2, [r0]; point r2 to b

inc r0
mov r3, [r0]; point r3 to c


movl r0, 02h
movh r0, 00h

add r4, r1, r2
add r5, r3, r0

sub r0, r4, r5
