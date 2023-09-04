package art.relev.springboot3.cnc.service;

import art.relev.springboot3.cnc.model.Article;
import art.relev.springboot3.cnc.param.ArticleParam;

public interface ArticleService {
    Article create(ArticleParam.Create param);

    void delete(ArticleParam.Delete param);

    Article update(ArticleParam.Update param);

    Article query(ArticleParam.Query param);
}
