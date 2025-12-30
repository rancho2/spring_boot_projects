package com.spring.ai.firstproject.helper;

import java.util.List;

public class Helper {
	public static List<String> getData()
	{
		return List.of("Java stands as one of the most influential and enduring programming languages in the history of software development ",
				"Conceived by James Gosling and his team at Sun Microsystems in the early 1990s and publicly released in 1995, ",
				"it was designed with a forward-thinking principle at its core: write once, run anywhere (WORA), ",
				"This philosophy addressed a significant challenge of the time: the fragmentation of computing environments, ",
				"Unlike languages that were compiled into machine-specific code, Java code is compiled into an intermediate format called bytecode, ",
				"which is then executed by the Java Virtual Machine (JVM). This abstraction layer means that any device equipped with a JVM can run a Java application,",
				"making it inherently platform-independent,",
				"The language's object-oriented nature is a fundamental aspect of its design, ",
				"Java organizes code around objects and classes, promoting principles such as encapsulation, inheritance, and polymorphism, ",
				"This paradigm encourages developers to create modular, reusable code and complex systems that are easier to manage and extend, ",
				"Furthermore, Java was built with robustness and security in mind. Its strong memory management, with features like automatic garbage collection, ",
				"alleviates the burden on developers to manually allocate and free memory, thereby preventing a whole class of common programming errors, ",
				"The Java security model, including the sandbox environment for applets, was also pioneering, ",
				"restricting untrusted code from performing harmful operations on a host system.");
	}
}
