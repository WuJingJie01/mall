package com.ptu.mall.controller;

import com.ptu.mall.domain.vo.ResponseResult;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@RestController
@RequestMapping("/common")
public class CommonController {

    @RequestMapping("/captcha")
    @ApiOperation(value = "生成验证码信息", produces = "application/octet-stream")
    public void generateCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException, FontFormatException {
        // 3个设置都是为了不缓存响应数据，同时设置可以兼容不同的浏览器或缓存服务器
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        // 响应内容是一个PNG格式的图像
        response.setContentType("image/png");

        // 生成不同类型验证码，三个参数分别为宽、高、位数
        // png类型
//        SpecCaptcha captcha = new SpecCaptcha(180, 40, 4);
        // gif类型
//        GifCaptcha captcha = new GifCaptcha(180, 40, 4);
        // 中文类型
//        ChineseCaptcha captcha = new ChineseCaptcha(180, 40, 4);
        // 中文gif类型
//        ChineseGifCaptcha captcha = new ChineseGifCaptcha(180, 40, 4);
        // 算术类型
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(180, 40, 4);

        // 设置字符类型
        captcha.setCharType(Captcha.TYPE_DEFAULT);

        // 设置字体
//        captcha.setFont(Captcha.FONT_8, 40);

        // 验证码存入 session
        request.getSession().setAttribute("verifyCode", captcha.text().toLowerCase());

        // 输出图片流
        captcha.out(response.getOutputStream());
    }

    @PostMapping("/uploadfile")
    public ResponseResult uploadFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return ResponseResult.failResult("文件不能为空");
        }
        String resourcesPath = new File(ClassLoader.getSystemResource("").getPath()).getAbsolutePath();

        // 定义upload目录路径
        String uploadDirPath = resourcesPath + File.separator + "upload";

        // 创建upload目录（如果不存在）
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        // 定义文件路径
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);
        String fileName = UUID.randomUUID() + suffix;
        File newFile = new File(uploadDirPath + File.separator + fileName);
        file.transferTo(newFile);
        String forePortPath = "http://localhost:8080/upload/" + fileName;
        ResponseResult result = ResponseResult.okResult();
        result.setData(forePortPath);
        return result;
    }
}
