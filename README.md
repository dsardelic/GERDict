# GERDict

## What is GERDict?

GERDict is a simple Java application created to help learn new German words and expressions. It is built as a guessing game in which the user has to guess the correct translation of a random word or expression. A simple pre-filled Excel file is used as the dictionary.

## How do I run it?

### Program configuration

The [config file](src/config.properties) contains the language and country codes used to create the Locale object which is in turn used for displaying the language-appropriate label messages. The available messages bundles are located in the [src/i18n](src/i18n) folder.

### Dictionary

A dictionary is an Excel file with the following characteristics:

* Column A: Word or expression in German.
* Column B: Corresponding German article. If not applicable, then the cell is to be left empty.
* Column C: Corresponding translation.

No column headers are used. Although a dictionary is not required to run the program, the program is basically useless without it.

### Run the program

GERDict is written for Java 8 and uses Maven to handle external dependencies. After code compilation, just run the main class [main/Main](src/main/Main.java).

## How do I use it?

The use is pretty straightforward. After selecting the dictionary, the first question appears. After making the selection, the correct answer is highlighted in green. If the incorrect answer was selected, then it is highlighted in red. Only one displayed answer is correct.

The program takes notice whether the word to be translated has a corresponding article or not, so as to display a set of random translations which also do or do not have a corresponding article. Since only one displayed answer is correct, the dictionary should contain at least 6 words with articles and 6 words/expressions without articles.

The next question is loaded by pressing the "Next word" button. Same thing can be achieved using the keyboard shortcut Alt+Enter or by right-clicking any one of the available answers.

The user can choose to translate the words from German and to German. The change is visible after the next question loads.

## Am I licensed to use this software?

This software is licensed under the MIT License which can be found in a [separate file](LICENSE).

## How can I get in touch with the author?

You can e-mail me at sardinhoATgmail.com.
