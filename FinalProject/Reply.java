package com.example.finalproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Reply implements Serializable {
    private final String userName;
    private final String replyId;
    private final String content;
    private int counterReply=0;
    private int likes;
    private final List<String> userIdReact;
    private final List<Reply> nestedReplies;

    public Reply(String userName,String replyId, String content) {
        this.userName = userName;
        this.replyId = replyId;
        this.content = content;
        this.likes = 0;
        this.nestedReplies = new ArrayList<>();
        this.userIdReact = new ArrayList<>();
    }
    public Reply(String userName,String replyId, String content,int noOfLike) {
        this.userName = userName;
        this.replyId = replyId;
        this.content = content;
        this.likes = noOfLike;
        this.nestedReplies = new ArrayList<>();
        this.userIdReact = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }


    public String getReplyId() {
        return replyId;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    public List<Reply> getNestedReplies() {
        return nestedReplies;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<String> getUserIdReact() {
        return userIdReact;
    }

    public void addNestedReply(String Name,String contentNReply,int noOfLike) {
        String nestedReplyId = replyId +'.'+ counterReply;
        Reply nReply = new Reply(Name,nestedReplyId,contentNReply,noOfLike);
        nestedReplies.add(nReply);
        counterReply++;
    }

    public void add_RemoveReaction(String userId) {
        if (!userIdReact.contains(userId)) {
            likes++;
            setLikes(likes);
            userIdReact.add(userId);
            System.out.println(likes);
        } else {
            likes--;
            setLikes(likes);
            userIdReact.remove(userId);
            System.out.println(likes);
        }
    }

    @Override
    public String toString() {
        return
                 replyId +
                "|"+userName+
                "|" + content +
                "|" + likes +
                "|" + nestedReplies ;
    }
}
