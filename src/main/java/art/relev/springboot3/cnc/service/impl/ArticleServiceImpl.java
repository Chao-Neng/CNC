package art.relev.springboot3.cnc.service.impl;

import art.relev.springboot3.cnc.dao.ArticleDao;
import art.relev.springboot3.cnc.dao.ResourceDao;
import art.relev.springboot3.cnc.exception.CNCException;
import art.relev.springboot3.cnc.model.Article;
import art.relev.springboot3.cnc.model.Resource;
import art.relev.springboot3.cnc.model.User;
import art.relev.springboot3.cnc.param.ArticleParam;
import art.relev.springboot3.cnc.response.ResultMessage;
import art.relev.springboot3.cnc.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleDao articleDao;
    private final ResourceDao resourceDao;

    @Override
    public Article create(ArticleParam.Create param) {
        Resource parentResource = resourceDao.getResourceById(param.getParentResourceId());
        if (parentResource == null) {
            throw new CNCException(ResultMessage.PARENT_RESOURCE_NOT_EXIST);
        }
        if (!parentResource.getResourceName().equals("chunk")) {
            throw new CNCException(ResultMessage.PARENT_RESOURCE_NOT_MATCH);
        }
        Resource resource = Resource.builder().resourceName("article").parentResource(parentResource).build();
        // TODO: temp resource ownerId
        try {
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User user && user.getResource() != null) {
                resource.getOwnerIdSet().add(user.getResource().getId());
            }
        } catch (Exception ignore) {
        }
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
