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

    function addComment(courseId) {
        return '/api/training/' + courseId + '/add_comment';
    }


    function addExCoach() {
        return '/api/user_controller/add_ex_coach';
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

    function approveLesson(actionId) {
        return '/api/training/' + actionId + '/confirm/lesson';
    }

    function cancelCreate(actionId) {
        return '/api/training/cancel_create/' + actionId;
    }

    function cancelChange(actionId) {
        return '/api/training/cancel_change/' + actionId;
    }

    function cancelEdit(trainingId) {
        return '/api/training/cancel_edit/' + trainingId;
    }

    function cancelLesson(actionId) {
        return '/api/training/' + actionId + '/canceled_lesson';
    }

    function changePassword() {
        return '/api/user_controller/set_password';
    }

    function confirm(trainingId) {
        return '/api/training/confirm/' + trainingId;
    }

    function createCourse() {
        return '/api/training/create';
    }

    function deleteCourse(courseId) {
        return '/api/training/' + courseId + '/remove';
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

    function editCourse(courseId) {
        return '/api/training/edit/' + courseId;
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

    function getExCoachList() {
        return '/api/user_controller/get_all_ex_coach';
    }

    function getFeedbacksOnUser(userId) {
        return '/api/feedback_controller/feedbacks_of_user/' + userId;
    }

    function getFeedback(fbId) {
        return '/api/feedback_controller/get_feedback/' + fbId;
    }

    function getLessonToApprove(actionId) {
        return '/api/training/' + actionId + '/approve_lesson';
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

    function getStatistics() {
        return '/api/statistics';
    }

    function getTimetable(courseId) {
        return '/api/training/' + courseId + '/lesson_list';
    }

    function getWaitingCoursesForUser(userId) {
        return '/api/user_controller/waitingTraining/' + userId;
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