package trigram;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.io.FileWriter;

public class TrigramDemo
{
    public static void main(final String [] args) 
    {
        String contents = null;
        try {
            contents = Files.readString(Path.of(args[0]));
        }
        catch (IOException err) {
            System.err.println("Coult not read file: " + args[0]);
        }
        
        var filter = new trigram.wordfilter.PlainWordFilter();
        var scanner = new FilteredScanner(filter);
        var dict = new Dictionary();
        scanner.scan(contents.split(" "), dict);

        var textgen = new RandomTextGenerator();

        String output = textgen.generate(dict);
        
        try {
            FileWriter fout = new FileWriter(args[1]);
            fout.write(output);
            fout.close();
        }
        catch (IOException err) {
            System.err.println("Failed to write to file: " + args[1]);
        }
    }
}
