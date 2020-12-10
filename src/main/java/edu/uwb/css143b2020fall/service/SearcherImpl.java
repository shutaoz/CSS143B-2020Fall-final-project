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

        if(temp.length == 1) {
            boolean flag = false;
            if (!index.containsKey(temp[0])) {
                List<List<Integer>> tempList = index.get(temp[0]);
                for (int i = 0; i < tempList.size(); ++i) {
                    for (int j = 0; j < tempList.get(i).size(); ++j) {
                        if (tempList.get(i).get(j) != null) {
                            result.add(i);
                            flag = true;

                        }
                        if(flag == true)
                            break;
                    }

                }


            }
            return result;
        }





        return result;
    }
}