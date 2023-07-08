# sorting-tool
## Stage 2/6: Not just numbers

# Description
Remember how we wanted the program to work not only with numbers but also with lines and words? In this stage, you will add behavior for text data types to your program. You will also implement parsing for command-line arguments that will allow the user to define the input data type

After parsing the arguments and reading the input, the program should treat the input according to its data type and output an information message similar to the one from the previous stage.

# Objectives
1. Parse arguments that define the input data type:
   - if the optional -dataType argument is provided, it should be followed by long, line, or word, which means that the input consists of numbers, lines, or words, respectively.
   - if the argument is not provided, you should assume that the -dataType argument is word.
<br>
2. Read the input depending on the type:
   - long — numbers with an arbitrary number of spaces between them, just like in the previous stage.
   - line — each line treated as a whole string.
   - word — continuous sequences of characters separated by an arbitrary number of spaces.
<br>
3. Compute the following information about the data:
   1. The number of lines, numbers, or words in the input.
   2. The greatest number or the longest line or word in the input.
   3. How many times this greatest or longest element occurs in the input (compare words and lines by length; if two elements are the same length, arrange them alphabetically).
   4. The greatest/longest element's occurrence percentage.
4. Print this information as shown in the examples. Note that you should print the longest line on a separate line, so you will end up printing 4 lines instead of 2.

# Examples
Run configuration examples:
```
java SortingTool -dataType long
java SortingTool -dataType line
java SortingTool -dataType word
```
### Run examples

The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

**Example 1**, for integers:
```
> 1 -2   333 4
> 42
> 1                 1
Total numbers: 7.
The greatest number: 333 (1 time(s), 14%).
```

**Example 2**, for lines:
```
> 1 -2   333 4
> 42
> 1                 1
Total lines: 3.
The longest line:
1                 1
(1 time(s), 33%).
```

**Example 3**, for words:
```
> 1 -2   333 4
> 42
> 1                 1
Total words: 7.
The longest word: 333 (1 time(s), 14%).
```