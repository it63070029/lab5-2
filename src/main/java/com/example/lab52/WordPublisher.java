package com.example.lab52;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import java.util.ArrayList;
@RestController
public class WordPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    protected Word words = new Word();

    @RequestMapping(value = "/addBad/{word}", method = RequestMethod.GET)
    public ArrayList<String> addBadWord(@PathVariable("word") String s) {
        words.badWords.add(s);
        return words.badWords;
    }

    @RequestMapping(value = "/delBad/{word}", method = RequestMethod.GET)
    public ArrayList<String> deleteBadWord(@PathVariable("word") String s) {
        words.badWords.remove(s);
        return words.badWords;
    }

    @RequestMapping(value = "/addGood/{word}", method = RequestMethod.GET)
    public ArrayList<String> addGoodWord(@PathVariable("word") String s) {
        words.goodWords.add(s);
        return words.goodWords;
    }

    @RequestMapping(value = "/delGood/{word}", method = RequestMethod.GET)
    public ArrayList<String> deleteGoodWord(@PathVariable("word") String s) {
        words.goodWords.remove(s);
        return words.goodWords;
    }
    @RequestMapping(value="/proof/{sentence}",method = RequestMethod.GET)
    public String proofSentence(@PathVariable("sentence") String s){
        boolean checkGood =false;
        boolean checkBad =false;
        for (int i = 0; i <words.goodWords.size() ; i++) {
            if(s.contains(words.goodWords.get(i))){
                checkGood = true;
                break;
            }
        }
        for (int j = 0; j <words.badWords.size() ; j++) {
            if(s.contains(words.badWords.get(j))){
                checkBad = true;
                break;
            }
        }
        if(checkGood&&checkBad){
            rabbitTemplate.convertAndSend("MyFanoutExchange","",s);
            return "Found Bad & Good Word";
        } else if (checkGood) {
            rabbitTemplate.convertAndSend("MyDirectExchange","good",s);
            return "Found Good Word";
        } else if (checkBad) {
            rabbitTemplate.convertAndSend("MyDirectExchange","bad",s);
            return "Found Bad Word";

        }

        return "Not Found";
    }
    @RequestMapping(value = "/getSentence", method = RequestMethod.GET)
    public Sentence getSentence() {
        return getSentence();
    }

}
