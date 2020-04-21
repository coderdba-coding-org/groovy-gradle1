import ratpack.health.HealthCheckHandler

import static groovy.json.JsonOutput.toJson
import static ratpack.groovy.Groovy.ratpack

import util.*

ratpack {

    bindings {

        bind(BackgroundExamples)
    }

    handlers {
        get {
            render "Hello world!"
        }

        get ("hello") {
            render "Hello world! hello!!"
        }

        path("helloagain") {
            byMethod {
                get{
                    render "Hello world! hello again!!"
                }
            }
        }

        // note: The class object "BackgroundExamples be" can be placed at 'path' or at 'get' lines
        path("backgroundvoid") { BackgroundExamples be ->
            byMethod {
                //get{ BackgroundExamples be ->
                get{
                    be.backgroundVoid()
                    render "completed void work" // need a render here as the method called above does not return anything browser throws error - no response sent to GET call
                }
            }
        }

        path("backgroundpromise") { BackgroundExamples be ->
            byMethod {
                //get{ BackgroundExamples be ->
                get{
                    render (be.backgroundPromise())
                }
            }
        }

        path("backgroundoperation") { BackgroundExamples be ->
            byMethod {
                //get{ BackgroundExamples be ->
                get{
                    be.backgroundOperation()
                    render "completed operation work" // need a render here as the method called above does not return anything browser throws error - no response sent to GET call

                }
            }
        }

\        path("backgroundpromisethen") { BackgroundExamples be ->
            byMethod {
                //get{ BackgroundExamples be ->
                get{
                    be.backgroundPromise().then{ String retmessage ->
                        render retmessage
                    }

                }
            }
        }

        get ("backgroundpromisethen1") {  BackgroundExamples be ->

            be.backgroundPromise().then{ String retmessage ->
                render retmessage
            }
        }

        get ("promisesleepappend0") { BackgroundExamples be ->

            //initially coded like this
            //fileHandler.PromiseSleepAppend1()

            be.PromiseSleepAppend0().then { String promisedMessage ->
                render promisedMessage
            }

        }
    }

}

