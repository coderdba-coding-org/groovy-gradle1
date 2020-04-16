import ratpack.health.HealthCheckHandler

import static ratpack.groovy.Groovy.ratpack
import logger.HelloLogger

ratpack {

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

            helloLogger.LogHello()
        }

        //dumb way
        //get ("messagesDumb") {

        //new HelloLogger().LogHello()
        //}

        get ("sayhello") { HelloLogger helloLogger ->

            helloLogger.SayHello()
        }
    }


}
