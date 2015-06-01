# crackingTheCode

### Files
- Anything relating to a question in the book *Cracking the Code Interview* is put in one file. E.g., the file for question 1.2 is named **1_2.md**
- Notes regarding to different aspects are named as **notes_*.md**
- submit.sh: the name of "submit" comes from leetcode. This script is used to test the code in a file using the given test cases. It will generate a Run.java file containing the implemented code and test code. Assertion errors will be reported upon failed test cases. E.g.: *submit.sh 1_3.md*
- debug.sh: this script is used to compile and run the Run.java file. It can be used when there are failed test cases, and the user can modify the generated Run.java to dubug.
- testscript.sh: it's used to check if the submit.sh works properly.
