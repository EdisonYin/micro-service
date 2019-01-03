package com.light.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.light.springboot.dao.mapper.UserMapper;

@Controller
public class ViewController {

    @Autowired
    private UserMapper userMapper;
    
    @GetMapping("/login")
    public String login() {

		return"login";
    }

    @GetMapping("/hello-world")
    public String helloWorld() {

        return"twts/index";
    }
    //login
    //@RequestMapping("/login", method = "POST")
    @PostMapping("/login")
    public String do_login() {
		return "main";
    }
    
    @GetMapping("/helloworld2")
    @ResponseBody
    public Object helloworld_2() {
       // return userMapper.getUsers();
        System.out.println("Hello  ");
    	return maxNum(6,2);
    }

    //1. 算法题
    //                      题目描述:
    //    	使用火柴组成一个最大的数字, 规定:
    //    	可组成的数字  1   2   3   4   5   6   7   8   9
    //    	所需火柴数量  2   5   5   4   5   6   3   7   6
    //    	给定火柴总数m, 组成n位数字, 输出可以组成的最大值. 如果不能组成最大的数字, 输出0
    public static int need_huocai_shulian[] = {2,3,4,5,6,7};
    public static int dui_ying_zuidashuzhi[] = {1,7,4,5,9,8};
    public int countMN(int m, int n) {
    	int max_num = 0;
    	if (n == 1) {
    		for (int i=0; i< need_huocai_shulian.length; i++) {
    			if (need_huocai_shulian[i] == m) {
    				max_num = dui_ying_zuidashuzhi[i];
    			}
    		}
    	} else {
    		if (m >= 2 * n && m <= 7*n) {
    			combine_decrease(m,n);
    		}
    	}
    	return max_num;
    }
    
    int completedTime = 0;
    int start = 0;
    int sum = 0;
    public int combine_decrease(int m, int n){
		for (int i=0; i< need_huocai_shulian.length; i++) {
           System.out.println(need_huocai_shulian[i]);
           sum = need_huocai_shulian[i];
           System.out.println("sum:"+sum);
           start++;
           if (start == n) {
        	   if (completedTime == n) {
        		   return 0;
        	   } else {
            	   start = 0;
            	   completedTime++;
            	   combine_decrease(m,n);   
        	   }
           } else {
        	   combine_decrease(m,n);
           }
		}
		return 0;
    }
    private String maxNum(int m, int n) {
        int rn = n;  // 还需要组成多少位
        final StringBuilder sb = new StringBuilder();

        // 选择优先级数组
        final int[] numbs = new int[]{9, 8, 7, 5, 4, 1};
        final int[] match = new int[]{6, 7, 3, 5, 4, 2};

        // 构建每一位
        while (rn > 0) {
            int i = -1;
            for (int j = 0; j < numbs.length; j++) {
                if (m - match[j] >= (rn - 1) * 2 
                && m - match[j] <= (rn - 1) * 7) {
                    // 确定了一位最优解的数字
                    i = j;
                    m -= match[j];
                    rn--;
                    break;
                }
            }
            if (i == -1) return "0";
            // 使用StringBuilder 避免数字溢出问题.
            sb.append(numbs[i]);
        }
        return sb.toString();
    }
}
