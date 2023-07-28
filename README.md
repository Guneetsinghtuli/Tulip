# Tulip - A Compiler Using Java (Under Development)

Tulip is a compiler project developed in Java, designed to transform high-level code into executable machine code. This project aims to provide developers with a simple and efficient tool for compiling their source code into a format that can be executed on the target platform.

**Please note that Tulip is currently under development, and the project is not yet ready for production use.**

## Table of Contents
- [Introduction](#introduction)
- [Getting Started](#getting-started)
- [Features](#features)
- [Current Progress](#current-progress)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Tulip compiler is developed with the goal of supporting various high-level programming languages and generating optimized machine code. It follows a modular and extensible architecture to enable easy integration of new features and improvements.

## Getting Started

To get started with Tulip, follow these steps:

1. Clone the repository to your local machine.
2. Ensure you have Java Development Kit (JDK) installed on your system (version X or higher).
3. Build the project using the provided build script or integrated development environment (IDE) of your choice.
4. Familiarize yourself with the codebase and project structure to understand its components.

## Features

Tulip is under active development, and the following features are either already implemented or planned for future releases:

- Lexical Analysis: Tokenizing the source code to identify meaningful symbols. [Completed]
- Syntax Analysis: Constructing a parse tree to check the code's grammatical correctness. [In Progress]
- Semantic Analysis: Analyzing the code to ensure it adheres to the language's semantic rules. [Planned]
- Intermediate Code Generation: Converting the parsed code into an intermediate representation. [Planned]
- Optimization: Performing various optimizations to enhance the generated machine code's efficiency. [Planned]
- Code Generation: Generating target-specific machine code from the intermediate representation. [Planned]

## Current Progress

As of now, we have completed the Lexical Analysis phase, and we are actively working on the Syntax Analysis module. I am dedicated to delivering a robust and efficient compiler.

## Usage

To compile your source code using Tulip, use the following command:

```java -jar tulip.jar <input-file>```


Replace `<input-file>` with the path to your source code file. The compiler will generate the corresponding executable output.

## Contributing

We welcome contributions to Tulip from the open-source community. If you find any bugs, have feature suggestions, or want to contribute code, please follow our contribution guidelines outlined in the CONTRIBUTING.md file.

## License

Tulip is released under the [MIT License](LICENSE), which allows for open-source use, modification, and distribution.

---

Thank you for your interest in Tulip! We appreciate your support and patience as we continue to develop and improve this compiler. If you encounter any issues or have any questions, feel free to reach out to us through GitHub's issue tracker. Happy coding!

