package services

import handlers.FileHandler

import ratpack.exec.Promise

class FileService implements FileServiceInterface {

    Promise<String> DummyPromiseSleepAppend1() {

        // JUST A DUMMY - NO REAL WORK IS DONE

        // Return with just plain native datatype does not work
        //return "PromiseSleepAppend1: completed"
        // Convert the output to a Promise.value (of native datatype data)
        return Promise.value("DummyPromiseSleepAppend1: completed ")
    }

    Promise<String> PromiseSleepAppend1() {

        FileHandler fh = new FileHandler()
        //handlers.FileHandler.sleepAndAppendFile()
        fh.sleepAndAppendFile()

        // Return with just plain native datatype does not work
        // Convert the output to a Promise.value (of native datatype data)
        return Promise.value("PromiseSleepAppend1: completed ")
    }
}
