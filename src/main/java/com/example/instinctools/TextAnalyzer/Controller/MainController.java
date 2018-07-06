package com.example.instinctools.TextAnalyzer.Controller;

import com.example.instinctools.TextAnalyzer.BracketsValidator;
import com.example.instinctools.TextAnalyzer.Entry;
import com.example.instinctools.TextAnalyzer.RepeatedElements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Controller
public class MainController {
    private String data;
    private Boolean isLoaded;

    @RequestMapping
    public String getMain(Model model) {
        model.addAttribute("myText", "");
        model.addAttribute("isLoaded", isLoaded);
        return "analyze";
    }


    @PostMapping("/load")
    public String load(@RequestParam("file") MultipartFile file, Model model) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(file.getInputStream());
            byte[] bytes = new byte[(int) file.getSize()];
            bufferedInputStream.read(bytes, 0, (int) file.getSize());
            data = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        isLoaded = true;
        model.addAttribute("isLoaded", isLoaded);
        return "redirect:/";
    }

    @PostMapping("/checkbrackets")
    public String check1(Model model) {
        BracketsValidator validator = new BracketsValidator();
        boolean correct = validator.validate(data);
        model.addAttribute("myText", "Скобки расставлены " + (correct ? "" : "не") + "правильно");
        model.addAttribute("isLoaded", isLoaded);
        return "analyze";
    }

    @PostMapping("/toplist")
    public String check2(Model model) {

        RepeatedElements rep = new RepeatedElements();
        List<Entry> entries = rep.findSameEl(data);
        entries.sort(Comparator.comparingInt(Entry::getCount).reversed());
        int toIndex = entries.size() > 10 ? 10 : entries.size();

        StringBuilder result = new StringBuilder();
        for (Entry entry : entries.subList(0, toIndex)) {
            result.append(entry.toString());
        }
        model.addAttribute("myText", result);
        model.addAttribute("isLoaded", isLoaded);
        return "analyze";
    }
}
