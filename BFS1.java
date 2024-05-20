// Problem 1: https://leetcode.com/problems/binary-tree-level-order-traversal/
// Time Complexity: O(n)
// Space Complexity: O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Approach: Using BFS, we can traverse the tree level by level. We can use a queue to store the nodes at each level. We can add the nodes to the queue and then process them level by level. We can add the nodes to the result list at each level.

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.val);
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }
            result.add(level);
        }
        return result;

    }
}

// Problem 2: https://leetcode.com/problems/course-schedule/
// Time Complexity: O(V+E)
// Space Complexity: O(V+E)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Approach: We can use a hashmap to store the prerequisites of each course. We
// can also store the indegree of each course. We can use a queue to store the
// courses with indegree 0. We can then process the courses one by one and
// reduce the indegree of the courses dependent on the current course. If the
// indegree of the dependent course becomes 0, we can add it to the queue. We
// can keep a count of the courses processed and return true if the count is
// equal to the number of courses.

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int count = 0;
        for (int[] pr : prerequisites) {
            indegree[pr[0]]++;
            if (!map.containsKey(pr[1])) {
                map.put(pr[1], new ArrayList<>());
            }
            map.get(pr[1]).add(pr[0]);
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                count++;
            }
        }
        if (count == numCourses)
            return true;
        while (!q.isEmpty()) {
            Integer course = q.poll();
            List<Integer> li = map.get(course);
            if (li != null) {
                for (Integer c : li) {
                    indegree[c]--;
                    if (indegree[c] == 0) {
                        q.add(c);
                        count++;
                        if (count == numCourses)
                            return true;
                    }
                }
            }
        }
        return false;
    }
}