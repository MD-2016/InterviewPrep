public class DrawLine {

    public static void drawLine(byte[] screen, int width, int x1, int x2, int y)
    {
        int startOffset = x1 % 8;
        int firstByte = startOffset == 0 ? x1 / 8 : x1 / 8 + 1;
        int endOffset = x2 % 8;
        int lastByte = endOffset == 7 ? x2/8 : x2/8 - 1;

        //draw line for full bytes
        for(int i = firstByte; i <= lastByte; ++i)
        {
            screen[i + width/8 * y] = (byte) 0xFF;
        }

        //draw start and end line
        byte startMask = (byte) (0xFF >> startOffset);
        byte endMask = (byte) ~(0xFF >> (endOffset + 1));
        if(x1/8 == x2/8)
        {
            byte mask = (byte) (startMask & endMask);
            screen[x1/8 + width/8*y] |= mask;
        }
        else
        {
            if(startOffset != 0)
            {
                screen[firstByte - 1 + width/8*y] |= startMask;
            }
            if(endOffset != 7)
            {
                screen[lastByte + 1 + width/8*y] |= endMask;
            }
        }

    }

    public static void print(Object o) {
        System.out.print(o);
    }


    public static void println() {
        print(lineSeparator());
    }

    public static void println(Object o) {
        print(o);
        println();
    }
    private static String lineSeparator() {
        return System.lineSeparator();
    }

    private static void printScreen(byte[] screen, int width)
    {
        int num = 1, widthNum = width/8;
        for(byte b : screen)
        {
            for(int i = 7; i >= 0; --i)
            {
                print((b >> i) & 1);
            }
            if(num % widthNum == 0) println();
            num++;
        }
        println();
    }

    public static void main(String[] args)
    {

    }

}
