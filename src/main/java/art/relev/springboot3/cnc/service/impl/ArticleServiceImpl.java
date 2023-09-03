package art.relev.springboot3.cnc.service.impl;

import art.relev.springboot3.cnc.dao.ArticleDao;
import art.relev.springboot3.cnc.model.Article;
import art.relev.springboot3.cnc.model.Resource;
import art.relev.springboot3.cnc.param.ArticleParam;
import art.relev.springboot3.cnc.service.ArticleService;
import art.relev.springboot3.cnc.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleDao articleDao;
    private final ResourceService resourceService;

    @Override
    public Article create(ArticleParam.Create param) {
        Resource resource = resourceService.from(param);
        Article article = Article.builder().resource(resource).title(param.getTitle()).content(param.getContent()).build();
        articleDao.save(article);
        return article;
    }

    @Override
    public void delete(ArticleParam.Delete param) {
        Resource resource = Resource.builder().id(param.getResourceId()).build();
        Article article = Article.builder().resource(resource).build();
        articleDao.delete(article);
    }

    @Override
    public Article update(ArticleParam.Update param) {
        Resource resource = Resource.builder().id(param.getResourceId()).build();
        Article article = Article.builder().resource(resource).title(param.getTitle()).content(param.getContent()).build();
        articleDao.save(article);
        return article;
    }

    @Override
    public Article query(ArticleParam.Query param) {
        Resource resource = Resource.builder().id(param.getResourceId()).build();
        Article article = articleDao.getArticleByResource(resource);
        return article;
    }
}
