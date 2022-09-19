package com.example.lab52;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class SentenceConsumer {
    protected Sentence sentences = new Sentence();
    @RabbitListener(queues = "BadWordQueue")
    public void addBadSentence(String s){
        sentences.badSentences.add(s);
        String text="";
        for (int i=0; i<sentences.badSentences.size(); i++){
            if (i>=1){
                text = text+" , ";
            }
            text = text+""+sentences.badSentences.get(i);

        }

        System.out.println("In addBadSentence Method : ["+text+"]");
    }
    @RabbitListener(queues = "GoodWordQueue")
    public void addGoodSentence(String s){
        sentences.goodSentences.add(s);
        String text="";
        for (int j=0; j<sentences.goodSentences.size(); j++){
            if (j>=1){
                text = text+",";
            }
            text = text+""+sentences.goodSentences.get(j);

        }
        System.out.println("In addGoodSentence Method : ["+text+"]");

    }
    @RabbitListener(queues = "GetQueue")
    public Sentence getSentencs(){
       return sentences;
    }

}
