package com.my.secuirty.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.my.secuirty.core.validate.code.ValidateCode;

public class ImageCode extends ValidateCode{
	
	private BufferedImage image; 
    
    
	public ImageCode(String code, BufferedImage image, int expireIn) {
		super(code, expireIn);
		this.image = image;
	}
	public ImageCode(String code, BufferedImage image, LocalDateTime expireTime) {
		super(code, expireTime);
		this.image = image;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
