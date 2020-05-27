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

        // USE: A fork loop - DOES NOT BLOCK other things when this class is 'bind' in ratpack.groovy
        execForkReconcileLoop()

        // DONT-USE: non-fork loop - blocks other things when this class is 'bind' in ratpack.groovy
        // nonForkReconcileLoop()
    }

    // FORK LOOP - GOOD - DOES NOT BLOCK OTHERS WHEN BOUND
    void execForkReconcileLoop() {

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

    // NON-FORK LOOP - BAD - BLOCKS OTHERS WHEN BOUND
    // This, without 'fork' will block starting ratpack server when 'bind' is done in ratpack.groovy
    void nonForkReconcileLoop() {

        println("CheckThingsService: simpleReconcileLoop() ... starting the loop")

        while(true) {
            println("CheckThingsService: simpleReconcileLoop() ... while loop repeat")

            // can add look for vm quueue and create / destroy vms

            sleep (300000)
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
