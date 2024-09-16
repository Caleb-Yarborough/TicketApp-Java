package com.example.ticketapp;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class BarCode {
    public static String generateBarcode(Integer serialNumber) throws WriterException, IOException {
        try {
            // data for Barcode: serial number
            String data = String.valueOf(serialNumber);
            // Where image of barcode is generated in files
            String path = "src/main/resources/img/" + data + ".png";
            // creates an instance of MultiFormatWriter giving Barcode details like barcode type
            // creates BitMatrix object, which represents black and white squares that make up the barcode
            BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.CODE_128, 300, 150);
            // converts the given file path "path" into a Path object.
            Path pathToFile = new File(path).toPath();
            // writes the BitMatrix to a PNG image file
            MatrixToImageWriter.writeToPath(matrix, "PNG", pathToFile);
            // prints if barcode is successfully created
            return path;
            // catches exceptions
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "EMPTY";
    }


}




