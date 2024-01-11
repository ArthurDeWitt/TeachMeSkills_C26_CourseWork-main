package com.teachmeskills.mycourse.controller.pars_document;

import com.teachmeskills.mycourse.exception.CheckKeyWordException;
import com.teachmeskills.mycourse.exception.CheckNullSession;
import com.teachmeskills.mycourse.exception.FileFormatException;
import com.teachmeskills.mycourse.exception.FileYearException;
import com.teachmeskills.mycourse.logger.Logger;
import com.teachmeskills.mycourse.session.Session;
import com.teachmeskills.mycourse.validation.FileValidation;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileParser {
    private static final String INVALID_DOCUMENT = "C:\\Users\\Savva\\IdeaProjects\\TeachMeSkills_C26_CourseWork\\log\\invalid_documents\\invalid_document.txt";
    private static final String INVALID_DOCUMENT_PATH = "D:\\orders\\invalidOrders";
    private static String INPUT_FOLDER = "D:\\orders\\orders";
    private static String OUTPUT_FOLDER = "D:\\orders\\invalidOrders";

    public static List<File> filePars(File file, Session session) throws CheckNullSession {
        if (session != null) {
            if (session.isSessionAlive()) {
                File[] files = file.listFiles();
                List<File> fileList = new ArrayList<>();
                for (File file1 : files) {
                    try {
                        FileValidation.fileValidate(file1);
                        fileList.add(file1);
                    } catch (FileYearException | CheckKeyWordException | FileFormatException e) {
                        Logger.LogErrorInfo(new Date(), e.getMessage(), e);
                        moveFile(file1.getName());
                        // написать запись невалидных данных в файл
                    }
                }
                return fileList;
            } else {
                Logger.logExecutionInfo(new Date(),"[INFO] session is death");
            }
        } else {
            throw new CheckNullSession("[ERROR] session is null");
        }
        return null;
    }
    private static void moveFile(String fileName) {
        Path result = null;
        try {
            result =  Files.move(Paths.get(INPUT_FOLDER + fileName), Paths.get(OUTPUT_FOLDER + fileName));
        } catch (IOException e) {
            System.out.println("Exception while moving file: " + e.getMessage());
        }
        if(result != null) {
            System.out.println("File moved successfully.");
        }else{
            System.out.println("File movement failed.");
        }
    }
}
