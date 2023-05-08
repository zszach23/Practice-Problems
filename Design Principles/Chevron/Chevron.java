// Zach Sally

// ==================
// |  Chevron.java  |
// ==================

// The first line of output will contain n '@' symbols, followed by n '*' symbols,
// followed by another n '@' symbols, followed by a newline.

// Every line afterwards will be the same as the previous line of output, but without the first
// character from the previous line of output, up to the last line, which contains a single '@' symbol
// followed by a newline character

public class Chevron
{
    public static final int MAX_SYMBOLS = 3;

    public static void main(String[] args)
    {
        if (args.length < 1)
        {
            System.err.println("No input detected, run java Chevron <n>");
            return;
        }

        int n = Integer.parseInt(args[0]);

        printChevron(n);
    }

    public static void printChevron(int n)
    {
        if (n <= 0)
        {
            System.out.println("OOPS, we can't have a non-positive input");
            return;
        }

        StringBuilder str = buildSymbolString(n);

        // Print the substring from line to end
        for (int line = 0; line < MAX_SYMBOLS * n; line++)
            System.out.println(str.substring(line));
    }

    public static StringBuilder buildSymbolString(int n)
    {
        StringBuilder str = new StringBuilder(MAX_SYMBOLS * n);

        appendChar(str, '@', n);
        appendChar(str, '*', n);
        appendChar(str, '@', n);

        return str;
    }

    public static void appendChar(StringBuilder str, char ch, int num)
    {
        for (int i = 1; i <= num; i++)
            str.append(ch);
    }
}