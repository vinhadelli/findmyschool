package nathanelucas.findmyschool;

/**
 * Created by lsrio on 29/11/2017.
 */

public class UserComment {

    private String userMail;
    private String commentText;

    public UserComment() {
    }

    public UserComment(String userMail, String commentText) {
        this.userMail = userMail;
        this.commentText = commentText;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
