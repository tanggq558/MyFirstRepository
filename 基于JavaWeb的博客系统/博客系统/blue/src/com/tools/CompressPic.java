package com.tools;
//图片上传处理
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CompressPic {
	public static String darwSmallImg(String url,int compressType, String fname, int new_w1,
			int new_h1, int new_w2, int new_h2) {
		String path = url + "\\" + fname;
		java.io.File file = new java.io.File(path); // 读入刚才上传的文件
		// 新的缩略图保存地址
		String newfname = "\\s" + fname;
		String newpath = url + newfname;
		
		int old_w=0,old_h=0;
		
		int width = 100;
		int height = 100;

		try {
			Image src = javax.imageio.ImageIO.read(file);
			old_w= src.getWidth(null); // 得到源图宽
			old_h = src.getHeight(null); // 得到源图高
			

			if ((old_w < new_w1 && old_h < new_h1) || (old_w < new_w2 && old_h < new_h2)) {
				// 这是不用压缩的情况
				FileInputStream input = new FileInputStream(path);
				FileOutputStream output = new FileOutputStream(newpath);

				int in = input.read();
				while (in != -1) {
					output.write(in);
					in = input.read();
				}
				input.close();output.close();

			} else {
				if(compressType==1){//135*90--90*135
					if ((float) (old_w / old_h) < 0.75) {// 3:4此时为90:135
						// 如果原图比要压缩的图小，则不压缩
						if (old_w < new_w2) {
							width = old_w;
						} else {
							width = new_w2;
						}
						if (old_h < new_h2) {
							height = old_h;
						} else {
							height = new_h2;
						}
					} else if (0.75 <= (float) (old_w / old_h)) {
						if (old_w < new_w1) {
							width = old_w;
						} else {
							width = new_w1;
						}
						if (old_h < new_h1) {
							height = old_h;
						} else {
							height = new_h1;
						}
					}
				}else if(compressType==2){//270*178
					if(old_w<new_w1){
						width=old_w;
					}else{
						width=new_w1;
					}
					if (old_h < new_h1) {
						height = old_h;
					} else {
						height = new_h1;
					}
				}

				BufferedImage tag = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(src, 0, 0, width, height, null); // 绘制缩小后的图

				FileOutputStream newimage = new FileOutputStream(newpath); // 输出到文件流

				JPEGImageEncoder encoder = JPEGCodec
						.createJPEGEncoder(newimage);
				encoder.encode(tag); // 近JPEG编码
				newimage.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return old_w+"*"+old_h+"."+old_w/old_h;
	}
}
