#Usage directions
1. write the tiny program in the file tiny.txt
2. open Parser.jar and the syntax tree appears on the screen
3. 2 output files are prodeuced:
	- **scanned.txt** contains the scanned tokens
	- **parsed.txt** ccontains the Parser output

# Error behaviour
errors are caught but not corrected and they are visible in the output file in the form (expecting x not y).
However errors do propagate the way they do in all compilers so there is no guarantee that the part following the error will be parsed correctly. The syntax tree correctness is guaranteed for the part before the error only
