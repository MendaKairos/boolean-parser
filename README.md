# boolean-parser
Contains method wich takes a boolean expression string composed of 
true and false, and evaluates it to a final value.

example 
((false&true)>true)=true evaluates to true

   * conjunction/And   &
	 * disjunction/Or    |
	 * not      -
	 * implication >
	 * equivalence  =
	 * XOR  ^
 
TruthTable object

all varibles need to be enclosed in [] 

ignose varible Names cases( [a]== [A], [Cd]==[cD])



example takes (([a]&[b])>[c])=[d]
toString method produces:
[a] [b] [c] [d] : (([a]&[b])>[c])=[d]
false false false false : ((false&false)>false)=false : false
true false false false : ((true&false)>false)=false : false
false true false false : ((false&true)>false)=false : false
true true false false : ((true&true)>false)=false : true
false false true false : ((false&false)>true)=false : false
true false true false : ((true&false)>true)=false : false
false true true false : ((false&true)>true)=false : false
true true true false : ((true&true)>true)=false : false
false false false true : ((false&false)>false)=true : true
true false false true : ((true&false)>false)=true : true
false true false true : ((false&true)>false)=true : true
true true false true : ((true&true)>false)=true : false
false false true true : ((false&false)>true)=true : true
true false true true : ((true&false)>true)=true : true
false true true true : ((false&true)>true)=true : true
true true true true : ((true&true)>true)=true : true





