package com.exadel.training.dao.domain;

import org.apache.lucene.analysis.charfilter.MappingCharFilterFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.apache.lucene.analysis.synonym.SynonymFilterFactory;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Parameter;
import java.util.List;

/**
 * Created by ayudovin on 05.10.2015.
 */
@Entity
@Table
@Indexed
@AnalyzerDef(name = "customAnalyzer",
        tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "English")
                }),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "Russian")
                }),
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "English")
                }),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "Russian")
                }),
                @TokenFilterDef(factory = WordDelimiterFilterFactory.class, params = {
                        @Parameter(name = "splitOnCaseChange", value = "1")
                }),
                @TokenFilterDef(factory = NGramFilterFactory.class, params = {
                        @Parameter(name = "minGramSize", value = "3"),
                        @Parameter(name = "maxGramSize", value = "10")
                }),
                @TokenFilterDef(factory = SynonymFilterFactory.class, params = {
                @Parameter(name = "synonyms", value = "searchSynonyms/synonyms.txt"),
                @Parameter(name = "expand", value = "false")
                })
        })
public class User {

    public   enum Role{
        ADMIN, USER, EX_COACH, EX_USER
    }

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;


    @NotNull
    private String login;

    @NotNull
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Analyzer(definition = "customAnalyzer")
    private String firstName;

    @NotNull
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Analyzer(definition = "customAnalyzer")
    private String lastName;

    @NotNull
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Analyzer(definition = "customAnalyzer")
    private String email;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Analyzer(definition = "customAnalyzer")
    private String phone;

    @OneToOne
    private UserPassword userPassword;

    @OneToMany(mappedBy = "user")
    private List<Listener> trainingListListener;

    @OneToMany(mappedBy = "coach")
    private List<Training> trainingListCoach;

    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbackList;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Attendance> attendances;

    public  User(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserPassword getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(UserPassword userPassword) {
        this.userPassword = userPassword;
    }

    public List<Training> getTrainingsCoach() {
        return trainingListCoach;
    }

    public void setTrainingsCoach(List<Training> trainingListCoach) {
        this.trainingListCoach = trainingListCoach;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public List<Comment> getCommentList() {
        return comments;
    }

    public void setCommentList(List<Comment> commentList) {
        this.comments = commentList;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public List<Listener> getTrainingListListener() {
        return trainingListListener;
    }

    public void setTrainingListListener(List<Listener> trainingListListener) {
        this.trainingListListener = trainingListListener;
    }
}
