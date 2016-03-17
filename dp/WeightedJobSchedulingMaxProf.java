package coding;

import java.util.Arrays;
import java.util.Comparator;

class Job{
    int start;
    int end;
    int profit;
    Job(int start, int end, int profit){
        this.start = start;
        this.end = end;
        this.profit = profit;
    }        
}

class JobFinnishTimeComparator implements Comparator<Job>{
    
    @Override
    public int compare(Job job1, Job job2){
        return job1.end > job2.end ? 1 : -1;
    }
}


public class WeightedJobSchedulingMaxProf {

    public int maximum(Job[] jobs){
        int maxVal = Integer.MIN_VALUE;
        
        int T[] = new int[jobs.length];
        JobFinnishTimeComparator jtc = new JobFinnishTimeComparator();
        Arrays.sort(jobs, jtc);
        
        T[0] = jobs[0].profit;
        
        for(int i=1;i<jobs.length;i++){
            T[i] = Math.max(jobs[i].profit, T[i]);
            for (int j=i-1;j>=0;j--){
                if (jobs[j].end <= jobs[i].start){
                    T[i]=Math.max(T[i], jobs[i].profit + T[j]);
                    break;
                }
                
            }
        }
        for (int val:T){
            maxVal = maxVal < val ?  val : maxVal;
        }
        return maxVal;
    }
    

    public static void main(String args[]){
        Job jobs[] = new Job[6];
        jobs[0] = new Job(1,3,5);
        jobs[1] = new Job(2,5,106);
        jobs[2] = new Job(4,6,5);
        jobs[3] = new Job(6,7,4);
        jobs[4] = new Job(5,8,11);
        jobs[5] = new Job(7,9,2);
        WeightedJobSchedulingMaxProf wmp = new WeightedJobSchedulingMaxProf();
        System.out.println(wmp.maximum(jobs));
        
    }
    
}
