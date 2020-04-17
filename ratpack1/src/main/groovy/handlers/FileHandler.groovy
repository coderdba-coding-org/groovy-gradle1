package handlers

import java.io.File

import groovy.util.logging.Slf4j
import ratpack.exec.Promise

@Slf4j
class FileHandler {

    void DumbAppend1() {
        def src = new File("/tmp/source.txt")
        def dst = new File("/tmp/dest.txt")
        dst << src.text
    }

    void SleepAppend1() {
        def src = new File("/tmp/source.txt")
        def dst = new File("/tmp/dest.txt")
        log.info 'SleepAppend1: appending first line'
        dst << "first line\n"
        log.info 'SleepAppend1: sleeping'
        sleep(5000)
        log.info 'SleepAppend1: appending source file to dest file'
        dst << src.text
        log.info 'SleepAppend1: appending second line'
        dst << "second line\n"
    }

    // THIS ACTUALLY DOES NOT WORK WHEN CALLED
    // GIVES ERROR: org.codehaus.groovy.runtime.typehandling.GroovyCastException:
    // Cannot cast object 'PromiseSleepAppend1: Completed' with class 'java.lang.String' to class 'ratpack.exec.Promise'
    Promise<String> PromiseSleepAppend0() {
        def src = new File("/tmp/source.txt")
        def dst = new File("/tmp/dest.txt")
        log.info 'SleepAppend1: appending first line'
        dst << "first line\n"
        log.info 'SleepAppend1: sleeping'
        sleep(5000)
        log.info 'SleepAppend1: appending source file to dest file'
        dst << src.text
        log.info 'SleepAppend1: appending second line'
        dst << "second line\n"

        // Convert the output to a Promise.value (of native datatype data)
        return Promise.value("PromiseSleepAppend0: completed ")
    }
}