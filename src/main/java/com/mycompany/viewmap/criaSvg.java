package com.mycompany.viewmap;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;


public class criaSvg {
    private final File arquivo;
    
    public criaSvg(String viewBox, String d) throws IOException, FileNotFoundException, TranscoderException{
        this.arquivo = new File("cidade.svg");
        if(arquivo.exists()){
            arquivo.delete();
            arquivo.createNewFile();
        }else{
            arquivo.createNewFile();
        }
        
        preencheArquivo(viewBox, d);
        criaJPG();
    }

    private void preencheArquivo(String viewBox, String d) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader("modelo.svg"));
        StringBuilder sb = new StringBuilder();

        while(br.readLine()!=null){
            sb.append(br.readLine());
        }
        sb.insert(sb.indexOf("vaqui"), viewBox);
        sb.delete(sb.indexOf("vaqui"), sb.indexOf("vaqui")+5);
        
        sb.insert(sb.indexOf("daqui"), d);
        sb.delete(sb.indexOf("daqui"), sb.indexOf("daqui")+5);
        
        BufferedWriter writer = new BufferedWriter(new PrintWriter(new FileWriter(this.arquivo)));
        writer.write(sb.toString());
        writer.close();
        
    }
    public ImageIcon getJPG() throws IOException{
        BufferedImage img = ImageIO.read(new File("out.jpg"));
      
        return new ImageIcon(img);
    }
    
    private void criaJPG() throws FileNotFoundException, TranscoderException, IOException{
        File out = new File("out.jpg");
        if(out.exists()){
            out.delete();
        }
        
        // Create a JPEG transcoder
        JPEGTranscoder t = new JPEGTranscoder();

        // Set the transcoding hints.
        t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,
                   new Float(.8));

        // Create the transcoder input.
        String svgURI = new File("cidade.svg").toURL().toString();
        TranscoderInput input = new TranscoderInput(svgURI);

        // Create the transcoder output.
        OutputStream ostream = new FileOutputStream("out.jpg");
        TranscoderOutput output = new TranscoderOutput(ostream);

        // Save the image.
        t.transcode(input, output); 

        // Flush and close the stream.
        ostream.flush();
        ostream.close();
        
    }
    
    
            
}

