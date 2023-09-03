package art.relev.springboot3.cnc.service;

import art.relev.springboot3.cnc.model.Comment;
import art.relev.springboot3.cnc.param.CommentParam;

public interface CommentService {
    Comment create(CommentParam.Create param);

    void delete(CommentParam.Delete param);

    Comment update(CommentParam.Update param);

    Comment query(CommentParam.Query param);
}
