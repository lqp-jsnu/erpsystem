package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.FileRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

import static edu.yctc.erpsystem.util.FileUtils.fileToByte;
import static edu.yctc.erpsystem.util.FileUtils.isDirExists;

/**
 * 文件上传下载接口实现
 *
 * @author lqp
 */
@RestController
@RequestMapping("/file")
public class FileRestControllerImpl implements FileRestController {

    private final static Logger logger = LoggerFactory.getLogger("controller");

    @Override
    @PostMapping("download")
    public void download(@RequestParam("filename") String filename, HttpServletResponse response) {
        try (
            // jdk7新特性，可以直接写到try()括号里面，java会自动关闭
            InputStream inputStream = new FileInputStream(new File(ConstantHolder.FILE_SAVE_PATH, filename));
            OutputStream outputStream = response.getOutputStream()
        ) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename, "UTF-8"));
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            logger.error("download error, IOException={}, filename={}, response={}", e, filename, response);
        }
    }

    @Override
    @PostMapping("upload")
    public ResultDO<Void> upload(@RequestParam("file") MultipartFile file, @RequestParam("fileType")String fileType, @RequestParam("fileName")String fileName) {
        // 参数校验
        if (file.isEmpty() || StringUtils.isBlank(fileType) || StringUtils.isBlank(fileName)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }
        String savePath;
        if (ConstantHolder.IMG_FILE.equals(fileType)) {
            savePath = ConstantHolder.IMG_SAVE_PATH;
        } else if (ConstantHolder.PRODUCT_IMG_FILE.equals(fileType)) {
            savePath = ConstantHolder.PRODUCT_IMG_SAVE_PATH;
        } else if (ConstantHolder.EXCEL_FILE.equals(fileType)) {
            savePath = ConstantHolder.FILE_SAVE_PATH;
        } else if (ConstantHolder.TEMPLATE_FILE.equals(fileType)) {
            savePath = ConstantHolder.TEMPLATE_SAVE_PATH;
            fileName = file.getOriginalFilename();
        } else {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        // 判断文件夹是否存在 不存在创建文件夹
        if (!isDirExists(savePath)) {
            return new ResultDO<>(false, ResultCode.FILE_CANNOT_BE_CREATE, ResultCode.MSG_FILE_CANNOT_BE_CREATE, null);
        }

        try {
            File dest = new File(savePath + fileName);
            file.transferTo(dest);
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, null);
        } catch (IOException e) {
            logger.error("upload error, IOException={}, file={}", e, file);
        }
        return new ResultDO<>(false, ResultCode.FILE_CANNOT_BE_CREATE, ResultCode.MSG_FILE_CANNOT_BE_CREATE, null);
    }

    @Override
    @GetMapping(value = "img/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable("imageName") String imageName) {
        byte[] imageContent = fileToByte(new File(ConstantHolder.IMG_SAVE_PATH + imageName));
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "productImg/{imageName}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable("imageName") String imageName) {
        byte[] imageContent = fileToByte(new File(ConstantHolder.PRODUCT_IMG_SAVE_PATH + imageName));
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

}
