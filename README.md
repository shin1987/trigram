About this Project
==================

The program implements a simple Trigram based text generator. The generator takes in a sample text and output randomly generated text as output. The sampling algorithm is based on the idea of Trigram. More info can be found at: http://codekata.com/kata/kata14-tom-swift-under-the-milkwood/

Build and Run the Executables
=============================

The project has been developed in Java using plain text editor and command-line tools. BASH script tools are employed to build and execute the code. I expect the project to be easily imported to an IDE or build toolchains if needed.

System requirement:

1. Java and JDK 13 (may be compatible with lower versions, but not tested)
2. BASH - optional if to use the automated build scripts.

    Code and building tools are tested on macOS with default Java shipped with the system. Please make sure any input file are saved in UTF-8. Other charset are not tested and may break the program.

Build the Project
-----------------
Make sure the current working directory is at the root of this project, where the script `build-project` is located. 
Simply run command `build-project` will build all artefacts required to run the demo program. The script requires BASH environment, if not available, one should manually compile all source code under `src` directory. 

To run the demo program, execute:

`java -cp build trigram.TrigramDemo [input.txt] [output.txt]`

where input.txt is the document from which a trigram lookup table would be built and output.txt is the result file name. If `output.txt` is not provided, a temporary file named `a.txt` would be created under current working directory.

A sample text file has been provided under `data` directory for quick verifcation. To run the program with the example test:

`java -cp build data/data0.txt`

if the program has been compiled correctly, the generated text should appear as `a.txt` in the current working directory.

A note on input file format:
There is an extra script called `processing-data` which prepares an arbitary text file to a more friendly format. To run it:

`processing-data original.txt input.txt`

transforms the original.txt into input.txt. This step is not required, but is expected to enhance the result (See later).

Build and Run all Unit Tests
----------------------------
JUnit test has been implemented to ensure the program functions as desired. All tests are put under `tests` directory, one can manually compile and run each of them or use the scripts (requires BASH):

First, `build-all-tests` that compiles all tests at once.

Then `run-all-tests` launches all unit tests and output result into a console.

If manually compilation is required, remember to link the library JUnit5 and Hamcrest - both have been provieded under `3rdparty` directory.

Documentations
==============

Run script `create-doc` to generate javadoc style documentation for the project. All code (except unit test) has been documented with javadoc. 

Future Improvement
==================

Better Punctuation Check
------------------------
Currently, punctuation marks are handled quite crudely. The program only recognises simple punctuation marks such as ".", ",", "?", etc. When there is no space between a word and a punctuation mark, the program has to discard the word. For example, the string "cat." is neither a pure word nor pure punctuation mark, the program applies a filter to discard it. (adding words like this may create too many dead ends, or resulting deplicate entries with the original word such as "cat" vs. "cat.").

The script `processing-data` is provided to add spaces between punctuation marks in a source document so that the program can treat words and punctuation mark separately. This script is only a temporary solution and does not solve all problems related to punctuation marks.

Proper handling of punctuation marks requires some kind of lexical parsing. This can easily be implemented in Java, and intergrated to the program. Supposing some lexical parser is available to extract words and punctuation marks properly, one only needs to derive the `trigram.core.TrigramScanner` class that adds the entities to a dictionary accordingly.

Adding a lexical parser to the TrigramScanner class is very similar to how word fitler is used in the current project. At the momement, a filter is used to discard any unwanted entities including punctuation marks. A parser would be used the same way to filter words and punctuation marks before adding to the dictionary.

Handling Input Paragraphs
-------------------------
The main program treats the whole input document as a single piece of source text. One may wish to build a separate Trigram dictionary trained from each paragraph. It can be handled by creating multiple instances of `trigram.core.TrigramDictionary` objects each trained from a separate paragraph. 

Handling Output Paragraphs
--------------------------
When multiple output paragraphs are desired, one can easily run the program multiple times and piece together the results. Or it can be handled with more heuristic sampling - for example to avoid two paragraphs looking too similar.

Backtracing
-----------
Backtracing can be used to exhuastively searches many possible text generations and output only those match some predefined condition. It is easier to implement backtracing with recursive calls instead of loops which is used in current random sampling. 

The new algorithm should be implemented by deriving `trigram.core.TextGenerator` class and it should then integrate with the rest unchanged.

Better Abstraction of the Main Program
--------------------------------------

Because there is only one algorithm implemented, the current main program directly constructs the `core.RandomTextGenerator` object to sample text. If there is need to extend the program to use different algorithms or dictionary implementations (e.g. handling multiple paragraphs), there may need extra abstraction layer in the main program.

Directory Structure
===================

* src: source code directory containing the main solution to the question.
* tests: code for unit tests - all implemented using JUnit 5.
* 3rdparty: external dependencies to this project, including console standalone version of JUnit 5 and hamcrest for unit test.
* build: where binaries and other artefacts are saved by automation scripts.
