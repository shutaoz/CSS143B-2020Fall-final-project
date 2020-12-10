package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.*;

/*
DO NOT CHANGE
 */

@Service
public class SearcherImpl implements Searcher {
    public List<Integer> search(String keyPhrase, Map<String, List<List<Integer>>> index) {
        List<Integer> result = new ArrayList<>();
        // add your code

        if(keyPhrase == null || index == null || keyPhrase == "")
            return result;



        String[] temp = keyPhrase.trim().split("\\s+");

        if(!isInside(temp, index))
            return result;

        if(temp.length == 1) {
            /*
            if (!index.containsKey(temp[0])) {
                List<List<Integer>> tempList = index.get(temp[0]);
                for (int i = 0; i < tempList.size(); ++i) {
                    for (int j = 0; j < tempList.get(i).size(); ++j) {
                        if (tempList.get(i).get(j) != null) {
                            result.add(i);
                            break;

                        }

                    }

                }


            }
            return result;

             */
            for(int i = 0; i < index.get(temp[0]).size(); ++i){
                if(!index.get(temp[0]).get(i).isEmpty())
                    result.add(i);
            }

            return result;

        }

        if(temp.length > 1){



        }





        return result;
    }

    public boolean isInside(String[] uWord, Map<String, List<List<Integer>>> index){
        boolean flag = true;
        for(int i = 0; i < uWord.length; ++i){
            if(!index.containsKey(uWord[i]))
                flag = false;
        }
        return flag;
    }

    public boolean isInsideDoc(String[] uWord, List<Integer> doc){
        boolean flag = true;
        for(int i = 0; i < uWord.length; ++i){
            if(!doc.contains(uWord[i]))
                flag = false;
        }


        return flag;

    }

    public boolean isRightOrder(String[] uWord, List<Integer> doc){

    }
}