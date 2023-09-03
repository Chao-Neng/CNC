package art.relev.springboot3.cnc.dao;

import art.relev.springboot3.cnc.exclude.CNCDao;
import art.relev.springboot3.cnc.model.Article;
import art.relev.springboot3.cnc.model.Resource;

public interface ArticleDao extends CNCDao<Article> {
    Article getArticleByResource(Resource resource);
}
