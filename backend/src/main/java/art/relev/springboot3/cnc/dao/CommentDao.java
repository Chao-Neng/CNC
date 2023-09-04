package art.relev.springboot3.cnc.dao;

import art.relev.springboot3.cnc.exclude.CNCDao;
import art.relev.springboot3.cnc.model.Comment;
import art.relev.springboot3.cnc.model.Resource;

public interface CommentDao extends CNCDao<Comment> {
    Comment getCommentByResource(Resource resource);
}
