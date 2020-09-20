import java.util.HashMap;
import java.util.Map;

public class PathsWithSum {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int val) {
            this.value = val;
            this.left = null;
            this.right = null;
        }
    }

    /*
     * Divide and Conquer approach
     * pathSum and countSum are the functions that handle this problem
     * Time: O(n log n) because each recursive call is O(n) and for a balanced tree is
     * T(n) = n + 2T(n/2) = O( n log(n)) for best case and worst is O(n^2) because tree becomes a singly linked list
     * Space: O(log n) because of the best case for time with halving the tree.
     * Worst case for space is O(n).
     */
    public static class BST {
        public int pathSum(Node root, int sum) {
            if (root == null) {
                return 0;
            }

            return countPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
        }

        private int countPath(Node root, int target) {
            int count = 0;
            if (root == null) {
                return count;
            }
            if (root.value == target) {
                count++;
            }

            count += countPath(root.left, target - root.value);
            count += countPath(root.right, target - root.value);
            return count;
        }
    }

    /*
     * Best solution?
     * Prefix sum and backtracking
     * Time: O(n)
     * Space: O(n)
     * Reducing down first approach by storing prefix and removing duplicate calculations
     */
    public static class BinaryTree {
        public int pathSum(Node root, int sum) {
            if (root == null) {
                return 0;
            }

            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            return countPath(root, sum, 0, map);
        }

        private int countPath(Node root, int target, int currSum, Map<Integer, Integer> map) {
            if (root == null) {
                return 0;
            }

            currSum += root.value;
            //check if subarray sum equals target
            int count = map.getOrDefault(currSum - target, 0);
            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
            //extend to left and right child
            count += countPath(root.left, target, currSum, map) + countPath(root.right, target, currSum, map);
            //remove the current node so it wont affect other path
            map.put(currSum, map.get(currSum) - 1);
            return count;
        }
    }

    /*
     * Book approach
     * Time: O(n) for n nodes in tree
     * Space: O(log n) for balanced tree and O(n) for unbalanced
     */
    public static class BT
    {
        public static int countPathsWithSum(Node root, int targetSum) {
            return countPathsWithSum(root, targetSum, 0, new HashMap<Integer, Integer>());
        }

        public static int countPathsWithSum(Node node, int targetSum, int runningSum, HashMap<Integer, Integer> pathCount) {
            if (node == null) return 0; // Base case

            runningSum += node.value;

            /* Count paths with sum ending at the current node. */
            int sum = runningSum - targetSum;
            int totalPaths = pathCount.getOrDefault(sum, 0);

            /* If runningSum equals targetSum, then one additional path starts at root. Add in this path.*/
            if (runningSum == targetSum) {
                totalPaths++;
            }

            /* Add runningSum to pathCounts. */
            incrementHashTable(pathCount, runningSum, 1);

            /* Count paths with sum on the left and right. */
            totalPaths += countPathsWithSum(node.left, targetSum, runningSum, pathCount);
            totalPaths += countPathsWithSum(node.right, targetSum, runningSum, pathCount);

            incrementHashTable(pathCount, runningSum, -1); // Remove runningSum
            return totalPaths;
        }

        public static void incrementHashTable(HashMap<Integer, Integer> hashTable, int key, int delta) {
            int newCount = hashTable.getOrDefault(key, 0) + delta;
            if (newCount == 0) { // Remove when zero to reduce space usage
                hashTable.remove(key);
            } else {
                hashTable.put(key, newCount);
            }
        }
    }

    public static void main(String[] args)
    {

    }
}

