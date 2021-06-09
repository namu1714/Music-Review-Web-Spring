package com.muizic.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ImageController {

	private boolean isImage(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/uploadAjax")
	@ResponseBody
	public ResponseEntity<String> uploadAjaxPost(MultipartFile file) {
		String uploadFolder = "C:\\upload";
		
		File uploadPath = new File(uploadFolder);
		log.info("upload path: " + uploadPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
	
		String uploadFileName = file.getOriginalFilename();
			
		//IE has file path
		uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);

		log.info("only file name: " + uploadFileName);

		UUID uuid = UUID.randomUUID();
			
		uploadFileName = uuid.toString() + "_" + uploadFileName;
					
		try {
			File saveFile = new File(uploadPath, uploadFileName);
			
			if(!isImage(saveFile))
				return new ResponseEntity<String> ("not image!", HttpStatus.BAD_REQUEST);
			
			file.transferTo(saveFile);
		} catch (Exception e) {
			log.error(e.getMessage());
		}//end catch

		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<String> (uploadFileName, header, HttpStatus.OK);
	}
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {
		log.info("filename: " + fileName);
		
		File file = new File("c:\\upload\\" + fileName);
		
		log.info("file: " + file);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName) {
		log.info("deleteFile: " + fileName);
		
		File file;
		
		try {
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
			file.delete();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
}
