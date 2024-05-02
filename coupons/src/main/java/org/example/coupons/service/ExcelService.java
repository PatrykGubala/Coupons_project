package org.example.coupons.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.coupons.model.Coupon;
import org.example.coupons.repository.CouponRepository;
import org.example.coupons.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private CouponRepository couponRepository;

    public String parseExcelFile(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<Coupon> coupons = new ArrayList<>();
            boolean isFirstRow = true;
            for (Row row : sheet) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }
                if (isRowEmpty(row)) {
                    continue;
                }
                try {
                    Coupon coupon = createCouponFromRow(row);
                    if (coupon != null) {
                        coupons.add(coupon);
                    }
                } catch (Exception e) {
                    System.out.println("Error processing row: " + e.getMessage());
                }
            }
            couponRepository.saveAll(coupons);
            workbook.close();
            return "File processed successfully, loaded " + coupons.size() + " coupons.";
        } catch (Exception e) {
            throw new RuntimeException("Failed to process Excel file: " + e.getMessage());
        }
    }


    private boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    public Coupon createCouponFromRow(Row row) {
        if (row == null) {
            return null;
        }

        String title = ExcelUtils.safeGetString(row.getCell(0));
        if (title == null || title.trim().isEmpty()) {
            return null;
        }

        Coupon coupon = new Coupon();
        coupon.setTitle(title);
        coupon.setContent(ExcelUtils.safeGetString(row.getCell(1)));
        coupon.setDiscountValue(ExcelUtils.safeGetDouble(row.getCell(2)));
        coupon.setType(ExcelUtils.safeGetString(row.getCell(3)));
        coupon.setCode(ExcelUtils.safeGetString(row.getCell(4)));
        coupon.setDate(ExcelUtils.safeGetLocalDate(row.getCell(5)));
        coupon.setExpires(ExcelUtils.safeGetLocalDate(row.getCell(6)));
        coupon.setStore(ExcelUtils.safeGetString(row.getCell(7)));
        coupon.setCategory(ExcelUtils.safeGetString(row.getCell(8)));
        coupon.setLinkZeStrony(ExcelUtils.safeGetString(row.getCell(9)));
        coupon.setDestinationUrl(ExcelUtils.safeGetString(row.getCell(11)));
        coupon.setExclusive(ExcelUtils.safeGetBoolean(row.getCell(12)));
        coupon.setStoreName(ExcelUtils.safeGetString(row.getCell(13)));

        return coupon;
    }
}
