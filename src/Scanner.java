import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Scanner {
    private final String source;
    private int start = 0;
    private int current = 0;

    private int line = 1;


    private final List<Token> tokens = new ArrayList<>();

    private static final HashMap<String,TokenType> keywords;

    static {
        keywords = new HashMap<>();
        keywords.put("and",    TokenType.AND);
        keywords.put("class",  TokenType.CLASS);
        keywords.put("else",   TokenType.ELSE);
        keywords.put("false",  TokenType.FALSE);
        keywords.put("for",    TokenType.FOR);
        keywords.put("fun",    TokenType.FUN);
        keywords.put("if",     TokenType.IF);
        keywords.put("nil",    TokenType.NIL);
        keywords.put("or",     TokenType.OR);
        keywords.put("print",  TokenType.PRINT);
        keywords.put("return", TokenType.RETURN);
        keywords.put("super",  TokenType.SUPER);
        keywords.put("this",   TokenType.THIS);
        keywords.put("true",   TokenType.TRUE);
        keywords.put("var",    TokenType.VAR);
        keywords.put("while",  TokenType.WHILE);
    }




    List<Token> scanTokens(){
        while(!isAtEnd()){
            start = current;
            scanToken();
        }
        // End of file token
        tokens.add(new Token(TokenType.EOF,"",null,line));
        return tokens;
    }


    private boolean isAtEnd(){
        return current >= source.length();
    }

    private void scanToken(){

        char c = advance();
        switch (c){
            case '(': addToken(TokenType.LEFT_PAREN); break;
            case ')': addToken(TokenType.RIGHT_PAREN); break;
            case '{': addToken(TokenType.LEFT_BRACE); break;
            case '}': addToken(TokenType.RIGHT_BRACE) ;break;
            case ',': addToken(TokenType.COMMA) ; break;
            case '.': addToken(TokenType.DOT) ; break;
            case '-': addToken(TokenType.MINUS) ; break;
            case '+': addToken(TokenType.PLUS) ; break;
            case ';': addToken(TokenType.SEMICOLON); break;
            case '*': addToken(TokenType.STAR); break;

            case '!':
                addToken(match('=') ? TokenType.BANG_EQUAL : TokenType.BANG);
                break;
            case '=':
                addToken(match('=') ? TokenType.EQUAL_EQUAL : TokenType.EQUAL);
                break;
            case '<':
                addToken(match('=') ? TokenType.LESS_EQUAL : TokenType.LESS);
                break;
            case '>':
                addToken(match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER);
                break;
            case '/':
                if(match('/')){
                    // This is comment
                    while(peek() != '\n' && !isAtEnd()) advance();
                }else{
                    addToken(TokenType.SLASH);
                }

            case ' ': 
            case '\r':
            case '\t':
                break;

            case '\n':
                line++;
                break;



            case '"': string(); break;



            default:
                if(isDigit(source.charAt(current))){
                    number();
                }else if(isAlpha(c)){
                    identifier(c);

                }else{
                    Tulip.error(line,"Unexpected Character.");
                }

                break;
        }
    }

    private void identifier(char c){
        while(isAlphaNumeric(c)) advance();

        String text = source.substring(start,current);
        TokenType type = keywords.get(text);
        if(type == null) type = TokenType.IDENTIFIER;
        addToken(type);

    }

    private static boolean isAlphaNumeric(char c){
        return isAlpha(c) || isDigit(c);
    }
    private static boolean isAlpha(char ch){
        return ((ch >= 'a' && ch<= 'z') || (ch >= 'A' && ch<= 'Z') || ch == '_');

    }

    private void number(){
        while (isDigit(peek())) advance();

        if(peek() == '.' && isDigit(peekNext())){
            advance();
            while(isDigit(peek())) advance();

        }

        addToken(TokenType.NUMBER,Double.parseDouble(source.substring(start,current)));
    }


    private char peekNext(){
        if(current + 1 >= source.length()) return '\0';
        return source.charAt(current+1);
    }

    private static boolean isDigit(char check){
        return check >= '0' && check <= '9';
    }

    private void string(){
        while(peek() != '"' && !isAtEnd()){
            if(peek() == '\n') line++;
            advance();
        }

        if(isAtEnd()){
            Tulip.error(line,"Unexpected Error");
        }

        advance();

        String tk = source.substring(start+1,current-1);
        addToken(TokenType.STRING,tk);

    }

    private char peek(){
        if(isAtEnd()) return '\0';
        return source.charAt(current);
    }


    private boolean match(char next){
        if(isAtEnd()) return false;
        if(source.charAt(current) != next) return false;

        current++;
        return true;
    }

    private char advance(){
        return source.charAt(current++);
    }


    private void addToken(TokenType tokenType){
        addToken(tokenType,null);

    }

    private void addToken(TokenType tokenType,Object literal){
        String text = source.substring(start,current);
        tokens.add(new Token(tokenType,text,literal,line));
    }

    Scanner(String source){
        this.source = source;
    }
}
