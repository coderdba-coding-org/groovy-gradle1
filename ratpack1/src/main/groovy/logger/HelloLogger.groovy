package logger

import groovy.util.logging.Slf4j

@Slf4j
class HelloLogger {
    String LogHello() {

        //log.setLevel(Level.WARN);

        println "Printing: hello groovy from class HelloWorld"
        log.error 'Logging: hello groovy ERROR from class HelloWorld'
        log.debug 'Logging: hello groovy DEBUG from class HelloWorld'
        log.trace 'Logging: hello groovy TRACE from class HelloWorld'
        log.info 'Logging: hello groovy INFO from class HelloWorld'

        return "Logged Hello messages in application log - go there and check"
    }

    String SayHello() {

        // return a string "Hello" - note - no 'return' keyword is optional
        return "Saying Hello"

    }
}
