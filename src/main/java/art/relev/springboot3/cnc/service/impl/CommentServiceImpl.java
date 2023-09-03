package art.relev.springboot3.cnc.service.impl;

import art.relev.springboot3.cnc.dao.CommentDao;
import art.relev.springboot3.cnc.model.Comment;
import art.relev.springboot3.cnc.model.Resource;
import art.relev.springboot3.cnc.param.CommentParam;
import art.relev.springboot3.cnc.service.CommentService;
import art.relev.springboot3.cnc.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;
    private final ResourceService resourceService;

    @Override
    public Comment create(CommentParam.Create param) {
        Resource resource = resourceService.from(param);
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
