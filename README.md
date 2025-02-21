# PostFix-Calculator

A Java program that uses a Stack to convert Infix expression into Postfix and then solves it.

## Background

  I was looking through past projects that I had done in my Undergrad when I came across my Infix to Postfix project. It started with me simply wanting to refresh my coding knowledge after a hiatus. I was just going to write some JavaDoc comments into the code both as a means to test my understanding of the code but also so my future self can use the comments for guidance.
  I didn't push commits regularly (like I should have), but I do remember the general order in which the edits were made.

## Project Purpose

  This was a class project in my Undergrad. The point was to write our own Stack Class using Generics and then use that class to convert an Infix mathematical expression into Postfix.
  I wanted to do more than that, which was for it to take user input, instead of using some pre-defined String. With that being said, when I decided to finally finish what I had started, I found that I had stopped at the method operatorAssignment, so that is where I picked up from.

## Edits

It started with the format of the JavaDoc comments.

- **AlexStackClass.java**

    No edits were made to **AlexStackClass.java**.

- **PostfixCalculator.java**

    The only permanent edits made to **PostfixCalculator.java** were the JavaDoc comments. There were some temporary print statements that I placed so I could follow along with what the code was doing after each iteration.

- **Main.java**

    Picking up from where I left off:

    This file had already been started, but it was never completed. I had all the variables created and set up. The program was passing a pre-defined input of type String from **Main.java** into **PostfixCalculator.java** and calling the methods in **PostfixCalculator.java**.

    ``totalNumber()``

      Already completed.
      Changed returnType from LinkedList<String> to String.

    ``operandAssignment()``

      Already completed.
      Changed returnType from LinkedList<String> to String.

    ``operatorAssignment()``

    My initial attempt was to read each operator symbol as a Char or Byte type. The program continuously kept throwing errors and exceptions when I tried entering in the operator symbols even though they were correct.
    As I reflected upon this now, I just remembered that when I had written the bare bones of the method and jotted down the framework as if saying, "This might not be correct, but this is the general idea of what I want." Now, I realize why it was not working from the beginning: I had simply jotted down the framework.
    The technical reason for why it was not working was because the input (operator symbol) was not being registered as a Char or Byte type.
    Why did I want the input to be one of these two types? Whenever I write code, I consider how the input might be exploited to unexpectedly break the program. I had the vision of making the operators be scanned as the Char or Byte type to put a size limit on the input in addition to the input being required to be one of the following symbols: "^ * / + -".
    After repeated failed attempts to make it work with the Char or Byte type, I changed the input to be read as the String type.
    Changed returnType from ``LinkedList<String>`` to String.

    ``equation()``

    Originally consisted of only two lines of code. First line was initializing the variable ``expression``. The second line was the return statement.
    Changed returnType from ``LinkedList<String>`` to String.

- **Wrapping it up**

    In the end, I decided to change the variable ``expression`` from a ``LinkedList<String>`` to just simply a String, so I could pass it into **PostfixCalculator.java**.
    I moved the PostfixCalculator method calls from the ``main()`` method in **Main.java** into its own method in **Main.java** called ``conversion()``.
    I aligned the print statements to output in a pretty and easy-to-read format.

--------

Example Output:

    Operands:  [12.0, 4.0, 5.0, 3.0, 12.0, 2.0]
    Operators: [/, ^, +, -, ^]
    Infix:   12.0 / 4.0 ^ 5.0 + 3.0 - 12.0 ^ 2.0
    PostFix: 12.0 4.0 5.0 ^ 3.0 + 12.0 2.0 ^ - /
    Answer:  0.013590033975084938
