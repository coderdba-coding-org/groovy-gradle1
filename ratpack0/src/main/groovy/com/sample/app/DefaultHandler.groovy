package com.sample.app

import ratpack.handling.Handler
import ratpack.handling.Context

class DefaultHandler implements Handler {
    private final String defaultMessage

    DefaultHandler(String message) {
        this.defaultMessage = message
    }

    @Override
    void handle(Context context) {
        if (context.pathTokens.containsKey("name")) {
            context.render "Hello, ${context.pathTokens.name}!"
        } else {
            context.render defaultMessage
        }
    }

}