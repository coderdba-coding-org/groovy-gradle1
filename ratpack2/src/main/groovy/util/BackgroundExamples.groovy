package util

import groovy.util.logging.Slf4j

import ratpack.exec.Promise
import ratpack.exec.Operation
import ratpack.exec.Blocking
import ratpack.exec.Downstream
import ratpack.exec.Execution

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

    Promise<String> promisesync() {
        Promise.sync {
            def String myMessage = "promisesync: completed  - method thread: " + Thread.currentThread().name
            println("promisesync: Method thread: " + Thread.currentThread().name)
            return myMessage
        }
    }

    Promise<String> promiseasyncThisHangs() {

        Promise.async {
            String myMessage = "promiseasync: completed  - method thread: " + Thread.currentThread().name
            println("promiseasync: Method thread : " + Thread.currentThread().name)
            return myMessage
        } as Promise<String>
    }

    // Promise.asynch is asynch - so, you will get different threads
    Promise<String> promiseasync() {

        Promise.async { Downstream downstream ->
            String myMessage = ""
            println("promiseasync: Initial Method thread : " + Thread.currentThread().name)

            // ask for an execution to be scheduled on another compute thread
            Execution.fork().start({ forkedExec ->
                myMessage = "promiseasync: forked thread completed  - method thread: " + Thread.currentThread().name
                println("promiseasync: forked thread: "+Thread.currentThread().name)
                downstream.success(myMessage)
            })
            return myMessage
        } as Promise<String>
    }

    // Promise.asynch is asynch - so, you will get different threads
    Promise<String> promiseasyncthread() {

        Promise.async { Downstream downstream ->
                String myMessage = ""
                println("promiseasyncthread: Initial Method thread : " + Thread.currentThread().name)

                // ask for an execution to be scheduled on another compute thread
                 Thread.start {
                    myMessage = "promiseasyncthread: forked thread completed  - method thread: " + Thread.currentThread().name
                     println("promiseasyncthread: forked thread: " + Thread.currentThread().name)
                    downstream.success(myMessage)
                }
            return myMessage
        } as Promise<String>
    }

    // blocking get is asynch - so, you will get different threads
    Promise<String> blockingget() {
        Blocking.get {
            println("blockingget: thread: " + Thread.currentThread().name)
            println("blockingget: starting")

            println("blockingget: sleep1 begin")
            sleep(2000)

            println("blockingget: sleep2 begin")
            sleep(1000)

            println("background1: ending")

            // no need to convert this to Promise.value() when using Blocking.get()
            return "Completed: blockingget"
        }
    }

    /****
    Promise<List<Employee>> asyncList() {
        Promise.async { Downstream downstream ->
            println("before"+Thread.currentThread().name)
            List<Employee> employees = new ArrayList<>()
            // ask for an execution to be scheduled on another compute thread
            Execution.fork().start({ forkedExec ->
                employees.add(new Employee("Adam","Jhon"))
                employees.add(new Employee("Yashu","JSDSD"))
                employees.add(new Employee("XYZ","ADA"))
                employees.add(new Employee("HXS","HJK"))
                println("promise method:"+Thread.currentThread().name)
                downstream.success(employees)
            })
            return employees
        } as Promise<List<Employee>>
    }
    ***/

}
