package com.cvbackend.springboot.maven.api.utils;

import java.util.Dictionary;
import java.util.Hashtable;

public class FileUtility {

    private static String[] image = {"jpg", "gif", "png", "jpeg", "svg", "webp"};
    //private static String[] text = {"txt", "md", "doc", "docx", "pdf"}; // Placeholder for demo purposes
    //private static String[] video = {"mp4", "avi", "mov", "mkv"}; // Placeholder for demo purposes
    private static Dictionary<String, String[]> validFileTypes = new Hashtable<>();

    static {
        validFileTypes.put("image", image);
    }

    public static boolean IsValidFileType(String fileName, String fileTypeCategory) {
        if (fileName == null || fileTypeCategory == null) {
            return false;
        }
        
        String[] allowedExtensions = validFileTypes.get(fileTypeCategory);
        if (allowedExtensions == null) {
            return false;
        }
        
        String fileExtension = getFileExtension(fileName);
        for (String extension : allowedExtensions) {
            if (fileExtension.equalsIgnoreCase(extension)) {
                return true;
            }
        }

        return false;

    }

    public static boolean IsValidFileSize() {
        return false;
    }

    private static String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf(".");
        if (lastIndexOfDot == -1) {
            return null; // No extension found
        }
        return fileName.substring(lastIndexOfDot + 1);
    }

}