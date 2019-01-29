class Solution {
// https://leetcode.com/problems/network-delay-time/
    public int networkDelayTime(int[][] times, int N, int K) {
        // Having a hash table<Node, time> storing minal time getting to the node
        // Having a hash table<Node, int[]> storing from a node, how much time takes to all nearby node
        if (times == null || times.length == 0 || times[0] == null || times[0].length == 0) {
            return -1;
        }
        
        HashMap<Integer, Integer> reachTime = new HashMap<>();
        HashMap<Integer, List<int[]>> directTime = new HashMap<>();
        
        for(int[] time: times) {
            int startNode = time[0];
            int endNode = time[1];
            int travelTime = time[2];
            if (directTime.containsKey(startNode)) {
                directTime.get(startNode).add(new int[] {endNode, travelTime});
            } else {
                List<int[]> entry = new ArrayList<>();
                entry.add(new int[] {endNode, travelTime});
                directTime.put(startNode, entry);
            }
        }
        
        for(int node: directTime.keySet()) {
            List<int[]> value = directTime.get(node);
            Collections.sort(value, (v1, v2) -> {return v1[1] - v2[1];});
        }
        
        for(int i = 1; i <= N; i++) {
            reachTime.put(i, Integer.MAX_VALUE);
        }
        
        dfs(0, K, reachTime, directTime);
        
        int maxTime = 0;
        for(int value: reachTime.values()) {
            if (value == Integer.MAX_VALUE) {
                return -1;
            }
            maxTime = Math.max(maxTime, value);
        }
        
        return maxTime;
        
    }
    
    private void dfs(int travelTime, int node, HashMap<Integer, Integer> reachTime, HashMap<Integer, List<int[]>> directTime) {
        if(travelTime >= reachTime.get(node)) {
            return;
        }
        
        reachTime.put(node, travelTime);
        if (directTime.containsKey(node)) {
            for(int[] next: directTime.get(node)) {
                dfs(travelTime + next[1], next[0], reachTime, directTime);
            }
        }
    }
}
