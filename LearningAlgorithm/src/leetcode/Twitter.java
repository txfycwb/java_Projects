package leetcode;
import java.util.*;
import java.util.Date;
class Twitter {
    private  HashMap<Integer, LinkedList<IdAT>> selfT= new HashMap<Integer, LinkedList<IdAT>>();
    private  HashMap<Integer, LinkedList<Integer>> followId= new HashMap<Integer, LinkedList<Integer>>();

    private class IdAT{
        int id;
        Date time;
        public IdAT(int id){
            this.id =id;
            this.time = new Date();
        }
    }
    /** Initialize your data structure here. */
    public Twitter() {

    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if(!selfT.containsKey(userId)) create(userId);
        selfT.get(userId).add(new IdAT(tweetId));
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        if(!selfT.containsKey(userId)) return null;
        PriorityQueue<IdAT> q = new PriorityQueue<IdAT>((a,b)->{return b.time.compareTo(a.time);});
        List<Integer> out = new LinkedList<>();
        for(IdAT t:selfT.get(userId))
            q.offer(t);
        for(int i:followId.get(userId))
            for(IdAT t:selfT.get(i))
                q.offer(t);
        int i = 0;
        while(q.size()!=0&&i<10){
            out.add(q.poll().id);
            i++;
        }
        if(out.size()==0) return null;
        return out;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(!selfT.containsKey(followerId)) create(followerId);
        if(!selfT.containsKey(followeeId)) create(followeeId);
        followId.get(followerId).add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!selfT.containsKey(followerId)) create(followerId);
        if(!selfT.containsKey(followeeId)) create(followeeId);
        followId.get(followerId).remove(followId.get(followerId).indexOf(followeeId));
    }

    private void create(int id){
        selfT.put(id, new LinkedList<IdAT>());
        followId.put(id, new LinkedList<Integer>());
    }

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
    
    public static void main(String[] args){
    	Twitter t =new Twitter();
    	t.postTweet(1, 1);
    	t.follow(2, 1);
    	System.out.println(Arrays.toString(t.getNewsFeed(2).toArray(new Integer[1])));
    	t.unfollow(2, 1);
    	System.out.println(Arrays.toString(t.getNewsFeed(2).toArray(new Integer[1])));
    }
}