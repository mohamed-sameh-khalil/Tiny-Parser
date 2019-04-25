package sources;

import java.util.ArrayList;
import java.util.List;

enum type {
    RESERVED,
    NUMBER,
    ID,
    SPECIAL,
    INVALID
}

public class Token {
    type t;
    String value;
    public Token(){}
    public Token(type t, String value){
        this.value = value;
        this.t = t;
    }
    static type getType (String str){
        switch (str) {
            case "reserved" :
                return type.RESERVED;
            case "number" :
                return type.NUMBER;
            case "identifier":
                return type.ID;
            case "special_character":
                return type.SPECIAL;
            default:
                return type.INVALID;
        }
    }
    static Token parseToken(String info){
        Token answer = new Token();
        String[] splitter = info.split(" ");
        answer.t = getType(splitter[0]);
        answer.value = splitter[1];
        return answer;
    }
    static List<Token> parseTokens(String scanned){
        List<Token> tokens = new ArrayList<>();
        String[] arr = scanned.split("\\r?\\n");//to work on windows or linux endlines
        for (String str :
                arr) {
            if(str.length() > 2)
                tokens.add(parseToken(str));
        }
        return tokens;
    }
}
