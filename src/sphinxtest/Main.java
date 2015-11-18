package sphinxtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.cmu.sphinx.alignment.LongTextAligner;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechAligner;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.decoder.adaptation.Stats;
import edu.cmu.sphinx.decoder.adaptation.Transform;
import edu.cmu.sphinx.result.WordResult;

/**
 * A simple example that shows how to transcribe a continuous audio file that
 * has multiple utterances in it.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Loading models...");

        Configuration configuration = new Configuration();

        // Load model from the jar
        configuration
                .setAcousticModelPath("file:lib//en-us");

        configuration
                .setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration
                .setLanguageModelPath("file:lib//cmusphinx-5.0-en-us.lm.dmp");

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter File name");
        String filename = keyboard.nextLine();
        String filename_sans_extension = filename.substring(0, filename.length()-4);
        
        PrintWriter writer = new PrintWriter(filename_sans_extension+".srt", "UTF-8");
        
        StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
        recognizer.startRecognition(new FileInputStream("data\\"+filename));       
        SpeechResult result; 
        int line_number=1;
        long startTime = System.nanoTime();
      
        while ((result = recognizer.getResult()) != null) {
        	int numOfWords = result.getWords().size();
        	if(numOfWords <=0)
        		continue;
        	writer.println(line_number);
        	writer.println(func2(result.getWords().get(0).getTimeFrame().getStart()) + " --> " +
        	func2(result.getWords().get(numOfWords-1).getTimeFrame().getEnd()) );
        	writer.println(result.getHypothesis());
        	writer.println();
        	line_number++;
        }
       writer.close();
       
       long endTime = System.nanoTime();
       System.out.println("Took "+(endTime - startTime) + " ns");
    }
    
    private static String func2(long n) {
        return func1(n / 3600000000l, 2) + ":" + func1(n / 60000 % 60, 2) + ":" 
                + func1(n / 1000 % 60, 2) + "," + func1(n % 1000, 3);
    }

    private static String func1(long n, int i) {
        String s = String.valueOf(n);
        while (s.length() < i) {
            s = "0" + s;
        }
        return s;
    }
}