package com.example.lab52;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.web.reactive.function.BodyInserters;


@Route(value = "index2")
public class MyView2 {
    private TextField addWord,addSentence,goodSentence,badSentence;
    private Button btnGoodWord,btnBadWord,btnSentence,btnShowSentence;
    private ComboBox goodWord,badWord;
    public MyView2(){
        addWord = new TextField("Add Word");
        addSentence = new TextField("Add Sentence");
        goodSentence = new TextField("Good Sentences");
        badSentence = new TextField("Bad Sentence");
        btnGoodWord = new Button("Add Good Word");
        btnBadWord = new Button("Add Bad Word");
        btnSentence = new Button("Add Sentence");
        btnShowSentence = new Button("Show Sentence");
        goodWord = new ComboBox();
        badWord = new ComboBox();

        HorizontalLayout h = new HorizontalLayout();
        VerticalLayout v1 = new VerticalLayout();
        VerticalLayout v2 = new VerticalLayout();

        v1.add(addWord,btnGoodWord,btnBadWord,goodWord,badWord);
        v2.add(addSentence,btnSentence,goodSentence,badSentence,btnShowSentence);
        h.add(v1,v2);
    }
}
