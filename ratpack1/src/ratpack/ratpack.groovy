import ratpack.health.HealthCheckHandler

import static ratpack.groovy.Groovy.ratpack
import logger.HelloLogger

ratpack {

    // bindings initialize the mentioned classes
    bindings {
        bind(HelloLogger)
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
    }


}
