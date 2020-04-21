package services

import groovy.util.logging.Slf4j
import ratpack.exec.Execution
import ratpack.exec.Promise
import ratpack.exec.Operation

import ratpack.service.Service
import ratpack.service.StartEvent

import java.util.concurrent.TimeUnit

@Slf4j
class CheckThingsService implements Service{

    void onStart(StartEvent event) {

        println("CheckThingsService: onStart() ... waking up")

        //simpleReconcileLoop()
        execReconcileLoop()
    }

    // LOOP EXAMPLE1
    void simpleReconcileLoop() {

        println("CheckThingsService: simpleReconcileLoop() ... starting the loop")

        while(true) {
            println("CheckThingsService: simpleReconcileLoop() ... while loop repeat")
            sleep (300000)
        }
    }

    // LOOP EXAMPLE2
    void execReconcileLoop() {

        println("CheckThingsService: execReconcileLoop(): ... starting the loop")

        Execution.fork()
                .onError { e ->
                    log.error("Failure during reconciliation", e)
                    metricRegistry.counter(Metrics.name("reconciliationFailure").build()).inc()
                }.onComplete {
            //def sleepTime = config.reconciler.intervalMillis + new Random().nextInt(config.reconciler.intervalMillis)
            def sleepTime = 300000 // milliseconds

            log.debug("CheckThingsService: execReconcileLoop(): Will try after ${sleepTime} milliseconds")
            println("CheckThingsService: execReconcileLoop(): Will try after ${sleepTime} milliseconds")

            Execution.current().controller.executor.schedule(this.&reconcileLoop, sleepTime, TimeUnit.MILLISECONDS)
        }.start {
            doReconcile().then { null }
        }
    }

    // doReconcile - the method to do actual reconciliation work
    Operation doReconcile() {

        println("CheckThingsService: doReconcile() ... doing reconciliation now")
        println("CheckThingsService: doReconcile() ... completed doing reconciliation now")

        // giving just this will error
        //return Promise.ofNull()

        Promise.ofNull().operation()
    }

}
