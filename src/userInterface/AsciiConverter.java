package userInterface;

/* Ascii Converter Class takes handles all basic conversions of integers and chars according to ASCII Chart */

public class AsciiConverter {
    private final int lowerCaseA = 97;
    private final int lowerCaseZ = 122;
    private final int upperCaseA = 65;
    private final int upperCaseZ = 90;
    private final int one = 49;
    private final int nine = 57;
    private final int INVALID = -1;

    AsciiConverter() {
    }

    public char intToUppercase(int i) {
        return (char) (i + upperCaseA);
    }

    public char intToLowercase(int i) {
        return (char) (i + lowerCaseA);
    }

    public int charToInt(char asciiValue) {
        if (asciiValue >= lowerCaseA && asciiValue <= lowerCaseZ) {
            return (asciiValue - lowerCaseA);
        }
        if (asciiValue >= upperCaseA && asciiValue <= upperCaseZ) {
            return (asciiValue - upperCaseA);
        }
        return INVALID;
    }

    public int charIntToInt(char asciiValue) {
        if (asciiValue >= one && asciiValue <= nine) {
            return (asciiValue - one);
        }
        return INVALID;
    }
}
