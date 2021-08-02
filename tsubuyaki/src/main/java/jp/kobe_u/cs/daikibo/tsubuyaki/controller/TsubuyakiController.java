package jp.kobe_u.cs.daikibo.tsubuyaki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.kobe_u.cs.daikibo.tsubuyaki.entity.Tsubuyaki;
import jp.kobe_u.cs.daikibo.tsubuyaki.service.TsubuyakiService;

@Controller
public class TsubuyakiController {
    @Autowired
    TsubuyakiService ts;

    @GetMapping("/")
    public String showIndex(){
        return "index";
    }

    //メイン画面を表示
    @GetMapping("/read")
    public String showTsubuyakiList(Model model) {
        List<Tsubuyaki> list = ts.getAllTsubuyaki(); //全つぶやきを取得
        model.addAttribute("tsubuyakiList", list);   //モデル属性にリストをセット
        model.addAttribute("tsubuyakiForm", new TsubuyakiForm());  //空フォームをセット
        return "tsubuyaki_list"; //リスト画面を返す
    }

    //つぶやきを投稿
    @PostMapping("/read")
    public String postTsubuyaki(@ModelAttribute("tsubuyakiForm") TsubuyakiForm form, Model model) {
        //フォームからエンティティに移し替え
        Tsubuyaki t = new Tsubuyaki();
        t.setName(form.getName());
        t.setComment(form.getComment());
        //サービスに投稿処理を依頼
        ts.postTsubuyaki(t);
        return "redirect:/read"; //メイン画面に転送
    }

    @PostMapping("/search")
    public String searchTsubuyaki(@RequestParam String word, Model model) {
        List<Tsubuyaki> list =ts.searchTsubuyaki(word);
        System.out.println(list);
        model.addAttribute("tsubuyakiList", list);
        model.addAttribute("tsubuyakiForm", new TsubuyakiForm());
        return "tsubuyaki_list"; //メイン画面に転送
    }
}
