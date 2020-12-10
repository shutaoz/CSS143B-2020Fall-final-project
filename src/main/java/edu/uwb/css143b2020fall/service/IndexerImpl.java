package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> index(List<String> docs) {
        Map<String, List<List<Integer>>> indexes = new HashMap<>();
        // add your code

        if(docs.isEmpty())    return null;

        for(int i = 0; i < getWords(docs).size(); ++i){
            indexes.put(getWords(docs).get(i), getLocations(getWords(docs).get(i), docs));

        }

        return indexes;
    }

    public List<List<Integer>> getLocations(String uWord, List<String> docs){
        List<List<Integer>> locations = new LinkedList<>();

        for(int i = 0; i < docs.size(); ++i){

            String[] temp = docs.get(i).trim().split("\\s+");
            locations.add(new LinkedList<>());

            for(int j = 0; j < temp.length; ++j){
                if(temp[j].equals(uWord))
                    locations.get(i).add(j);



            }
        }
        return locations;
    }

    public List<String> getWords(List<String> docs){
        List<String> uniqueWords = new LinkedList<>();

        for(int i = 0; i < docs.size(); ++i){

            String[] temp = docs.get(i).trim().split("\\s+");


            for(int j = 0; j < temp.length; ++j){
                if(!uniqueWords.contains(temp[j]))
                    uniqueWords.add(temp[j]);



            }
        }
        return uniqueWords;
    }
}