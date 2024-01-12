package freevoice.shared.constants;

public class URLS {
    /* USER AND AUTH
     ********************************/
    public static final String user = "api/user";

    /* FILES
     ********************************/
    public static final String image = "api/image";
    public static final String setUserProfileImage = "/setProfileImage/{userId}";
    public static final String getUserProfileImage = "/getProfileImage/{userEmail}";

    // VIDEOS
    public static final String video = "api/video";
    public static final String uploadVideos = "/upload";
    public static final String changeVideo = "/changeVideo/{name}";
    public static final String changeVideoName = "/changeName/{name}";
    public static final String deleteVideo = "/delete/{name}";
    public static final String playVideo = "/play/{name}";
    public static final String getAllVideoNames = "/getAllNames";
    public static final String getAllVideos = "/getAll";
    public static final String getVideoByName = "/getByName/{name}";
    public static final String setVideoDescription = "/setDescription/{name}";
    public static final String getVideoDescription = "/getDescription/{name}";

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