package util

import groovy.util.logging.Slf4j

import ratpack.exec.Operation
import ratpack.exec.Promise

@Slf4j
class BackgroundExamples {

    void backgroundVoid() {

        println("background1: thread: " + Thread.currentThread().name)
        println("background1: starting")

        println("background1: sleep1 begin")
        sleep(2000)

        println("background1: sleep2 begin")
        sleep(1000)

        println("background1: ending")
    }

    Operation backgroundOperation() {

        println("backgroundOperation: thread: " + Thread.currentThread().name)
        println("backgroundOperation: starting")

        println("backgroundOperation: sleep1 begin")
        sleep(2000)

        println("backgroundOperation: sleep2 begin")
        sleep(1000)

        println("backgroundOperation: ending")
    }

    Promise<String> backgroundPromise() {

        println("background1: thread: " + Thread.currentThread().name)
        println("background1: starting")

        println("background1: sleep1 begin")
        sleep(2000)

        println("background1: sleep2 begin")
        sleep(1000)

        println("background1: ending")

        return Promise.value("Completed: backgroundPromise")

    }

    Promise<String> PromiseSleepAppend0() {
        def src = new File("/tmp/source.txt")
        def dst = new File("/tmp/dest.txt")
        log.info 'SleepAppend1: appending first line'
        dst << "first line\n"
        log.info 'SleepAppend1: sleeping'
        sleep(1000)
        log.info 'SleepAppend1: appending source file to dest file'
        dst << src.text
        log.info 'SleepAppend1: appending second line'
        dst << "second line\n"

        // Convert the output to a Promise.value (of native datatype data)
        return Promise.value("PromiseSleepAppend0: completed ")
    }
}
