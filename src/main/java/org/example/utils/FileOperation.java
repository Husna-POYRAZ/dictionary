package org.example.utils;

import org.example.constants.Constants;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileOperation {
    public static FileOperation fileOperation = new FileOperation();

    public List<String> readFileByJava8(String fileName) throws IOException {
        List<String> result;
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            result = lines.collect(Collectors.toList());
        }
        return result;

    }

    public void writeFile(String fileName, String line) throws IOException {
        FileWriter writer = new FileWriter(fileName, true);
        writer.write("\n"+line);
        writer.close();
    }



}
