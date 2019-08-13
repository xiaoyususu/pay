package com.pay.gateway;

/**
 * @ClassName Factorization
 * @Description 因数分解
 * @Author boy
 * @Date 2019/8/13 2:22 PM
 */
public class Factorization {

    /*
     * @Author boy
     * @Description 对一个整数进行因数分解
     * @Date 2019/8/13 2:24 PM
     * @Param [num]
     * @return void
     */
    public static void factor(int num) {
        double sqr = Math.sqrt(num);
        System.out.println("因数分解结果：");
        for(int i=2;i<=sqr;i++){
            if(num%i == 0) {
                System.out.print(i+" ");
                num/=i;
                i--;
            }
        }
    }

    public static void main(String args[]){
        factor(100);
    }
}
