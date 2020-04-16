import ratpack.health.HealthCheckHandler

import static ratpack.groovy.Groovy.ratpack
//import logger.HelloLogger
import logger.*
import handlers.*

ratpack {

    // bindings initialize the mentioned classes
    bindings {
        bind(HelloLogger)
        bind(FileHandler)
    }

    handlers {
        get {
            render "Hello world!"
        }

        get ("hello") {
            render "Hello world! hello!!"
        }

        get ("messages") { HelloLogger helloLogger ->

            render helloLogger.LogHello()
        }

        //dumb way - use bindings instead
        //get ("messagesDumb") {

        //new HelloLogger().LogHello()
        //}

        get ("sayhello") { HelloLogger helloLogger ->

            render helloLogger.SayHello()
        }

        get ("dumbcopy1") { FileHandler fileHandler ->

            render fileHandler.DumbAppend1()
        }
        get ("sleepappend1") { FileHandler fileHandler ->

            fileHandler.SleepAppend1()
            render "finished"
        }
        get ("promisesleepappend1") { FileHandler fileHandler ->

            fileHandler.PromiseSleepAppend1()
            render "finished"
        }
    }


}
