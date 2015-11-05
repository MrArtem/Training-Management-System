(function () {
    'use strict';
        angular
            .module('tmsAPI')
            .factory('urlProvider', UrlProvider);

    /* @ngInject */
    function UrlProvider() {
        var urlProvider = {
            addComment: addComment,
            addFeedback: addFeedback,
            addParticipant: addParticipant,
            approveCourse: approveCourse,
            cancelCreate: cancelCreate,
            cancelEdit: cancelEdit,
            confirm: confirm,
            createCourse: createCourse,
            deleteLesson: deleteLesson,
            deleteParticipant: deleteParticipant,
            editCourse: editCourse,
            findTrainings: findTrainings,
            findUsers: findUsers,
            getApproveList: getApproveList,
            getComments: getComments,
            getCourseList: getCourseList,
            getCurrentCoursesForUser: getCurrentCoursesForUser,
            getEditedCourse: getEditedCourse,
            getFeedbacksOnUser: getFeedbacksOnUser,
            getNewsList: getNewsList,
            getParticipants: getParticipants,
            getPastCoursesForUser: getPastCoursesForUser,
            getProfileInfo: getProfileInfo,
            getShortInfo: getShortInfo,
            getTimetable: getTimetable,
            getWaitingCoursesForUser: getWaitingCoursesForUser,
            leave: leave,
            login: login,
            logout: logout,
            manageLesson: manageLesson,
            subscribe: subscribe,
            uploadFiles: uploadFiles
        };
        return urlProvider;
    }

    function addComment(courseId) {
        return '/training/' + courseId + '/add_comment';
    }

    function addFeedback() {
        return '/feedback_controller/add_feedback';
    }

    function addParticipant(courseId) {
        return '/training/' + courseId + '/add_listener'; //!!! CHECK
    }

    function approveCourse(actionId) {
        return '/training/confirm/' + actionId;
    }

    function cancelCreate(trainingId) {
        return '/training/cancel_create/' + trainingId;
    }

    function cancelEdit(trainingId) {
        return '/training/cancel_edit/' + trainingId;
    }

    function confirm(trainingId) {
        return '/training/confirm/' + trainingId;
    }

    function createCourse() {
        return '/training/create';
    }

    function deleteLesson(courseId, lessonId) {
        return '/training/' + courseId + '/lesson/' + lessonId;
    }

    function deleteParticipant(courseId, userId) {
        return '/training/' + courseId + '/leave/' + userId; //CHECK
    }

    function editCourse(trainingId) {
        return '/training/edit/' + trainingId;
    }

    function findTrainings(searchQuery) {
        return '/search_controller/search_training';
    }

    function findUsers(searchQuery) {
        return '/search_controller/search_user'
    }

    function getApproveList() {
        return '/approve_list';
    }

    function getComments(courseId) {
        return '/training/' + courseId + '/comment_list';
    }

    function getCourseList() {
        return '/training/training_list';
    }

    function getCurrentCoursesForUser(userId) {
        return '/user_controller/actualTraining/' + userId;
    }

    function getEditedCourse(trainingId) {
        return '/training/getApproveTraining/' + trainingId;
    }

    function getFeedbacksOnUser(userId) {
        return '/feedback_controller/feedbacks_of_user/' + userId;
    }

    function getNewsList() {
        return '/news';
    }

    function getParticipants(courseId) {
        return '/training/' + courseId + '/listener_list';
    }

    function getPastCoursesForUser(userId) {
        return '/user_controller/visitedTraining/' + userId;
    }

    function getProfileInfo(userId) {
        return '/user_controller/user_info/' + userId;
    }

    function getShortInfo(courseId) {
        return '/training/' + courseId;
    }

    function getTimetable(courseId) {
        return '/training/' + courseId + '/lesson_list';
    }

    function getWaitingCoursesForUser(userId) {
        //return;
    }

    function leave(courseId) {
        return '/training/' + courseId + '/leave';
    }

    function login() {
        return '/login';
    }

    function logout() {
        return '/logout';
    }

    function manageLesson(courseId) {
        return '/training/' + courseId + '/lesson';
    }

    function subscribe(courseId) {
        return '/training/' + courseId + '/addListener';
    }

    function uploadFiles() {
        return '/file_controller/';
    }
})();