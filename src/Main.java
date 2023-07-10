
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class Tulip {
    static Boolean hasError = false;
    public static void main(String[] args) throws IOException {



        // I want to receive a file path as args

        // if I receive more than 1 file
        if(args.length > 1){
            System.out.println("Usage: Tulip <script>");
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        }else{
            runPrompt();
        }
    }


    private static void runFile(String path) throws IOException {

        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String str = new String(bytes, Charset.defaultCharset());
        run(str);

        if(hasError) System.exit(65);

    }



    private static void runPrompt() throws IOException{
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        for(;;){
            System.out.println(">> ");
            String line = reader.readLine();
            if(line == null) break;
            run(line);
            hasError = false;
        }
    }


    private static void run(String source){
        Scanner sc = new Scanner(source);
        List<Token> tokens = sc.scanTokens();

        for(Token token: tokens){
            System.out.println(token);
        }
    }


//    Lexemes and Tokens
    public static void error(int line,String message){
        report(line,"",message);
    }

    static void report(int line,String where,String message){
        System.out.println("At Line "+ line + " Error "+ where + ": "+ message);
    }
}