package com.itbuka.entity;

import java.util.ArrayList;
import java.util.List;

class bishiCode  {
    List<String> res=new ArrayList<>();
    List<List<String>> ans=new ArrayList<>();
    public List<List<String>> subsets(String[] nums) {
        dfs(0,nums);
        return ans;
    }
    public void dfs(int cur,String[] nums){
        if(cur==nums.length){
            ans.add(new ArrayList<String>(res));
            return;
        }
        res.add(nums[cur]);
        dfs(cur+1,nums);
        res.remove(res.size()-1);
        dfs(cur+1,nums);
    }
}