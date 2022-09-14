package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        StorageStrategy strategy = new HashMapStorageStrategy();
        testStrategy(strategy, 10000);

        StorageStrategy strategy1 = new OurHashMapStorageStrategy();
        testStrategy(strategy1, 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> result = new HashSet<>();
        for(String s : strings){
            result.add(shortener.getId(s));
        }
        return result;
    }

    public static Set<String> getStrings (Shortener shortener, Set<Long> keys){
        Set<String> result = new HashSet<>();
        for (Long l : keys){
            result.add(shortener.getString(l));
        }
        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> setString = new HashSet<>((int)elementsNumber);
        for (int i = 0; i < elementsNumber; i++) {
            setString.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Set<Long> setLong = new HashSet<>();
        Date currentDate = new Date();
        for(String s : setString){
            setLong.add(shortener.getId(s));
        }
        Date endDate = new Date();
        long time = endDate.getTime() - currentDate.getTime();
        Helper.printMessage(String.valueOf(time));

        Set<String> resultStringSet = new HashSet<>();
        Date startDate = new Date();
        for (Long l : setLong){
            resultStringSet.add(shortener.getString(l));
        }
        Date finishDate = new Date();
        long result = finishDate.getTime() - startDate.getTime();
        Helper.printMessage(String.valueOf(result));

        if(setString.size() == resultStringSet.size()){
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}
