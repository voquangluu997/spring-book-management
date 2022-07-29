package springtraining.luuquangbookmanagement.configs.excelFile;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springtraining.luuquangbookmanagement.model.AddBookRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelReader {
    public List<AddBookRequest> readBookFromExcelFile(MultipartFile excelFile) throws IOException {
        Workbook workbook = new XSSFWorkbook(excelFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        List<AddBookRequest> addBookRequestList = new ArrayList<>();
        for (Row row : sheet) {
            int rowNum = row.getRowNum();
            if (rowNum == 0 || row.getRowNum() == sheet.getPhysicalNumberOfRows() - 1) continue;
            String title = row.getCell(0).getStringCellValue();
            String description = row.getCell(1).getStringCellValue();
            String author = row.getCell(2).getStringCellValue();
            AddBookRequest addBookRequest = AddBookRequest.builder()
                    .title(title)
                    .description(description)
                    .author(author)
                    .build();
            addBookRequestList.add(addBookRequest);
        }
        return addBookRequestList;
    }

}
