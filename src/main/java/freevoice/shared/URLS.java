package freevoice.shared;

public class URLS {
    // LIVE CHAT
    public static final String chat = "api/chat";
    public static final String createChatComment = "/createComment";
    public static final String getChatCommentById = "/getComment/{commentId}";
    public static final String getAllChatComments = "/getAllComments";

    // FORUM POST
    public static final String forumPost = "api/forumPost";
    public static final String createForumPost = "/create";
    public static final String getForumPostById = "/getById/{id}";
    public static final String getAllForumPosts = "/getAll";
    public static final String deleteForumPost = "/delete/{id}";

    // FORUM COMMENT
    public static final String forumComment = "api/forumComment";
    public static final String createForumComment = "/create";
    public static final String getAllForumComments = "/getAll/{id}";
    public static final String deleteForumComment = "/delete";
    public static final String likeForumComment = "/like/{id}";
    public static final String dislikeForumComment = "/dislike/{id}";
}