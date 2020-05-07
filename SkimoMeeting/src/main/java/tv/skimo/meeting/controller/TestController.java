package tv.skimo.meeting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 5/5/2020.
 */
@Controller
@RequestMapping("/")
public class TestController
{
	@PostMapping("video-upload")
	public String upload( @RequestParam("file") MultipartFile file){
		return "success";
	}
	@GetMapping("video-upload")
	public String uploadGet( Model model ){
		return "uploadTest.html";
	}
}
