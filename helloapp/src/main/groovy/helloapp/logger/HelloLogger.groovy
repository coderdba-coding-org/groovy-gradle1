package helloapp.logger

import groovy.util.logging.Slf4j

@Slf4j
class HelloLogger {
    void logHello() {

        //log.setLevel(Level.WARN);

        println "Printing: hello groovy from class HelloWorld"
        log.debug ("Logging: hello groovy from class HelloWorld")
    }
}
