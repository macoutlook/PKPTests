package com.macloz.Utilities;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by maciek on 2017-05-08.
 */
public class PdfParser {
    public String filePath;
    static PdfParser instance = null;


    protected void PdfParser() { }

    public void initializePdfParser(String filePath){
        this.filePath = filePath;
    }

    public static PdfParser getInstance() {
        if (instance == null) {
            instance = new PdfParser();
        }
        return instance;
    }

    public String parsePdfDocument(){
        PDFParser parser;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        PDFTextStripper pdfStripper;
        String parsedText = null;
        File file = new File(this.filePath);

        try {
            parser = new PDFParser(new RandomAccessFile(file, "r"));
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);
            pdDoc.close();
            cosDoc.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (cosDoc != null)
                    cosDoc.close();
                if (pdDoc != null)
                    pdDoc.close();
            } catch (Exception e1) {
                e.printStackTrace();
            }
        }
        return parsedText;
    }
}
