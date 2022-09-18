package com.example.lab52;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
@RestController
public class Sentence implements Serializable {
    public ArrayList<String> badSentences;
    public ArrayList<String> goodSentences;
    public Sentence(){
        badSentences = new ArrayList<>();
        goodSentences = new ArrayList<>();
    }
}
