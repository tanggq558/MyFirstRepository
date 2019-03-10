package com.web;
//验证码生成处理类
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tools.MD5;

public class PictureCheckCode extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private Random rand=new Random();
	
	
	/**
	 * 生成随机颜色
	 * 
	 * @param   start  [int]
	 * @param   end    [int]
	 * @return  Color  [object]
	 */
	public Color getRandColor(int start,int end){
		int randNum;
		if(start>255) start=255;
		if(end>255) end=255;
		if(start>end) randNum=start-end;
		else randNum=end-start;
		int r=start+rand.nextInt(randNum);
		int g=start+rand.nextInt(randNum);
		int b=start+rand.nextInt(randNum);
		return new Color(r,g,b);
	}
	
	
	/**
	 * 着色\旋转\缩放
	 * 
	 * @param word 文字
	 * @param g    图片对象
	 */
	public void coloredAndRotation(String word,int i,Graphics g){
		/**着色**/
		g.setColor(new Color(20+rand.nextInt(110),20+rand.nextInt(110),20+rand.nextInt(110)));
		/**旋转**/
		Graphics2D g2d=(Graphics2D)g;
		AffineTransform trans=new AffineTransform();
		trans.rotate(rand.nextInt(45)*3.14/180,15*i+8,7);
		/**缩放**/
		float scaleSize=rand.nextFloat()+0.8f;
		if(scaleSize>1f) scaleSize=1f;
		trans.scale(scaleSize, scaleSize);
		g2d.setTransform(trans);
		g.drawString(word,15*i+20,20);
	}
	
	/**
	 * 生成100条干扰线
	 * 
	 * @param g2d
	 * @param width
	 * @param height
	 */
	public void getRandLine(Graphics2D g2d,int width,int height){
		for(int i=0;i<100;i++){
			int x=rand.nextInt(width-1);
			int y=rand.nextInt(height-1);
			int z=rand.nextInt(6)+1;
			int w=rand.nextInt(12)+1;
			
			BasicStroke bs=new BasicStroke(2f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
			Line2D line=new Line2D.Double(x,y,x+z,y+w);
			g2d.setStroke(bs);
			g2d.draw(line);
		}
	}
	
	/**
	 * 获取随机文字
	 * 
	 * @param  length [int]        验证码长度
	 * @param  g      [Graphics]   图片对象
	 * @return String
	 * @case1:A-Z
	 * @case2:chinese
	 * @default:0-9
	 */
	@SuppressWarnings("unused")
	public String getRandWord(int length,Graphics g){
		String finalWord="",firstWord="";
		int tempInt=0;
		String[] array={
				"0","1","2","3",
				"4","5","6","7",
				"8","9","a","b",
				"c","d","e","f"};
		
		for(int i=0;i<length;i++){
			switch(rand.nextInt(2)){
				case 1:
						tempInt=rand.nextInt(26)+65;
						firstWord=String.valueOf((char)tempInt);
						break;
				/*case 2:
						int r1,r2,r3,r4;
						String strH,strL;//high&low
						r1=rand.nextInt(3)+11; //前闭后开[11,14)
						if(r1==13){
							r2=rand.nextInt(7);
						}else{
							r2=rand.nextInt(16);
						}
						
						r3=rand.nextInt(6)+10;
						if(r3==10){
							r4=rand.nextInt(15)+1;
						}else if(r3==15){
							r4=rand.nextInt(15);
						}else{
							r4=rand.nextInt(16);
						}
						
						strH=array[r1]+array[r2];
						strL=array[r3]+array[r4];
						
						byte[] bytes=new byte[2];
						bytes[0]=(byte)(Integer.parseInt(strH,16));
						bytes[1]=(byte)(Integer.parseInt(strL,16));
						
						firstWord=new String(bytes);
						break;*/
				default:
						tempInt=rand.nextInt(10)+48;
						firstWord=String.valueOf((char)tempInt);
						break;
			}
			finalWord+=firstWord;
			this.coloredAndRotation(firstWord,i, g);
		}
		
		return finalWord;
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//设制不缓存图片
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","No-cache");
		response.setDateHeader("Expires",0);
		
		//生成图片
		response.setContentType("image/jpeg");
		int width=100;
		int height=40;
		BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		Graphics g=image.getGraphics();
		Graphics2D g2d=(Graphics2D)g;
		Font mFont=new Font("宋体",Font.BOLD,22);
		g.setColor(this.getRandColor(200,250));
		g.fillRect(0, 0, width, height);
		g.setFont(mFont);
		g.setColor(this.getRandColor(180,200));
		
		this.getRandLine(g2d, width, height);
		
		String randCode=this.getRandWord(4, g);
		
		HttpSession session=request.getSession();
		session.setAttribute("randCode",MD5.code(randCode.toLowerCase()));
		
		g.dispose();
		
		ImageIO.write(image,"JPEG",response.getOutputStream());
		
	}

}
