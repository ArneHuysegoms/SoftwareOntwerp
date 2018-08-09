package util;

public class MutableString {
    private String string;

    public MutableString(String str){
        this.string = str;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void appendChar(char toAdd) {
        string+=toAdd;
    }

    public void removeLastChar(){
        if(string.length()!=0)
            string.substring(0,string.length()-2);
    }

    public int length(){
        return string.length();
    }
}
