# marsaccommodation

If you are running the test on Eclipse, you may have to setup lombork as below:
https://projectlombok.org/setup/eclipse

How to Execute Test:
Option#1:
• Clone the project from GitHub: https://github.com/richashri87/marsaccommodation.git to your IDE( I have used Eclipse)

• Go to \src\test\java\io\billie\rest\test package

• Right click on AppTest.java file and run as junit custom configuration
     . choose Test runner as jUnit5
     .put -ea -Denv={custome environment} inside VM arguments for example -Denv=dev

Option#2: Run test for custom environment configuration (dev in this case):
• Open command prompt

• Go to project directory

• Type mvn install, once completed type mvn test -Denv=dev
