movl r0, 01h
movh r0, 05h

mov r1, [r0]; point r1 to a

inc r1; a++

inc r0

mov r2, [r0]; point r2 to b

sub r2, r2, r1; b -= a

