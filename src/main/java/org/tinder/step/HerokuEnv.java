package org.tinder.step;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class HerokuEnv {

    public static int port() {
        try {
            return Integer.parseInt(System.getenv("PORT"));
        } catch (NumberFormatException ex) {
            log.error(ex.getMessage());
            return 9000;
        }
    }
}
