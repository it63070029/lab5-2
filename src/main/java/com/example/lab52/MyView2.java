package com.example.lab52;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;


import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import com.vaadin.flow.component.notification.Notification;

import java.util.ArrayList;

@Route(value = "/index2")
public class MyView2 extends HorizontalLayout{
    private TextField addWord,addSentence;
    private TextArea goodSentence,badSentence;
    private Button btnGoodWord,btnBadWord,btnSentence,btnShowSentence;
    private ComboBox goodWord,badWord;
    private VerticalLayout v1,v2;
    private Label lb1,lb2;
    private Word words = new Word();



    public MyView2() {

        addWord = new TextField("Add Word");
        addWord.setSizeFull();
        addSentence = new TextField("Add Sentence");
        addSentence.setSizeFull();
        goodSentence = new TextArea("Good Sentences");
        goodSentence.setSizeFull();
        badSentence = new TextArea("Bad Sentence");
        badSentence.setSizeFull();
        btnGoodWord = new Button("Add Good Word");
        btnGoodWord.setSizeFull();
        btnBadWord = new Button("Add Bad Word");
        btnBadWord.setSizeFull();
        btnSentence = new Button("Add Sentence");
        btnSentence.setSizeFull();
        btnShowSentence = new Button("Show Sentence");
        btnShowSentence.setSizeFull();
        goodWord = new ComboBox();
        goodWord.setSizeFull();
        goodWord.setItems(words.goodWords);
        badWord = new ComboBox();
        badWord.setSizeFull();
        badWord.setItems(words.badWords);
        lb1 = new Label("Good Words");
        lb2 = new Label("Bad Words");


        v1 = new VerticalLayout();
        v1.setSizeFull();
        v2 = new VerticalLayout();

        v1.add(addWord,btnGoodWord,btnBadWord,lb1,goodWord,lb2,badWord);
        v2.add(addSentence,btnSentence,goodSentence,badSentence,btnShowSentence);

        this.add(v1,v2);

        btnGoodWord.addClickListener(event->{
            MultiValueMap<String,String> formData = new LinkedMultiValueMap<>();
            formData.add("word",addWord.getValue());

            ArrayList out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/addGood")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(ArrayList.class)
                    .block();
            Notification txt = Notification.show("Insert "+addWord.getValue()+" to Good Word List Complete");
            goodWord.setItems(out);


        });
        btnBadWord.addClickListener(event->{
            MultiValueMap<String,String> formData = new LinkedMultiValueMap<>();
            formData.add("word",addWord.getValue());

            ArrayList out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/addBad")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(ArrayList.class)
                    .block();
            Notification txt = Notification.show("Insert "+addWord.getValue()+" to Bad Word List Complete");
            badWord.setItems(out);
        });
        btnSentence.addClickListener(event ->{
            MultiValueMap<String,String> formData = new LinkedMultiValueMap<>();
            formData.add("sentence",addSentence.getValue());

            String out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/proof")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            Notification txt = Notification.show(out);
        });
        btnShowSentence.addClickListener(event ->{
            Sentence out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/getSentence")
                    .retrieve()
                    .bodyToMono(Sentence.class)
                    .block();
            goodSentence.setValue(String.valueOf(out.goodSentences));
            badSentence.setValue(String.valueOf(out.badSentences));
        });
    }
}
