package com.ezdi.library;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Dashboard_Lib
{
	public static BufferedImage resize(BufferedImage img, int w, int h)
	{

		try
		{
			// Getting the width and height of the given image
			int width = img.getWidth();
			int height = img.getHeight();

			// Creating a new image objects with the new width and height and the with the old image type
			BufferedImage dimg = new BufferedImage(w, h, img.getType());
			Graphics2D g = dimg.createGraphics();
			Thread.sleep(3000);
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			Thread.sleep(2000);
			g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			Thread.sleep(2000);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Thread.sleep(3000);
			// Creating a graphics image for the new image.
			g.drawImage(img, 0, 0, w, h, 0, 0, width, height, null);
			Thread.sleep(3000);
			g.dispose();
			return dimg;
		}

		catch (Exception e)
		{

			e.printStackTrace();
			return img;
		}

	}
}
