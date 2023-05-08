// Zach Sally

// ==================
// |  Diamond.java  |
// ==================

// Prints a diamond of odd, positive width to the terminal.
// This program relies on test-driven development, functional decomposition, DRY
// and self-documenting code practices

public class Diamond
{
    public static void main(String[] args)
    {
        if (args.length < 1)
        {
            System.err.println("No input detected, run java Diamond <n>");
            return;
        }

        int width = Integer.parseInt(args[0]);

        printDiamond(width);
    }

    public static void printDiamond(int width)
    {
        if (width <= 0)
        {
            System.out.println("OOPS, we can't have a non-positive width");
            return;
        }

        if (width % 2 == 0)
        {
            System.out.println("OOPS, we can't have an even width");
            return;
        }

        printBar(width);

        // Print each line for top of diamond
        for (int numStars = 1; numStars <= width; numStars += 2)
            printLine(numStars, width);

        // Print each line for bottom of diamond
        for (int numStars = width - 2; numStars >= 1; numStars -= 2)
            printLine(numStars, width);

        printBar(width);
    }

    public static void printBar(int width)
    {
        System.out.print("+-");
        printChar('-', width);
        System.out.println("-+");
    }

    public static void printLine(int numStars, int width)
    {
        System.out.print("| ");

        int numSpaces = (width - numStars) / 2;

        printChar(' ', numSpaces);
        printChar('*', numStars);
        printChar(' ', numSpaces);

        System.out.println(" |");
    }

    public static void printChar(char ch, int num)
    {
        for (int i = 1; i <= num; i++)
            System.out.print(ch);
    }
}