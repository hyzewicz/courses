/**
Course 1 Week 4 exercise
Select a range of images to be saved in grayscale
Uses Duke's simplified ImageResource and DirectoryResource classes */

import edu.duke.*;
import java.io.*;

public class BatchGrayscale {
    
    public ImageResource makeGray(ImageResource inImage) {
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		for (Pixel pixel: outImage.pixels()) {
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
			pixel.setRed(average);
			pixel.setGreen(average);
			pixel.setBlue(average);
		}
		return outImage;
	}

	public void selectAndConvert () {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			ImageResource gray = makeGray(inImage);
			String fname = inImage.getFileName();
			String newName = "gray-" + fname;
			gray.setFileName(newName);
			gray.draw();
			gray.save();
		}
	}

}
