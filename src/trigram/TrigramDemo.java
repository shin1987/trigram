package trigram;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.io.FileWriter;
import trigram.wordfilter.*;

/**
 * Main entrance to the program.
 */
public class TrigramDemo
{
    /**
     * @param args args[0] must be specified to be the input document filename
     *             args[1] is optional to specify the output filename.
     */
    public static void main(final String [] args) 
    {
        // Input argument check
        String output;
        
        if (args.length < 1) {
            System.err.println("Input file must be specified");
            return;
        }

        if (args.length < 2) {
            System.err.println("Output file not specified, result will be " +
                               "saved to a.txt in current directory");
            output = "a.txt";
        }
        else {
            output = args[1];
        }

        // Read in text content to fill the dictionary
        String contents = null;
        try {
            contents = Files.readString(Path.of(args[0]));
        }
        catch (IOException err) {
            System.err.println("Coult not read file: " + args[0]);
        }

        // Create trigram dictionary
        var filter = new CompondWordFilter();
        filter.add(new PlainWordFilter()).add(new SimplePunctuationFilter());
        
        var scanner = new FilteredScanner(filter);
        var dict = new Dictionary();
        scanner.scan(contents.split(" "), dict);

        // Sampling text
        var textgen = new RandomTextGenerator();

        String text = textgen.generate(dict, 500);

        // Save output to file
        try {
            FileWriter fout = new FileWriter(output);
            fout.write(text);
            fout.close();
        }
        catch (IOException err) {
            System.err.println("Failed to write to file: " + output);
        }
    }
}
