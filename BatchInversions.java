
/**
Course 1 Week 4 exercise
Select images and save them as negatives
Uses Duke's simplified ImageResource and DirectoryResource classes
 */

import edu.duke.*;
import java.io.File;

public class BatchInversions {
    
    public ImageResource makeInversion(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel: outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int red = 255 - inPixel.getRed();
            int green = 255 - inPixel.getGreen();
            int blue = 255 - inPixel.getBlue();
            pixel.setRed(red);
            pixel.setGreen(green);
            pixel.setBlue(blue);
        }
        return outImage;
    }
    
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource inverted = makeInversion(inImage);
            String fname = inImage.getFileName();
            String newName = "inverted-" + fname;
            inverted.setFileName(newName);
            inverted.draw();
            inverted.save();
        }
    }

}
