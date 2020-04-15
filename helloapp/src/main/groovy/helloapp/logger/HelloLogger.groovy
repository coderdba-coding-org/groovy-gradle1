package helloapp.logger

import groovy.util.logging.Slf4j

@Slf4j
class HelloLogger {
    void logHello() {

        //log.setLevel(Level.WARN);

        println "Printing: hello groovy from class HelloWorld"
        log.error 'Logging: hello groovy ERROR from class HelloWorld'
        log.debug 'Logging: hello groovy DEBUG from class HelloWorld'
        log.trace 'Logging: hello groovy TRACE from class HelloWorld'
        log.info 'Logging: hello groovy INFO from class HelloWorld'


    }
}
