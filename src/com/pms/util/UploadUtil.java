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
				String oldFileName = attach.getOriginalFilename();//ԭ�ļ���
				String prefix=FilenameUtils.getExtension(oldFileName);//ԭ�ļ���׺     
				int filesize = 500000;
		        if(attach.getSize() >  filesize){//�ϴ���С���ó��� 500k
	            	request.setAttribute(errorInfo, " * �ϴ���С���ó��� 500k");
	            }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
	            		|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){//�ϴ�ͼƬ��ʽ����ȷ
	            	String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.jpg";  
	                File targetFile = new File(path, fileName);  
	                if(!targetFile.exists()){  
	                    targetFile.mkdirs();  
	                }  
	                //����  
	                try {  
	                	attach.transferTo(targetFile);  
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                    request.setAttribute(errorInfo, " * �ϴ�ʧ�ܣ�");
	                }
	                fileList.add(path+File.separator+fileName);
	                
	            }else{
	            	request.setAttribute(errorInfo, " * �ϴ�ͼƬ��ʽ����ȷ");
	            	
	            }
			}
			
		}
		return fileList;
	}
}
