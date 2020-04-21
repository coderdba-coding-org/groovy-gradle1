import static ratpack.groovy.Groovy.ratpack;

import com.sample.app.DefaultHandler
import ratpack.registry.Registry
import com.sample.app.services.IEmployeeService
import com.sample.app.services.internal.DefaultEmployeeService
import com.sample.app.services.BackgroundUtil


import static groovy.json.JsonOutput.toJson;


ratpack {
    bindings {
        add(new DefaultHandler("Hello, Ratpack!!!"))
        bind(IEmployeeService,DefaultEmployeeService)
        bind(BackgroundUtil)
        //bindInstance(BackgroundUtil, new BackgroundUtil())
        //bind(BackgroundUtil)
    }

    handlers {

        all {

            // here we can place pre-checks, validations, authentication and such
            // ...
            // ...
            // ...

            // this line attempts to add some context info and then go to the requested handler after that
            context.next(Registry.single("myself"))
        }

        // get the value in the context and add to the render
        //get{
        //    def a=context.get(String)
        //    println(a)
        //    render(json(["message": "handler chain.$a"]))
        //}

        //note: these two have the same path "/" - only the first one will be called - unless you comment the first one
        //get { render "Welcome to Ratpack World!!!" }

        get(DefaultHandler)

        get("name/:name",DefaultHandler)

        path("employee") {
            byMethod {
                get{
                    IEmployeeService emp ->
                        //it will run in blocking thread not on main thread
                        println("1.."+Thread.currentThread().name)
                        //emp.AsyncBlocklist().then{ x -> render(toJson(x)) }
                        //emp.syncList().then{ x -> render(toJson(x)) }
                        emp.asyncList().then{ x -> render(toJson(x)) }
                        sleep(1000)
                        println("3.."+Thread.currentThread().name)
                }
            }
        }

        path("asyncblockget") {
            byMethod {
                get{
                    IEmployeeService emp ->
                        //it will run in blocking thread not on main thread
                        println("1.."+Thread.currentThread().name)
                        emp.AsyncBlocklist().then{ x -> render(toJson(x)) }

                        sleep(1000)
                        println("3.."+Thread.currentThread().name)
                }
            }
        }

        path("synclist") {
            byMethod {
                get{
                    IEmployeeService emp ->
                        println("1.."+Thread.currentThread().name)
                        emp.syncList().then{ x -> render(toJson(x)) }
                        sleep(1000)
                        println("3.."+Thread.currentThread().name)
                }
            }
        }

        path("asynclist") {
            byMethod {
                get{
                    IEmployeeService emp ->
                        //it will run in blocking thread not on main thread
                        println("1.."+Thread.currentThread().name)
                        emp.asyncList().then{ x -> render(toJson(x)) }
                        sleep(1000)
                        println("3.."+Thread.currentThread().name)
                }
            }
        }

        //  ---------------------- GOWRISH BACKGROUND TRIALS ----------------------------------------
        // this goes synchronous
        path("background1") {
            byMethod {
                get{
                    BackgroundUtil util ->
                        util.background1()
                        render("Working in the background")
                }
            }
        }

        // this goes synch, but gives exception
        // java.lang.NullPointerException: Cannot invoke method onError() on null object
        path("background1a") { BackgroundUtil util ->
            byMethod {
                get{
                    util.background1().onError { e->
                        println("Endpoint background1a - errored")
                    }.then {
                        render("Working in the background")
                    }
                }

            }
        }

        // this seem to go synch and give error
        // java.lang.NullPointerException: Cannot invoke method then() on null object
        path("background1b") { BackgroundUtil util ->
            byMethod {
                get{
                    util.background1().then {
                        render"Working in the background"
                    }
                }

            }
        }

        path("background1c") { BackgroundUtil util ->
            byMethod {
                get{
                    util.background1().then()
                    render("Working in the background")
                }

            }
        }

        path("backgroundpromiseoperation") { BackgroundUtil util ->
            byMethod {
                post{
                    util.backgroundPromiseOperation().then {
                        println("Working in the background")
                        render("Working in the background")
                    }

                    //render("Working in the background")

                    //util.backgroundPromiseOperation().onError() {
                    //    println("Error")
                    //}.then{
                    //    render("Working in the background")
                    //}
                }

            }
        }

    }

}