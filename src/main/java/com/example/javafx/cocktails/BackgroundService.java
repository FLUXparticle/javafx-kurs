package com.example.javafx.cocktails;

import java.util.concurrent.*;

public class BackgroundService {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    public static void runInBackground(Runnable block) {
        EXECUTOR_SERVICE.execute(block);
    }

    public static void shutdown() {
        EXECUTOR_SERVICE.shutdown();
    }

}
