package com.panther.test.fourinone.chapter03;

import com.fourinone.FileAdapter;
import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

import java.io.File;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午11:46 18-3-24
 * @Version:
 */
public class WordCountWK extends MigrantWorker{

    @Override
    protected WareHouse doTask(WareHouse inhouse) {
        String filePath = inhouse.getString("filePath");
        long n = 64;
        long num = (new File(filePath)).length()/n +1;
        FileAdapter fa = null;
        FileAdapter.ReadAdapter ra = null;
        byte[] bts = null;
        HashMap<String, Integer> wordcount = new HashMap<String, Integer>();
        fa = new FileAdapter(filePath);
        for(long i = 0; i<num; i++){
            ra = fa.getReader(i*n, n);
            bts = ra.readAll();
            StringTokenizer tokenizer = new StringTokenizer(new String(bts));
            while(tokenizer.hasMoreTokens()){
                String curword = tokenizer.nextToken();
                if(wordcount.containsKey(curword)){
                    wordcount.put(curword, wordcount.get(curword) + 1);
                }else{
                    wordcount.put(curword, 1);
                }
            }
        }
        fa.close();
        return new WareHouse("word", wordcount);

    }

    public static void main(String[] args){
        WordCountWK wordCountWK = new WordCountWK();
        wordCountWK.waitWorking("localhost", 2010, "wordcount");
    }
}
