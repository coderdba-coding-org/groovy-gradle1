package com.sample.app.services

import ratpack.exec.Operation
import ratpack.exec.Promise

class BackgroundUtil {

    Operation background1() {

        println("background1: thread: " + Thread.currentThread().name)
        println("background1: starting")

        println("background1: sleep1 begin")
        sleep(3000)

        println("background1: sleep2 begin")
        sleep(2000)

        println("background1: ending")
    }

    Operation background2() {

        println("background2: thread: " + Thread.currentThread().name)
        println("background2: starting")

        println("background2: sleep1 begin")
        sleep(3000)

        println("background2: sleep2 begin")
        sleep(2000)

        println("background2: ending")

    }

    Promise<String> backgroundPromise() {

        println("backgroundPromise: thread: " + Thread.currentThread().name)
        println("backgroundPromise: starting")

        println("backgroundPromise: sleep1 begin")
        sleep(3000)

        println("backgroundPromise: sleep2 begin")
        sleep(2000)

        println("backgroundPromise: ending")

        return Promise.value("backgroundPromise completed")

    }

    Operation backgroundPromiseOperation() {

        backgroundPromise().flatMap {theMessage ->
            println(theMessage)
        }.operation()
    }
}
