package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ResultDO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传下载接口
 *
 * @author lqp
 */
public interface FileRestController {

    /**
     * 下载文件
     *
     * @param filename 文件名
     * @param response 响应
     */
    void download(String filename, HttpServletResponse response);

    /**
     * 上传文件
     *
     * @param file 文件名
     * @param fileType 文件类型
     * @param fileName 文件名字
     * @return 是否成功
     */
    ResultDO<Void> upload(MultipartFile file, String fileType, String fileName);

    /**
     * 获取本地图片
     *
     * @param imageName 图片名称
     * @return 图片
     */
    ResponseEntity<byte[]> getImage(String imageName);

    /**
     * 获取本地图纸
     *
     * @param imageName 图片名称
     * @return 图片
     */
    ResponseEntity<byte[]> getProductImage(String imageName);

}
