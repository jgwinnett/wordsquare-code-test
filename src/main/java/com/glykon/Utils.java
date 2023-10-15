package com.glykon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Utils {
    public static List<String> loadDictionary() throws IOException {
        Path dictionaryPath = Path.of(Objects.requireNonNull(Utils.class.getClassLoader().getResource("dictionary.txt")).getPath());
        return Files.readAllLines(dictionaryPath);
    }
}
