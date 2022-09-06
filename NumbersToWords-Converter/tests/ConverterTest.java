import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import ru.converter.Converter;

import java.io.FileInputStream;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class ConverterTest {
    private final Converter converter = new Converter();
    @Test
    public void dataDrivenTest() throws Exception {
        converter.loadResources();
        FileInputStream in = new FileInputStream("tests/data/data.xls");
        Workbook workbook = new HSSFWorkbook(in);
        BigInteger testNumber = new BigInteger("0");
        String testText = "" ;
        for (Row row : workbook.getSheetAt(0)){
            for (Cell cell:row){

                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        testText = cell.getRichStringCellValue().getString();
                        break;
                    case NUMERIC:
                        testNumber =  BigInteger.valueOf((long) cell.getNumericCellValue());
                    default:
                        break;
                }
            }
            assertEquals("Number conversion error" + testNumber, testText, converter.getFullNumber(testNumber.toString()));
        }
    }


}