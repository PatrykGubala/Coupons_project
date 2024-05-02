package org.example.coupons.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;

import java.time.LocalDate;
import java.util.Date;

public class ExcelUtils {

    public static String safeGetString(Cell cell) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return NumberToTextConverter.toText(cell.getNumericCellValue());
        } else {
            return cell.toString();
        }
    }

    public static Date safeGetLocalDate(Cell cell) {
        if (cell == null) {
            return null;
        }
        return cell.getDateCellValue();

    }

    public static Double safeGetDouble(Cell cell) {
        if (cell == null || cell.getCellType() != CellType.NUMERIC) {
            return null;
        }
        return cell.getNumericCellValue();
    }

    public static Boolean safeGetBoolean(Cell cell) {
        if (cell == null || cell.getCellType() != CellType.BOOLEAN) {
            return null;
        }
        return cell.getBooleanCellValue();
    }
}
