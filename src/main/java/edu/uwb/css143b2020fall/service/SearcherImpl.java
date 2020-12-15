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

        /*
        if(temp.length > 1){

        //a generated list of indices

            List<List<Integer>> placeHolder = new LinkedList<>();

            //find indices of all the words
            for(int i = 0; i < temp.length; ++i){
                placeHolder.add(findIndices(temp[i], index));
            }

            result = findSharedDocs(placeHolder);

            result = inRightOrder(temp, result, index);

            return result;





        }

         */

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

    /*
    //check if every word is inside a specific document
    public boolean isInsideDoc(String[] uWord, List<Integer> doc){
        boolean flag = true;
        for(int i = 0; i < uWord.length; ++i){
            if(!doc.contains(uWord[i]))
                flag = false;
        }


        return flag;

    }

     */
    /*


    public List<Integer> findIndices(String uWord, Map<String, List<List<Integer>>> index){
        List<Integer> indices = new LinkedList<>();
        //?
        for(int i = 0; i < index.get(uWord).size(); ++i){
            if(!index.get(uWord).get(i).isEmpty())
                indices.add(i);

        }
        return indices;
    }

    public List<Integer> findSharedDocs(List<List<Integer>> indices){
        List<Integer> resList = new LinkedList<>();
        Integer[] resArray = new Integer[indices.size()];

        //compare the first two index lists
        //a one step process
        resArray = findCommon(indices.get(0), indices.get(1));

        //conversion into a list to be used in the findCommon method
        for(int i = 0; i < resArray.length; ++i)
            resList.add(resArray[i]);

        for(int i = 2; i < indices.size() - 1; ++i){
            resArray = findCommon(resList, indices.get(i));
            //?
            resList.removeAll(resList);

            for(int j = 0; j < resArray.length; ++j)
                resList.add(resArray[j]);


        }
        return resList;


    }

    public Integer[] findCommon(List<Integer> listA, List<Integer> listB){
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();


        for(int i = 0; i < listA.size(); ++i)
            setA.add(listA.get(i));


        for(int i = 0; i < listB.size(); ++i)
            setB.add(listB.get(i));

        setA.retainAll(setB);

        Integer[] result = new Integer[setA.toArray().length];
        for(int i = 0; i < setA.toArray().length; ++i){
            result[i] = (Integer) setA.toArray()[i];
        }
        return result;

    }

    //words passed in are the searched keywords
    //uWord is created to facilitate the index finding process
    //another refinement step to find docs that have the words in the right order
    public List<Integer> inRightOrder(String[] words, List<Integer> commonDoc, Map<String, List<List<Integer>>> index){

        for(int i = 0; i < (words.length - 1); ++i){
            if(-1 != (index.get(words[i]).get(commonDoc.get(i)).indexOf(words[i]) - index.get(words[i + 1]).get(commonDoc.get(i + 1)).indexOf(words[i + 1])))
                commonDoc.remove((Integer)i);
        }
        return commonDoc;
    }

     */


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