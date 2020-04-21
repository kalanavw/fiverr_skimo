package com.demo.vasuskimodemo.model;

import java.io.Serializable;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 4/20/2020.
 */
public class DemoMedia implements Serializable
{
	private String imageUrl;
	private String videoUrl;

	public DemoMedia()
	{
	}

	public DemoMedia( String imageUrl, String videoUrl )
	{
		this.imageUrl = imageUrl;
		this.videoUrl = videoUrl;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl( String imageUrl )
	{
		this.imageUrl = imageUrl;
	}

	public String getVideoUrl()
	{
		return videoUrl;
	}

	public void setVideoUrl( String videoUrl )
	{
		this.videoUrl = videoUrl;
	}
}
