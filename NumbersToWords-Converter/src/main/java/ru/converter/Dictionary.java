package ru.converter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Dictionary {

    static final String ZERO = "zero";
    static final String MINUS = "minus";

    static final Map<Integer, String> exponentMap = new HashMap<Integer, String>() {{
        put(0, "");
    }};
    private static final Map<Integer, String> simpleNumbersMap = new HashMap<Integer, String>() {{
        put(0, "");
    }};
    private static final Map<Integer, String> decimalNumbersMap = new HashMap<Integer, String>() {{
    }};
    private static final Map<Integer, String> hundredthNumbersMap = new HashMap<Integer, String>() {{
        put(0, "");
    }};

    static private final String[] thousandOperator = new String[]{"", "one", "two"};

    static String getExponent(int key) {
        return exponentMap.get(key);
    }

    static String getSimpleNumber(int key) {
        return simpleNumbersMap.get(key);
    }

    static String getDecimalNumber(int key) {
        return decimalNumbersMap.get(key);
    }

    static String getHundredthNumber(int key) {
        return hundredthNumbersMap.get(key);
    }

    static String getThousandOperator(int key) {
        return thousandOperator[key];
    }

    public void loadResources() {

        fillMap(exponentMap, "exponentMap.xls", 1);
        fillMap(decimalNumbersMap, "decimalNumbers.xls", 2);
        fillMap(hundredthNumbersMap, "hundredNumbers.xls", 1);
        fillMap(simpleNumbersMap, "simpleNumbers.xls", 1);
    }

    private void fillMap(Map<Integer, String> fillMap, String pathFile, int fillIndex) {
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            FileInputStream file = new FileInputStream(new File(classLoader.getResource(pathFile).getFile()));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            String testText = "";
            for (Row row : workbook.getSheetAt(0)) {
                for (Cell cell : row) {
                    testText = cell.getRichStringCellValue().getString();
                }
                fillMap.put(fillIndex++, testText);
            }
        } catch (NullPointerException | IOException e) {
            System.out.println("problems с discovery file: " + pathFile + ". Perhaps the file is corrupted or does not exist!");


        }
    }

    enum numbersMap {
        FIRST, SECOND, THIRD
    }
}
