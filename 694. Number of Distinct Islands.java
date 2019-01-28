class Solution {
    public int numDistinctIslands(int[][] grid) {
        // inspired by solution, record path when found the path
        // tip! 当没有找到的点的时候也要记录，记录为sl/r/u/d
        // 原因是dfs退回的时候，可能跳过一个点，也可能跳过两个，需要记录
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        Set<String> paths = new HashSet<>();
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    sb.append('0');
                    travel(i, j, grid, sb);
                    System.out.println(sb.toString());
                    paths.add(sb.toString());
                }
            }
        }
        
        return paths.size();
    }
    
    private void travel(int x, int y, int[][] grid, StringBuilder path) {
        // we will only travel the points that is 1
        // left 'l'
        // right 'r'
        // up 'u'
        // down 'd'
        grid[x][y] = 0;
        if(validPoint(x, y - 1, grid)) {
            path.append('l');
            travel(x, y - 1, grid, path);
        } else {
            path.append("sl");
        }
        
        if (validPoint(x, y + 1, grid)) {
            path.append('r');
            travel(x, y + 1, grid, path);
        } else {
            path.append("sr");
        }
        
        if (validPoint(x - 1, y, grid)) {
            path.append('u');
            travel(x - 1, y, grid, path);
        } else {
            path.append("su");
        }
        
        if (validPoint(x + 1, y, grid)) {
            path.append('d');
            travel(x + 1, y, grid, path);
        } else {
            path.append("sd");
        }
        
    }
    
    private boolean validPoint(int x, int y, int[][] grid) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
            return true;
        }
        
        return false;
    }
}
