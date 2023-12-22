package com.api.telisadoptproyect.library.util;

import java.util.concurrent.ThreadLocalRandom;

public class GeneratorTool {
    public GeneratorTool() {
    }
    public int OTP(){return ThreadLocalRandom.current().nextInt(100000, 999999);}

}
