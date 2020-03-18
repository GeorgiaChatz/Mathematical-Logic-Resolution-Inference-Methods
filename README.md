# Mathematical Logic Resolution Inference Methods

It is implementation of project in Artificial Intelligence course.

-Resolution Inference Methods for mathematical logic using resolution for CNF and Horn clauses.

# Project Informations

* First Part 
   * Exporting conclusions with **resolution** for propositional logic
   * _Example_
       *  1. (!p21 V b11 )^(b11 V p12 V p21)^(!p12 V b11)^(b11))
    

* Second Part 
   * Exporting forward conclusions for Horn clauses (definitive Clauses) using **propositional logic**
    * _Example_
       *  P=>Q
          L^M=>P
          B^L=>M
          A^P=>L
          A^B=>L
          A
          B
 
* Third Part 
   * Exporting forward conclusions for Horn clauses (definitive Clauses) using **categorical logic**
   
   * _Example_
       *  American(x)^Weapon(y)^Sells(x,y,z)^Hostile(z)=>Criminal(x)
          Missile(M1)
          Owns(Nono,M1)
          Missile(x)^Owns(Nono,x)=>Sells(West,x,Nono)
          Missile(x)=>Weapon(x)
          Enemy(x,America)=>Hostile(x)
          American(West)
          Enemy(Nono,America)

