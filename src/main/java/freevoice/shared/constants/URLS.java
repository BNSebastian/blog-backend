package freevoice.shared.constants;

public class URLS {
    /*
     * FRONTEND
     ********************************/
    public static final String FRONTEND = "https://bucovala-sebastian.go.ro:4200";

    /*
     * USER AND AUTH
     ********************************/
    public static final String user = "api/user";
    public static final String confirmUser = "registration/confirm&token=";

    /*
     * PAYMENT
     ********************************/
    public static final String paypal = "api/paypal";
    public static final String initiatePayment = "/pay";
    public static final String paymentSuccess = "/success/{paymentId}/{payerId}";
    public static final String paypalPaySuccess = FRONTEND + "/#/donateSuccess";
    public static final String paypalPayFailure = FRONTEND + "/#/home";

    /*
     * FILES
     ********************************/
    public static final String image = "api/image";
    public static final String setUserProfileImage = "/setProfileImage/{userId}/{imageId}";
    public static final String getUserProfileImage = "/getProfileImage/{userEmail}";
    public static final String getUserProfileImages = "/getUserProfileImages";
    public static final String saveUserProfileImage = "/saveUserProfileImage";
    public static final String getProfileImageCount = "/getProfileImageCount";
    public static final String getProfileImageById = "/getProfileImageById/{id}";

    // VIDEOS
    public static final String video = "api/video";
    public static final String uploadVideos = "/upload";
    public static final String changeVideo = "/changeVideo/{name}";
    public static final String changeVideoName = "/changeName/{name}";
    public static final String deleteVideo = "/delete/{name}";
    public static final String playVideo = "/play/{name}";
    public static final String getAllVideoNames = "/getAllNames";
    public static final String getVideoNamesSize = "/getSize";
    public static final String getVideoNamesPage = "/getPage/{pageIndex}/{pageSize}";
    public static final String getAllVideos = "/getAll";
    public static final String getVideoByName = "/getByName/{name}";
    public static final String setVideoDescription = "/setDescription/{name}";
    public static final String getVideoDescription = "/getDescription/{name}";

    // VIDEO COMMENTS
    public static final String videoComment = "api/videoComment";
    public static final String createVideoComment = "/createComment";
    public static final String getVideoComment = "/getComment/{commentId}";
    public static final String getAllVideoComments = "/getAllComments/{videoName}";
    public static final String updateVideoComment = "/updateComment";
    public static final String deleteVideoComment = "/deleteComment/{commentId}";

    // FORUM POST
    public static final String forumPost = "api/forumPost";
    public static final String createForumPost = "/create";
    public static final String getForumPostById = "/getById/{id}";
    public static final String getAllForumPosts = "/getAll";
    public static final String getForumPostsSize = "/getSize";
    public static final String getForumPostPage = "/getPage/{pageIndex}/{pageSize}";
    public static final String deleteForumPost = "/delete/{id}";
    public static final String incrementPostViewCount = "/incrementViewCount/{postId}";
    public static final String getPostViewCount = "/getViewCount/{postId}";
    public static final String pinForumPost = "/pinPost/{postId}";

    // FORUM COMMENT
    public static final String forumComment = "api/forumComment";
    public static final String createForumComment = "/create";
    public static final String getAllForumComments = "/getAll/{id}";
    public static final String getForumCommentsSize = "/getSize/{id}";
    public static final String getForumCommentsPage = "/getPage/{id}/{pageIndex}/{pageSize}";
    public static final String deleteForumComment = "/delete";
    public static final String likeForumComment = "/like/{id}";
    public static final String dislikeForumComment = "/dislike/{id}";

    // ARTICLES
    public static final String articles = "api/article";

}