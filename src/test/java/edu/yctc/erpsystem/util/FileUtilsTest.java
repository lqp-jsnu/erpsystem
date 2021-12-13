package edu.yctc.erpsystem.util;

import edu.yctc.erpsystem.constant.ConstantHolder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static edu.yctc.erpsystem.util.FileUtils.deleteFile;
import static edu.yctc.erpsystem.util.FileUtils.isDirExists;

@SpringBootTest
class FileUtilsTest {

    @Test
    void testWrite() {
        assert isDirExists(ConstantHolder.DB_SAVE_PATH);
    }

    @Test
    void testDeleteFile() {
        assert deleteFile(ConstantHolder.IMG_SAVE_PATH, "0.jpg");
    }

}
