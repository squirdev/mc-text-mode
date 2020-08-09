# mc-text-mode
Connect to a server using only a console
Built with MCProtocolLIB 1.12.2

## setup
clone the repo and run `gradlew shadowJar`. a .jar file will output in ./build/libs

## usage
java -jar `filename` `email` `password` `ip`      
you will be then connected to the server. send messages/commands by typing stuff in

## how to change the version from 1.12.2
in build.gradle replace `com.github.Steveice10:MCProtocolLib:1.12.2-1` with `com.github.Steveice10:MCProtocolLib:{version}-1`
