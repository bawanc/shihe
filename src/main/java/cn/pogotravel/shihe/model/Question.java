package cn.pogotravel.shihe.model;

public class Question {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.id
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.title
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.description
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.gmtcreate
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    private Long gmtcreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.gmtmodified
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    private Long gmtmodified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.creator
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    private Long creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.commentcount
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    private Integer commentcount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.viewcount
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    private Integer viewcount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.likecount
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    private Integer likecount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.tag
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    private String tag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.avatarurl
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    private String avatarurl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.id
     *
     * @return the value of question.id
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.id
     *
     * @param id the value for question.id
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.title
     *
     * @return the value of question.title
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.title
     *
     * @param title the value for question.title
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.description
     *
     * @return the value of question.description
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.description
     *
     * @param description the value for question.description
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.gmtcreate
     *
     * @return the value of question.gmtcreate
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public Long getGmtcreate() {
        return gmtcreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.gmtcreate
     *
     * @param gmtcreate the value for question.gmtcreate
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public void setGmtcreate(Long gmtcreate) {
        this.gmtcreate = gmtcreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.gmtmodified
     *
     * @return the value of question.gmtmodified
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public Long getGmtmodified() {
        return gmtmodified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.gmtmodified
     *
     * @param gmtmodified the value for question.gmtmodified
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public void setGmtmodified(Long gmtmodified) {
        this.gmtmodified = gmtmodified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.creator
     *
     * @return the value of question.creator
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.creator
     *
     * @param creator the value for question.creator
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.commentcount
     *
     * @return the value of question.commentcount
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public Integer getCommentcount() {
        return commentcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.commentcount
     *
     * @param commentcount the value for question.commentcount
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public void setCommentcount(Integer commentcount) {
        this.commentcount = commentcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.viewcount
     *
     * @return the value of question.viewcount
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public Integer getViewcount() {
        return viewcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.viewcount
     *
     * @param viewcount the value for question.viewcount
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public void setViewcount(Integer viewcount) {
        this.viewcount = viewcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.likecount
     *
     * @return the value of question.likecount
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public Integer getLikecount() {
        return likecount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.likecount
     *
     * @param likecount the value for question.likecount
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.tag
     *
     * @return the value of question.tag
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.tag
     *
     * @param tag the value for question.tag
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.avatarurl
     *
     * @return the value of question.avatarurl
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public String getAvatarurl() {
        return avatarurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.avatarurl
     *
     * @param avatarurl the value for question.avatarurl
     *
     * @mbg.generated Wed Mar 18 23:25:34 CST 2020
     */
    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl == null ? null : avatarurl.trim();
    }
}