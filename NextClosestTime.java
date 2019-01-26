class NextClosestTime {
// https://leetcode.com/problems/next-closest-time/
//1. special case: 00:00
//2. time 2 is smaller than time1
//3. skip input time
    public String nextClosestTime(String time) {
        if (time == null) {
            return null;
        }
        
        int currentMin = 60 * 24 * 2;
        String currentRet = time;
        
        for (int i = 0; i < time.length(); i++) {
            if (i == 2) {
                continue;
            }
            for (int j = 0; j < time.length(); j++) {
                if (j == 2) {
                    continue;
                }
                for(int p = 0; p < time.length(); p++) {
                    if (p == 2) {
                        continue;
                    }
                    
                    for (int q = 0; q < time.length(); q++) {
                        if (q == 2) {
                            continue;
                        }
                        char hour1 = time.charAt(i);
                        char hour2 = time.charAt(j);
                        char minute1 = time.charAt(p);
                        char minute2 = time.charAt(q);
                        if(validTime(hour1, hour2, minute1, minute2)) {
                            String time2 = new StringBuilder()
                                .append(hour1)
                                .append(hour2)
                                .append(':')
                                .append(minute1)
                                .append(minute2)
                                .toString();
                            if (time2.equals(time)) {
                                continue;
                            }
                            int delta = deltaInMinutes(time, time2);
                            // System.out.println(time2);
                            // System.out.println(delta);
                            // System.out.println("----");
                            if(delta <= currentMin) {
                                currentMin = delta;
                                currentRet = time2;
                            }
                        }
                    }
                }
            }
        }
        
        return currentRet;
    }
    
    private boolean validTime(char hour1, char hour2, char minute1, char minute2) {
        if ((hour1 - '0') * 10 + (hour2 - '0') >= 24) {
            return false;
        }
        if ((minute1 - '0') * 10 + (minute2 - '0') >= 60) {
            return false;
        }
        
        
        return true;
    }
    
    private int deltaInMinutes(String time1, String time2) {
        int hour1 = (time1.charAt(0) - '0') * 10 + time1.charAt(1) - '0';
        int minute1 = (time1.charAt(3) - '0') * 10 + time1.charAt(4) - '0';
        int timeInMintue1 = hour1 * 60 + minute1;
        
        int hour2 = (time2.charAt(0) - '0') * 10 + time2.charAt(1) - '0';
        int minute2 = (time2.charAt(3) - '0') * 10 + time2.charAt(4) - '0';
        int timeInMintue2 = hour2 * 60 + minute2;
        
        if (timeInMintue2 >= timeInMintue1) {
            return timeInMintue2 - timeInMintue1;
        } else {
            return timeInMintue2 + 60 * 24 - timeInMintue1;
        }
        
    }
}
