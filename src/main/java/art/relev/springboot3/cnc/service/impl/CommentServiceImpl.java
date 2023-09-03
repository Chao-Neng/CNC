package art.relev.springboot3.cnc.service.impl;

import art.relev.springboot3.cnc.dao.CommentDao;
import art.relev.springboot3.cnc.dao.ResourceDao;
import art.relev.springboot3.cnc.exception.CNCException;
import art.relev.springboot3.cnc.model.Comment;
import art.relev.springboot3.cnc.model.Resource;
import art.relev.springboot3.cnc.model.User;
import art.relev.springboot3.cnc.param.CommentParam;
import art.relev.springboot3.cnc.response.ResultMessage;
import art.relev.springboot3.cnc.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;
    private final ResourceDao resourceDao;

    @Override
    public Comment create(CommentParam.Create param) {
        Resource parentResource = resourceDao.getResourceById(param.getParentResourceId());
        if (parentResource == null) {
            throw new CNCException(ResultMessage.PARENT_RESOURCE_NOT_EXIST);
        }
        if (!parentResource.getResourceName().equals("article")) {
            throw new CNCException(ResultMessage.PARENT_RESOURCE_NOT_MATCH);
        }
        Resource resource = Resource.builder().resourceName("comment").parentResource(parentResource).build();
        // TODO: temp resource ownerId
        try {
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User user && user.getResource() != null) {
                resource.getOwnerIdSet().add(user.getResource().getId());
            }
        } catch (Exception ignore) {
        }
        Comment comment = Comment.builder().resource(resource).content(param.getContent()).build();
        commentDao.save(comment);
        return comment;
    }

    @Override
    public void delete(CommentParam.Delete param) {
        Resource resource = Resource.builder().id(param.getResourceId()).build();
        Comment comment = Comment.builder().resource(resource).build();
        commentDao.delete(comment);
    }

    @Override
    public Comment update(CommentParam.Update param) {
        Resource resource = Resource.builder().id(param.getResourceId()).build();
        Comment comment = Comment.builder().resource(resource).content(param.getContent()).build();
        commentDao.save(comment);
        return comment;
    }

    @Override
    public Comment query(CommentParam.Query param) {
        Resource resource = Resource.builder().id(param.getResourceId()).build();
        Comment comment = commentDao.getCommentByResource(resource);
        return comment;
    }
}
