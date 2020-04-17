package services

import ratpack.exec.Promise

class FileService implements FileServiceInterface {

    Promise<String> PromiseSleepAppend1() {
        // Return with just plain native datatype does not work
        //return "PromiseSleepAppend1: completed"

        // Convert the output to a Promise.value (of native datatype data)
        return Promise.value("PromiseSleepAppend1: completed ")
    }
}
