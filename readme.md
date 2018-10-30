*About*:
This project demonstrates a crytpraphically strong RNG by combining Mersenne Twister with entropy and hashing.

java.security.SecureRandom is utilized to gather entropy and generated seeds. The generated seeds are then hashed using it as a seed for the underlying Mersenne Twister instance.

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
```
java -jar target/mersenne-twister-runner.jar 100 100 ~/random.txt
```
