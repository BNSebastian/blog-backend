package freevoice.shared;

public class URLS {
    // LIVE CHAT
    public static final String chat = "api/chat";
    public static final String createChatComment = "/createComment";
    public static final String getChatCommentById = "/getComment/{commentId}";
    public static final String getAllChatComments = "/getAllComments";

    // FORUM POST
    public static final String forumPost = "api/forumPost";
    public static final String createForumPost = "/createPost";
    public static final String getAllForumPosts = "/getAllPosts";
    public static final String deleteForumPost = "/deletePost/{postId}";

    // FORUM COMMENT
    public static final String forumComment = "api/forumComment";
    public static final String createForumComment = "/createComment";
    public static final String getAllForumComments = "/getAllComments/{id}";
    public static final String deleteForumComment = "/deleteComment";
}