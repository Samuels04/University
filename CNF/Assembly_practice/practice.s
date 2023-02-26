//
//  practice.s
//  Assembly_practice
//
//  Created by Samuel Sánchez González on 25/02/2023.
//

;Exercise_1
mov r1, r0;

;Exercise_2
movl r1, 45;
movh r1, 00;

;Exercise_3
movl r1, 10h;
movh r1, 20h;

mov r0, [r1];

;Exercise_4
movl r0, 56h;
movh r0, 34h;

mov r1, [r0]
mov r1, r3;

;Exercise_5
movl r0, 56h;
movh r0, 34h;

mov r1, [r0];

movl r2, 12h;
movh r2, ABh;

mov r3, [r2];

mov r1, r3;

;Exercise_6

add r0, r1, 5;

;Exercise_7

movl r2, 67h;
movh r2, 45h;

mov r3, [r2];

sub r0, r1, r3;

;Exercise_8

movl r3, 00h;
movh r3, 10h;

mov r0, [r3];

add r4, r1, r2;

movl r5, 00h;
movh r5, 04h;

mov r6, [r5];

sub r7, r0, r6;

sub r8, r4, r7;
