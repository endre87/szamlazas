package com.endreit.invoice.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger
{
    private static FileHandler fileHandler;
    private static SimpleFormatter formatterTxt;

    public static void setup() throws IOException
    {

        // get the global logger to configure it
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.INFO);

        // suppress the logging output to the console
//        Logger rootLogger = Logger.getLogger("");
//        Handler[] handlers = rootLogger.getHandlers();
//        if (handlers[0] instanceof ConsoleHandler)
//        {
//            rootLogger.removeHandler(handlers[0]);
//        }
//
//        fileHandler = new FileHandler("logs.txt");
//
//        // create a TXT formatter
//        formatterTxt = new SimpleFormatter();
//        fileHandler.setFormatter(formatterTxt);
//        logger.addHandler(fileHandler);
    }
}

