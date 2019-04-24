package sources;
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
    static Token ParseToken(String info){
        Token answer = new Token();
        String[] splitter = info.split(" ");
        answer.t = getType(splitter[0]);
        answer.value = splitter[1];
        return answer;
    }
}
