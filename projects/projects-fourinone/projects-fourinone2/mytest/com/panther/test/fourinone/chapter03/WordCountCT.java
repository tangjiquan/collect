package com.panther.test.fourinone.chapter03;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午11:56 18-3-24
 * @Version:
 */
public class WordCountCT extends Contractor {

    @Override
    public WareHouse giveTask(WareHouse inhouse) {
        WorkerLocal[] wks = getWaitingWorkers("wordcount");
        System.out.println("wks length:" + wks.length);

        WareHouse[] whs = doTaskBatch(wks, inhouse);
        HashMap<String, Integer> wordcount = new HashMap<String, Integer>();
        for(WareHouse result : whs){
            HashMap<String, Integer> wordmp = (HashMap<String, Integer>)result.get("word");
            for(Iterator<String> iter = wordmp.keySet().iterator(); iter.hasNext();){
                String curword = iter.next();
                if(wordcount.containsKey(curword)){
                    wordcount.put(curword, wordcount.get(curword) + wordmp.get(curword));
                }else{
                    wordcount.put(curword, wordmp.get(curword));
                }

            }
        }
        return new WareHouse("word", wordcount);
    }

    public static void main(String[] args){
        WordCountCT wordCountCT = new WordCountCT();
        long begin = (new Date()).getTime();
        WareHouse result = wordCountCT.giveTask(new WareHouse("filePath", "/home/tangjiquan/Study/projects/panther/source/collect/projects/projects-fourinone/projects-fourinone2/mytest/com/panther/test/fourinone/chapter03/first.txt"));
        long end  = (new Date()).getTime();
        System.out.println("time :" + (end - begin)/1000 + "s");
        System.out.println("result: " + result);

    }
}
