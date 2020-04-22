package com.demo.vasuskimodemo.controller;

import com.demo.vasuskimodemo.model.DemoMedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 4/20/2020.
 */
@Controller
public class DemoMediaViewController
{
	@Autowired
	private ResourceLoader resourceLoader;
	@Value("${server.resource.base.url}")
	private String baseUrl;

	@GetMapping
	public String viewMedia( Model model )
	{
		try
		{
			Resource timeCodeResource = resourceLoader.getResource( "classpath:static/abcde/timecodes.txt" );
			Resource videoResource = resourceLoader.getResource( "classpath:static/abcde/source.mp4" );
			Resource imgResource = resourceLoader.getResource( "classpath:static/abcde/img" );

			List<String> timeCodeList;
			try (Stream<String> lines = Files.lines( Paths.get( timeCodeResource.getURI() ) ))
			{
				timeCodeList = lines.collect( Collectors.toList() );
			}
			catch ( IOException e )
			{
				e.printStackTrace();
				return "error.html";
			}
			List<String> preparedTimeList = new ArrayList<>();
			String initVal = timeCodeList.get( 0 );
			if ( initVal == null )
			{
				return "error.html";
			}
			preparedTimeList.add( initVal );
			for ( int i = 0; i < timeCodeList.size(); i++ )
			{
				if ( ( Double.parseDouble( timeCodeList.get( i ) ) - Double.parseDouble( initVal ) ) > 30 )
				{
					preparedTimeList.add( timeCodeList.get( i ) );
					initVal = timeCodeList.get( i );
					i = timeCodeList.indexOf( timeCodeList.get( i ) ) - 1;
				}
			}
			List<String> imgList = null;
			try (Stream<Path> walk = Files.walk( Paths.get( imgResource.getURI() ) ))
			{
				imgList = walk.filter( Files::isRegularFile ).map( path -> path.getFileName().toString() ).collect( Collectors.toList() );
			}
			catch ( IOException e )
			{
				e.printStackTrace();
				return "error.html";
			}
			File videoFile = new File( videoResource.getURI() );
			String videoFileName = videoFile.getName();
			System.out.println( baseUrl );
			ArrayList<DemoMedia> demoMediaList = new ArrayList<>();
			List<String> finalImgList = imgList;
			IntStream.range( 0, preparedTimeList.size() ).forEach( i -> {
				double v = Double.parseDouble( preparedTimeList.get( i ) );
				int videoTime = ( int ) v;
				demoMediaList.add( new DemoMedia( this.baseUrl.concat( "img/" ).concat( finalImgList.get( i ) ), this.baseUrl.concat( videoFileName ).concat( "#t=" + videoTime ) ) );
			} );

			model.addAttribute( "mediaList", demoMediaList );
			return "index.html";
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return "error.html";
		}
	}
}
