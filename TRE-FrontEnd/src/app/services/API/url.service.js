(function () {
    'use strict';
        angular
            .module('tmsAPI')
            .factory('urlProvider', UrlProvider);

    /* @ngInject */
    function UrlProvider() {
        var urlProvider = {
            addComment: addComment,
            addExCoach: addExCoach, //TODO
            addFeedback: addFeedback,
            addParticipant: addParticipant,
            addTag: addTag,
            approveCourse: approveCourse,
            cancelCreate: cancelCreate,
            cancelEdit: cancelEdit,
            confirm: confirm,
            createCourse: createCourse,
            deleteFile: deleteFile,
            deleteLesson: deleteLesson,
            deleteParticipant: deleteParticipant,
            editCourse: editCourse,
            findTrainings: findTrainings,
            findUsers: findUsers,
            getAllTags: getAllTags,
            getApproveList: getApproveList,
            getAttachments: getAttachments,
            getAttendance: getAttendance,
            getComments: getComments,
            getCourseList: getCourseList,
            getCurrentCoursesForUser: getCurrentCoursesForUser,
            getEditedCourse: getEditedCourse,
            getExCoachList: getExCoachList, //TODO
            getFeedback: getFeedback,
            getFeedbacksOnUser: getFeedbacksOnUser,
            getNewsList: getNewsList,
            getParticipants: getParticipants,
            getPastCoursesForUser: getPastCoursesForUser,
            getProfileInfo: getProfileInfo,
            getShortInfo: getShortInfo,
            getTimetable: getTimetable,
            getWaitingCoursesForUser: getWaitingCoursesForUser, //TODO
            leave: leave,
            login: login,
            logout: logout,
            manageLesson: manageLesson,
            setAttendance: setAttendance,
            setRating: setRating, //TODO
            subscribe: subscribe,
            uploadFiles: uploadFiles
        };
        return urlProvider;
    }

    function addComment(courseId) {
        return '/api/training/' + courseId + '/add_comment';
    }

    //TODO
    function addExCoach() {
        //
    }

    function addFeedback() {
        return '/api/feedback_controller/add_feedback';
    }

    function addParticipant(courseId) {
        return '/api/training/' + courseId + '/addExListener';
    }

    function addTag() {
        return '/api/training/add_tag';
    }

    function approveCourse(actionId) {
        return '/api/training/confirm/' + actionId;
    }

    function cancelCreate(trainingId) {
        return '/api/training/cancel_create/' + trainingId;
    }

    function cancelEdit(trainingId) {
        return '/api/training/cancel_edit/' + trainingId;
    }

    function confirm(trainingId) {
        return '/api/training/confirm/' + trainingId;
    }

    function createCourse() {
        return '/api/training/create';
    }

    function deleteFile(fileId) {
        return '/api/file_controller/delete_file/' + fileId;
    }

    function deleteLesson(courseId, lessonId) {
        return '/api/training/' + courseId + '/lesson/' + lessonId;
    }

    function deleteParticipant(courseId, userId) {
        return '/api/training/' + courseId + '/leave/' + userId;
    }

    function editCourse(trainingId) {
        return '/api/training/edit/' + trainingId;
    }

    function findTrainings(searchQuery) {
        return '/api/search_controller/search_training';
    }

    function findUsers(searchQuery) {
        return '/api/search_controller/search_user'
    }

    function getAllTags() {
        return '/api/training/tag_list';
    }

    function getApproveList() {
        return '/api/approve_list';
    }

    function getAttachments(courseId) {
        return '/api/file_controller/get_files/' + courseId;
    }

    function getAttendance(lessonId) {
        return '/api/attendance_controller/all_attendance/' + lessonId;
    }

    function getComments(courseId) {
        return '/api/training/' + courseId + '/comment_list';
    }

    function getCourseList() {
        return '/api/training/training_list';
    }

    function getCurrentCoursesForUser(userId) {
        return '/api/user_controller/actualTraining/' + userId;
    }

    function getEditedCourse(trainingId) {
        return '/api/training/getApproveTraining/' + trainingId;
    }

    //TODO
    function getExCoachList() {
        //return '/api/';
    }

    function getFeedbacksOnUser(userId) {
        return '/api/feedback_controller/feedbacks_of_user/' + userId;
    }

    function getFeedback(fbId) {
        return '/api/feedback_controller/get_feedback/' + fbId;
    }

    function getNewsList() {
        return '/api/news';
    }

    function getParticipants(courseId) {
        return '/api/training/' + courseId + '/listener_list';
    }

    function getPastCoursesForUser(userId) {
        return '/api/user_controller/visitedTraining/' + userId;
    }

    function getProfileInfo(userId) {
        return '/api/user_controller/user_info/' + userId;
    }

    function getShortInfo(courseId) {
        return '/api/training/' + courseId;
    }

    function getTimetable(courseId) {
        return '/api/training/' + courseId + '/lesson_list';
    }

    //TODO
    function getWaitingCoursesForUser(userId) {
        //return;
    }

    function leave(courseId) {
        return '/api/training/' + courseId + '/leave';
    }

    function login() {
        return '/api/login';
    }

    function logout() {
        return '/api/logout';
    }

    function manageLesson(courseId) {
        return '/api/training/' + courseId + '/lesson';
    }

    function setAttendance() {
        return '/api/attendance_controller/update_attendance';
    }

    //TODO
    function setRating(courseId, rating) {
        return '/api/training/' + courseId + '/set_rating/' + rating;
    }

    function subscribe(courseId) {
        return '/api/training/' + courseId + '/addListener';
    }

    function uploadFiles() {
        return '/api/file_controller/add_files';
    }
})();