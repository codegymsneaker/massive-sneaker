package com.codegym.sneaker.utils;

import java.io.File;

public class StorageUtils {
    public static final String FEATURE_LOCATION = "F:\\codegym\\exercise\\WBD_JAVA\\massive-sneaker\\build\\libs\\exploded\\sneaker-vietnam-1.0-SNAPSHOT.war\\resource\\image";

    public static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return fileName.substring(dotIndex);
    }

    public static void removeFeature(String fileName) {
        File file = new File(FEATURE_LOCATION + "/" + fileName);
        if (file.exists()) {
            file.delete();
        }
    }
}
