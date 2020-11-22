package Chapter_8;

import java.awt.*;

public class Paint_Fill
{
    enum Color
    {
        Red, Pink, Green, Yellow, Blue, Black, White
    }

    /*
     * My Approach
     * Focus on integers instead of using colors
     * Time: O(n)
     * Space: O(n)
     */
    public int[][] paintFill(int[][] image, int sr, int sc, int c)
    {
        int color = image[sr][sc];
        if(color != c) dfs(image, sr, sc, color, c);
        return image;
    }

    public void dfs(int[][] image, int r, int c, int color, int newColor)
    {
        if(image[r][c] == color)
        {
            image[r][c] = newColor;
            if(r >= 1) dfs(image, r-1, c, color, newColor);
            if(c >= 1) dfs(image, r, c-1, color, newColor);
            if(r+1 < image.length) dfs(image, r+1, c, color, newColor);
            if(c+1 < image[0].length) dfs(image, r, c+1, color, newColor);
        }
    }


    /*
     * Book Approach
     * Time: O(n x m)
     * Space: O(n)
     */

    //support code
    public static String PrintColor(Color c)
    {
        switch(c)
        {
            case Black:
                return "BL";
            case White:
                return "W";
            case Red:
                return "R";
            case Yellow:
                return "Y";
            case Green:
                return "G";
            case Pink:
                return "P";
            case Blue:
                return "B";
        }
        return "X";
    }

    //Support code
    public static void PrintScreen(Color[][] screen)
    {
        for(int r = 0; r < screen.length; r++)
        {
            for(int c = 0; c < screen[0].length; c++)
            {
                System.out.print(PrintColor(screen[r][c]));
            }
            System.out.println();
        }
    }

    //Support code
    public static int randomInt(int n)
    {
        return (int) (Math.random() * n);
    }

    //Main algorithm
    public static boolean PaintFill(Color[][] screen, int r, int c, Color ocolor, Color ncolor)
    {
        if(r < 0 ||r >= screen.length || c < 0 || c >= screen[0].length)
        {
            return false;
        }
        if(screen[r][c] == ocolor)
        {
            screen[r][c] = ncolor;
            PaintFill(screen, r-1, c, ocolor, ncolor);
            PaintFill(screen, r + 1, c, ocolor, ncolor);
            PaintFill(screen, r, c - 1, ocolor, ncolor);
            PaintFill(screen, r, c + 1, ocolor, ncolor);
        }
        return true;
    }

    //Main Algorithm
    public static boolean PaintFill(Color[][] screen, int r, int c, Color color)
    {
        if(screen[r][c] == color)
        {
            return false;
        }
        return PaintFill(screen, r, c, screen[r][c], color);
    }

}
