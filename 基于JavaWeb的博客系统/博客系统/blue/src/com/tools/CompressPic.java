package com.tools;
//ͼƬ�ϴ�����
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
		java.io.File file = new java.io.File(path); // ����ղ��ϴ����ļ�
		// �µ�����ͼ�����ַ
		String newfname = "\\s" + fname;
		String newpath = url + newfname;
		
		int old_w=0,old_h=0;
		
		int width = 100;
		int height = 100;

		try {
			Image src = javax.imageio.ImageIO.read(file);
			old_w= src.getWidth(null); // �õ�Դͼ��
			old_h = src.getHeight(null); // �õ�Դͼ��
			

			if ((old_w < new_w1 && old_h < new_h1) || (old_w < new_w2 && old_h < new_h2)) {
				// ���ǲ���ѹ�������
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
					if ((float) (old_w / old_h) < 0.75) {// 3:4��ʱΪ90:135
						// ���ԭͼ��Ҫѹ����ͼС����ѹ��
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
				tag.getGraphics().drawImage(src, 0, 0, width, height, null); // ������С���ͼ

				FileOutputStream newimage = new FileOutputStream(newpath); // ������ļ���

				JPEGImageEncoder encoder = JPEGCodec
						.createJPEGEncoder(newimage);
				encoder.encode(tag); // ��JPEG����
				newimage.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return old_w+"*"+old_h+"."+old_w/old_h;
	}
}
