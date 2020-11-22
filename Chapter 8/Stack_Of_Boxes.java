package Chapter_8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Stack_Of_Boxes
{

    //my support class
    public static class mainBox
    {
        private int width, length, height;
        public mainBox(int w, int l, int h)
        {
            this.width = w;
            this.length = l;
            this.height = h;
        }

        public boolean canStack(mainBox b)
        {
            return b == null || (this.width < b.width &&
                    this.length < b.length &&
                    this.height < b.height);
        }

        public String toString()
        {
            return "(" + width + ", " + length + ", " + height + ")";
        }

    }
    /*
     * My Approach
     * Time:
     * Space:
     */
    public static ArrayList<mainBox> buildTallStack(mainBox[] boxes)
    {
        if(boxes == null) return null;
        return buildTallStack(boxes,null);
    }

    public static ArrayList<mainBox> buildTallStack(mainBox[] boxes, mainBox bottom)
    {
        int maxHeight = 0;
        ArrayList<mainBox> maxStack = null;
        for(mainBox box : boxes)
        {
            if(box.canStack(bottom))
            {
                ArrayList<mainBox> stackOfBoxes = buildTallStack(boxes, box);
                int height = getStackHeight(stackOfBoxes);
                if(height > maxHeight)
                {
                    maxHeight = height;
                    maxStack = stackOfBoxes;
                }
            }
        }

        if(maxStack == null) maxStack = new ArrayList<>();
        if(bottom != null) maxStack.add(0, bottom);
        return maxStack;
    }

    private static int getStackHeight(List<mainBox> boxes)
    {
        int height = 0;
        for(mainBox b : boxes) height += b.height;
        return height;
    }


    /*
     * Book Support Classes
     */
    public class Box
    {
        public int width;
        public int height;
        public int depth;
        public Box(int w, int h, int d) {
            width = w;
            height = h;
            depth = d;
        }

        public boolean canBeUnder(Box b) {
            if (width > b.width && height > b.height && depth > b.depth) {
                return true;
            }
            return false;
        }

        public boolean canBeAbove(Box b) {
            if (b == null) {
                return true;
            }
            if (width < b.width && height < b.height && depth < b.depth) {
                return true;
            }
            return false;
        }

        public String toString() {
            return "Box(" + width + "," + height + "," + depth + ")";
        }
    }

    public static class BoxComparator implements Comparator<Box>
    {
        @Override
        public int compare(Box x, Box y){
            return y.height - x.height;
        }
    }

    /*
     * Book Approach 1
     * Time:
     * Space:
     */
    public static int createStack(ArrayList<Box> boxes) {
        Collections.sort(boxes, new BoxComparator());
        int maxHeight = 0;
        for (int i = 0; i < boxes.size(); i++) {
            int height = createStack(boxes, i);
            maxHeight = Math.max(maxHeight, height);
        }
        return maxHeight;
    }

    public static int createStack(ArrayList<Box> boxes, int bottomIndex) {
        Box bottom = boxes.get(bottomIndex);
        int maxHeight = 0;
        for (int i = bottomIndex + 1; i < boxes.size(); i++) {
            if (boxes.get(i).canBeAbove(bottom)) {
                int height = createStack(boxes, i);
                maxHeight = Math.max(height, maxHeight);
            }
        }
        maxHeight += bottom.height;
        return maxHeight;
    }

    /*
     * Book Approach 2
     * Time:
     * Space:
     */
    public static int createStack2(ArrayList<Box> boxes) {
        Collections.sort(boxes, new BoxComparator());
        int maxHeight = 0;
        int[] stackMap = new int[boxes.size()];
        for (int i = 0; i < boxes.size(); i++) {
            int height = createStack2(boxes, i, stackMap);
            maxHeight = Math.max(maxHeight, height);
        }
        return maxHeight;
    }

    public static int createStack2(ArrayList<Box> boxes, int bottomIndex, int[] stackMap) {
        if (bottomIndex < boxes.size() && stackMap[bottomIndex] > 0) {
            return stackMap[bottomIndex];
        }

        Box bottom = boxes.get(bottomIndex);
        int maxHeight = 0;
        for (int i = bottomIndex + 1; i < boxes.size(); i++) {
            if (boxes.get(i).canBeAbove(bottom)) {
                int height = createStack2(boxes, i, stackMap);
                maxHeight = Math.max(height, maxHeight);
            }
        }
        maxHeight += bottom.height;
        stackMap[bottomIndex] = maxHeight;
        return maxHeight;
    }

    /*
     * Book Approach 3
     * Time:
     * Space:
     */
    public static int createStack3(ArrayList<Box> boxes) {
        Collections.sort(boxes, new BoxComparator());
        int[] stackMap = new int[boxes.size()];
        return createStack3(boxes, null, 0, stackMap);
    }

    public static int createStack3(ArrayList<Box> boxes, Box bottom, int offset, int[] stackMap) {
        if (offset >= boxes.size()) {
            return 0;
        }

        /* height with this bottom */
        Box newBottom = boxes.get(offset);
        int heightWithBottom = 0;
        if (bottom == null || newBottom.canBeAbove(bottom)) {
            if (stackMap[offset] == 0) {
                stackMap[offset] = createStack3(boxes, newBottom, offset + 1, stackMap);
                stackMap[offset] += newBottom.height;
            }
            heightWithBottom = stackMap[offset];
        }

        /* without this bottom */
        int heightWithoutBottom = createStack3(boxes, bottom, offset + 1, stackMap);

        return Math.max(heightWithBottom, heightWithoutBottom);
    }

}
