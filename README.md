# Mathematical Logic Resolution Inference Methods

It is implementation of project in Artificial Intelligence course.

-Resolution Inference Methods for mathematical logic using resolution for CNF and Horn clauses.

# Project Informations

* First Part 
   * Exporting conclusions with **resolution** for propositional logic
   * _Example_ 
        1. (!p21 V b11 )^(b11 V p12 V p21)^(!p12 V b11)^(b11))
    

* Second Part 
   * Exporting forward conclusions for Horn clauses (definitive Clauses) using **propositional logic**
    * _Example_
        1.  P=>Q
        2.  L^M=>P
        3.  B^L=>M
        4.  A^P=>L
        5.  A^B=>L
        6.  A
        7.  B
 
* Third Part 
   * Exporting forward conclusions for Horn clauses (definitive Clauses) using **categorical logic**
   
   * _Example_
       1. American(x)^Weapon(y)^Sells(x,y,z)^Hostile(z)=>Criminal(x)
       2.  Missile(M1)
       3.  Owns(Nono,M1)
       4.  Missile(x)^Owns(Nono,x)=>Sells(West,x,Nono)
       5.  Missile(x)=>Weapon(x)
       6.  Enemy(x,America)=>Hostile(x)
       7.  American(West)
       8.  Enemy(Nono,America)

# Run Instractions

 * Run the Main Class
 * We have made a menu asking the user which of the three methods he wants to use and depending on his answer we call this  method.
