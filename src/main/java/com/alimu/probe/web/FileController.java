package com.alimu.probe.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("upload")
public class FileController {
	
	@RequestMapping(value="/{fileName}")
	public void getStreamData(HttpServletRequest request,HttpServletResponse response,@PathVariable String fileName) {
		String rootDir = request.getSession().getServletContext().getRealPath("/");
		rootDir = rootDir.substring(0, rootDir.lastIndexOf(File.separator));
		rootDir = rootDir.substring(0, rootDir.lastIndexOf(File.separator)+1) + "files"+File.separator;
		File filepath = new File(rootDir, fileName);
		ServletOutputStream out=null;
		try {
			FileInputStream instream=new FileInputStream(filepath);
			byte[] b=new byte[1024];
			int length=0;
			BufferedInputStream bis=new BufferedInputStream(instream);
			out=response.getOutputStream();
			BufferedOutputStream bos=new BufferedOutputStream(out);
			while((length=bis.read(b))!=-1) {
				bos.write(b,0, length);
			}
			bis.close();
		} catch (Exception  e) {
			e.printStackTrace();
		} 
	}
}
