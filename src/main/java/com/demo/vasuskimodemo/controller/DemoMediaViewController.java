package com.demo.vasuskimodemo.controller;

import com.demo.vasuskimodemo.model.DemoMedia;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 4/20/2020.
 */
@Controller
public class DemoMediaViewController
{
	@GetMapping
	public String viewMedia( Model model )
	{
		ArrayList<DemoMedia> demoMediaList = new ArrayList<DemoMedia>()
		{
			{
				add( new DemoMedia( "http://localhost:8181/skimo/assets/img/posters/poster1.PNG", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4" ) );
				add( new DemoMedia( "http://localhost:8181/skimo/assets/img/posters/poster2.PNG", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4" ) );
				add( new DemoMedia( "http://localhost:8181/skimo/assets/img/posters/poster3.PNG", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4" ) );
				add( new DemoMedia( "http://localhost:8181/skimo/assets/img/posters/poster4.PNG", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4" ) );
			}
		};

		model.addAttribute( "mediaList", demoMediaList );
		return "index.html";
	}
}
