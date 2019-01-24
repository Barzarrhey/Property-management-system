package com.pms.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {

	public static List<String> fileUtil(String path,HttpServletRequest request,MultipartFile[] attachs){
		String errorInfo = null;
		List<String> fileList=new ArrayList<String>();
		for(int i = 0;i < attachs.length ;i++){
			MultipartFile attach = attachs[i];
			if(!attach.isEmpty()){	
				String oldFileName = attach.getOriginalFilename();//原文件名
				String prefix=FilenameUtils.getExtension(oldFileName);//原文件后缀     
				int filesize = 500000;
		        if(attach.getSize() >  filesize){//上传大小不得超过 500k
	            	request.setAttribute(errorInfo, " * 上传大小不得超过 500k");
	            }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
	            		|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式不正确
	            	String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.jpg";  
	                File targetFile = new File(path, fileName);  
	                if(!targetFile.exists()){  
	                    targetFile.mkdirs();  
	                }  
	                //保存  
	                try {  
	                	attach.transferTo(targetFile);  
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                    request.setAttribute(errorInfo, " * 上传失败！");
	                }
	                fileList.add(path+File.separator+fileName);
	                
	            }else{
	            	request.setAttribute(errorInfo, " * 上传图片格式不正确");
	            	
	            }
			}
			
		}
		return fileList;
	}
}
