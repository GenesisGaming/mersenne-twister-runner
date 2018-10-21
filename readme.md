*Checkout:*
```
git@github.com:GenesisGaming/mersenne-twister-runner.git
```

*Build:*
```
mvn clean package
```
note: the compiled binary file is target/mersenne-twister-runner.jar


*Usage:*
```
  $ java -jar {pathToJar} {maxValue} {count} {filename}

    -pathToJar: location of the mersenne-twister-runner.jar

    -maxValue: maximum value of the random number to be generated

    -count: defines how many times a random number will be generated

    -filename: file where the numbers generated will appended
```

example:
`java -jar mersenne-twister-runner.jar 100
