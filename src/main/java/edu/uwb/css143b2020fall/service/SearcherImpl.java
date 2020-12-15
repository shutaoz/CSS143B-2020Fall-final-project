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

        //case 0 when the input is empty
        //return an empty list
        if(keyPhrase == null || index == null || keyPhrase == "")
            return result;



        String[] temp = keyPhrase.split("\\s+");

        List<String> tempList = new LinkedList<>();
        for(int i = 0; i < temp.length; ++i)
            tempList.add(temp[i]);

        //if this test pass, that means all the words in the input are at least somewhere inside the map
        if(!isInside(temp, index))
            return result;

        //when the input only contains 1 word
        if(temp.length == 1) {

            for(int i = 0; i < index.get(temp[0]).size(); ++i){
                if(!index.get(temp[0]).get(i).isEmpty())
                    result.add(i);
            }

            return result;

        }



        if(temp.length > 1){

            return orderDocs(temp, index, commonDocs(temp, index));

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



    public List<Integer> commonDocs(String[] words,Map<String, List<List<Integer>>> index){
        boolean flag = true;
        List<Integer> result = new LinkedList<>();

        //document size
        for(int i = 0; i < index.get(words[0]).size(); ++i){
            //string array size
            for(int j = 0; j < words.length; ++j){
                if(index.get(words[j]).get(i).isEmpty())
                    flag = false;

            }
            if(flag == true)
                result.add(i);

            flag = true;

        }
        return result;
    }

    public List<Integer> orderDocs(String[] words, Map<String, List<List<Integer>>> index, List<Integer> docs){
        boolean flag = true;
        List<Integer> result = new LinkedList<>();
        int count = docs.size();
        Integer[] docsArray = new Integer[count];
        for(int i = 0; i < docsArray.length; ++i)
            docsArray[i] = docs.get(i);

        for(int i = 0; i < count; ++i){
            for(int j = 0; j < (words.length - 1); ++j){
                for(int x = 0; x < index.get(words[j]).get(docs.get(i)).size(); ++x) {
                    int temp = x;
                    while(index.get(words[j + 1]).get(docs.get(i)).get(temp) < index.get(words[j]).get(docs.get(i)).get(0)) {
                        if(temp + 1 < index.get(words[j + 1]).get(docs.get(i)).size())
                            ++temp;
                        else
                            break;
                    }
                    if ((index.get(words[j]).get(docs.get(i)).get(x) - index.get(words[j + 1]).get(docs.get(i)).get(temp)) != -1 )
                        flag = false;

                }
            }
            if(!flag)
                docsArray[i] = -1;

            flag = true;
        }

        for(int i = 0; i < docsArray.length; ++i){
            if(docsArray[i] != -1)
                result.add(docsArray[i]);
        }



        return result;



    }


}