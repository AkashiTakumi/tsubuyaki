package jp.kobe_u.cs.daikibo.tsubuyaki.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.kobe_u.cs.daikibo.tsubuyaki.entity.Tsubuyaki;
import jp.kobe_u.cs.daikibo.tsubuyaki.repository.TsubuyakiRepository;

@Service
public class TsubuyakiService {
    @Autowired
    TsubuyakiRepository repo;

    /**
     * つぶやきの登録
     * @param t
     * @return
     */
    public Tsubuyaki postTsubuyaki(Tsubuyaki t) {
        String name = t.getName();
        if (name == null || name.length() == 0) {
            t.setName("名無しの権兵衛さん");
        }
        t.setCreatedAt(new Date());
        return repo.save(t);
    }

    /**
     * つぶやきの一覧表示
     * @return
     */
    public List<Tsubuyaki> getAllTsubuyaki() {
        Iterable<Tsubuyaki> found = repo.findAll();
        ArrayList<Tsubuyaki> list = new ArrayList<>();
        found.forEach(list::add);
        return list;
    }

    /**
     * つぶやきの検索
     * @param word
     * @return
     */
    public List<Tsubuyaki> searchTsubuyaki(String word) {
        Iterable<Tsubuyaki> found = repo.findByCommentContaining(word);
        ArrayList<Tsubuyaki> list = new ArrayList<>();
        found.forEach(list::add);
        return list;
    }
}
