package edu.yctc.erpsystem.vo;

import java.util.Arrays;

/**
 * 验证码类
 *
 * @author lqp
 */
public class VerifyCodeVO {

    /**
     * 验证码
     */
    private String code;

    /**
     * 图片字节流
     */
    private byte[] imgBytes;

    /**
     * 到期时间
     */
    private Long expireTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getImgBytes() {
        return imgBytes;
    }

    public void setImgBytes(byte[] imgBytes) {
        this.imgBytes = imgBytes;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "VerifyCodeDO{" +
                "code='" + code + '\'' +
                ", imgBytes=" + Arrays.toString(imgBytes) +
                ", expireTime=" + expireTime +
                '}';
    }

}
