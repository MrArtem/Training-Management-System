(function () {
    'use strict';
        angular
            .module('tmsAPI')
            .factory('urlProvider', UrlProvider);

    /* @ngInject */
    function UrlProvider() {
        
        
        var urlProvider = {
            addComment: addComment,
            addExCoach: addExCoach,
            addFeedback: addFeedback,
            addParticipant: addParticipant,
            addTag: addTag,
            approveCourse: approveCourse,
            approveLesson: approveLesson,
            cancelChange: cancelChange,
            cancelCreate: cancelCreate,
            cancelEdit: cancelEdit,
            cancelLesson: cancelLesson,
            changePassword: changePassword,
            confirm: confirm,
            createCourse: createCourse,
            deleteCourse: deleteCourse,
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
            getExCoachList: getExCoachList,
            getFeedback: getFeedback,
            getFeedbacksOnUser: getFeedbacksOnUser,
            getLessonToApprove: getLessonToApprove,
            getNewsList: getNewsList,
            getParticipants: getParticipants,
            getPastCoursesForUser: getPastCoursesForUser,
            getProfileInfo: getProfileInfo,
            getShortInfo: getShortInfo,
            getStatistics: getStatistics,
            getTimetable: getTimetable,
            getWaitingCoursesForUser: getWaitingCoursesForUser,
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
    
    var isApi = true
    function apiPrefix() {
        return isApi ? "/api" : "";
    }

    function addComment(courseId) {
        return apiPrefix() + '/training/' + courseId + '/add_comment';
    }


    function addExCoach() {
        return apiPrefix() + '/user_controller/add_ex_coach';
    }

    function addFeedback() {
        return apiPrefix() + '/feedback_controller/add_feedback';
    }

    function addParticipant(courseId) {
        return apiPrefix() + '/training/' + courseId + '/addExListener';
    }

    function addTag() {
        return apiPrefix() + '/training/add_tag';
    }

    function approveCourse(actionId) {
        return apiPrefix() + '/training/confirm/' + actionId;
    }

    function approveLesson(actionId) {
        return apiPrefix() + '/training/' + actionId + '/confirm/lesson';
    }

    function cancelCreate(actionId) {
        return apiPrefix() + '/training/cancel_create/' + actionId;
    }

    function cancelChange(actionId) {
        return apiPrefix() + '/training/cancel_change/' + actionId;
    }

    function cancelEdit(trainingId) {
        return apiPrefix() + '/training/cancel_edit/' + trainingId;
    }

    function cancelLesson(actionId) {
        return apiPrefix() + '/training/' + actionId + '/canceled_lesson';
    }

    function changePassword() {
        return apiPrefix() + '/user_controller/set_password';
    }

    function confirm(trainingId) {
        return apiPrefix() + '/training/confirm/' + trainingId;
    }

    function createCourse() {
        return apiPrefix() + '/training/create';
    }

    function deleteCourse(courseId) {
        return apiPrefix() + '/training/' + courseId + '/remove';
    }

    function deleteFile(fileId) {
        return apiPrefix() + '/file_controller/delete_file/' + fileId;
    }

    function deleteLesson(courseId, lessonId) {
        return apiPrefix() + '/training/' + courseId + '/lesson/' + lessonId;
    }

    function deleteParticipant(courseId, userId) {
        return apiPrefix() + '/training/' + courseId + '/leave/' + userId;
    }

    function editCourse(courseId) {
        return apiPrefix() + '/training/edit/' + courseId;
    }

    function findTrainings(searchQuery) {
        return apiPrefix() + '/search_controller/search_training';
    }

    function findUsers(searchQuery) {
        return apiPrefix() + '/search_controller/search_user'
    }

    function getAllTags() {
        return apiPrefix() + '/training/tag_list';
    }

    function getApproveList() {
        return apiPrefix() + '/approve_list';
    }

    function getAttachments(courseId) {
        return apiPrefix() + '/file_controller/get_files/' + courseId;
    }

    function getAttendance(lessonId) {
        return apiPrefix() + '/attendance_controller/all_attendance/' + lessonId;
    }

    function getComments(courseId) {
        return apiPrefix() + '/training/' + courseId + '/comment_list';
    }

    function getCourseList() {
        return apiPrefix() + '/training/training_list';
    }

    function getCurrentCoursesForUser(userId) {
        return apiPrefix() + '/user_controller/actualTraining/' + userId;
    }

    function getEditedCourse(trainingId) {
        return apiPrefix() + '/training/getApproveTraining/' + trainingId;
    }

    function getExCoachList() {
        return apiPrefix() + '/user_controller/get_all_ex_coach';
    }

    function getFeedbacksOnUser(userId) {
        return apiPrefix() + '/feedback_controller/feedbacks_of_user/' + userId;
    }

    function getFeedback(fbId) {
        return apiPrefix() + '/feedback_controller/get_feedback/' + fbId;
    }

    function getLessonToApprove(actionId) {
        return apiPrefix() + '/training/' + actionId + '/approve_lesson';
    }

    function getNewsList() {
        return apiPrefix() + '/news';
    }

    function getParticipants(courseId) {
        return apiPrefix() + '/training/' + courseId + '/listener_list';
    }

    function getPastCoursesForUser(userId) {
        return apiPrefix() + '/user_controller/visitedTraining/' + userId;
    }

    function getProfileInfo(userId) {
        return apiPrefix() + '/user_controller/user_info/' + userId;
    }

    function getShortInfo(courseId) {
        return apiPrefix() + '/training/' + courseId;
    }

    function getStatistics() {
        return apiPrefix() + '/statistics';
    }

    function getTimetable(courseId) {
        return apiPrefix() + '/training/' + courseId + '/lesson_list';
    }

    function getWaitingCoursesForUser(userId) {
        return apiPrefix() + '/user_controller/waitingTraining/' + userId;
    }

    function leave(courseId) {
        return apiPrefix() + '/training/' + courseId + '/leave';
    }

    function login() {
        return apiPrefix() + '/login';
    }

    function logout() {
        return apiPrefix() + '/logout';
    }

    function manageLesson(courseId) {
        return apiPrefix() + '/training/' + courseId + '/lesson';
    }

    function setAttendance() {
        return apiPrefix() + '/attendance_controller/update_attendance';
    }

    //TODO
    function setRating(courseId, rating) {
        return apiPrefix() + '/training/' + courseId + '/set_rating/' + rating;
    }

    function subscribe(courseId) {
        return apiPrefix() + '/training/' + courseId + '/addListener';
    }

    function uploadFiles() {
        return apiPrefix() + '/file_controller/add_files';
    }
})();