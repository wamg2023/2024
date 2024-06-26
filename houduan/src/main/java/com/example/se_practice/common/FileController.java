package com.example.se_practice.common;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {
    //文件存储路径
    private static final String filePath=System.getProperty("user.dir")+"/file/";
    @PostMapping("/upload")
    public Result upload( MultipartFile file) {
        synchronized (FileController.class){
        String flag = System.currentTimeMillis() + "";//获取当前时间戳
        String filename = file.getOriginalFilename(); //获取原始文件名
        try {
            //没有文件夹则在当前项目根目录下创建一个文件夹
            if (!FileUtil.isDirectory(filePath)){
                FileUtil.mkdir(filePath);
            }
            //文件存储形式：时间戳+文件名
            FileUtil.writeBytes(file.getBytes(),filePath+flag+"-"+filename);
            System.out.println(filename+"--上传成功");
            Thread.sleep(1L);
        } catch (Exception e) {
            System.err.println(filename+"上传失败");
        }
        return Result.Success(flag+"-"+filename);
        }
    }
    @GetMapping("/{flag}")
    public void  avatarPath(@PathVariable("flag") String flag, HttpServletResponse response) {
        if(FileUtil.isDirectory(filePath)){
            FileUtil.mkdir(filePath);
        }
        OutputStream os;
        List <String> filenames=FileUtil.listFileNames(filePath);
        String avatar=filenames.stream().filter(name ->name.contains(flag)).findAny().orElse("");
        try{
            if(StrUtil.isNotEmpty(avatar)){
                response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(avatar,"UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes=FileUtil.readBytes(filePath+ avatar);
                os=response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        }catch (Exception e){
            System.out.println("文件下载失败");
        }
    }
}
